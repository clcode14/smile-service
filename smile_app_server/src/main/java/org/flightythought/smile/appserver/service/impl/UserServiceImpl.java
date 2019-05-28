package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.PushMessage;
import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.common.Constants;
import org.flightythought.smile.appserver.common.PushCodeEnum;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.JPushUtils;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.entity.UserFansEntity;
import org.flightythought.smile.appserver.database.repository.JourneyRepository;
import org.flightythought.smile.appserver.database.repository.UserFansRepository;
import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.flightythought.smile.appserver.dto.UserHeightWeightBirthdayDTO;
import org.flightythought.smile.appserver.dto.UserIdQueryDTO;
import org.flightythought.smile.appserver.dto.UserInfoDTO;
import org.flightythought.smile.appserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserFansRepository userFansRepository;
    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private JPushUtils jPushUtils;

    @Override
    public UserInfo updateUserInfo(UserHeightWeightBirthdayDTO userHeightWeightBirthdayDTO) {
        // 获取当前登陆用户
        UserEntity user = platformUtils.getCurrentLoginUser();
        if (user != null) {
            Long userId = user.getId();
            // 根据用户ID获取用户
            UserEntity userEntity = userRepository.getOne(userId);
            // 生日
            if (userHeightWeightBirthdayDTO.getBirthday() != null) {
                userEntity.setBirthday(userHeightWeightBirthdayDTO.getBirthday());
            }
            // 身高
            if (userHeightWeightBirthdayDTO.getHeight() != null) {
                userEntity.setHeight(userHeightWeightBirthdayDTO.getHeight());
            }
            // 体重
            if (userHeightWeightBirthdayDTO.getWeight() != null) {
                userEntity.setBodyWeight(userHeightWeightBirthdayDTO.getWeight());
            }
            // 更新用户信息
            userRepository.save(userEntity);
            return getUserInfo(userEntity);
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo(UserEntity userEntity) {
        if (userEntity != null) {
            String domainPort = platformUtils.getDomainPort();
            UserInfo userInfo = new UserInfo();
            // 用户ID
            userInfo.setUserId(userEntity.getId());
            // 用户姓名
            userInfo.setUserName(userEntity.getUsername());
            // 用户昵称
            userInfo.setNickName(userEntity.getNickName());
            // 用户头像
            ImagesEntity imagesEntity = userEntity.getPhotoImage();
            if (imagesEntity != null) {
                String photoUrl = platformUtils.getImageInfo(imagesEntity, domainPort).getUrl();
                userInfo.setPhoto(photoUrl);
            }
            // 手机号
            userInfo.setMobile(userEntity.getMobile());
            // 登陆次数
            userInfo.setLoginCount(userEntity.getLoginCount());
            // 身高
            userInfo.setHeight(userEntity.getHeight());
            // 体重
            userInfo.setWeight(userEntity.getBodyWeight());
            // 生日
            userInfo.setBirthday(userEntity.getBirthday());
            // 性别
            userInfo.setSex(userEntity.getSex());
            // 获取用户关注人数
            userInfo.setFollowNumber(userFansRepository.countByFromUserId(userInfo.getUserId()));
            // 获取用户粉丝人数
            userInfo.setFanNumber(userFansRepository.countByToUserId(userInfo.getUserId()));
            // 获取用户旅程数量
            userInfo.setJourneyNumber(journeyRepository.countByUserId(userInfo.getUserId()));
            // 判断该用户自己是否关注
            UserEntity currentUser = platformUtils.getCurrentLoginUser();
            UserFansEntity userFansEntity = userFansRepository.findByFromUserIdAndToUserId(currentUser.getId(), userEntity.getId());
            if (userFansEntity != null) {
                userInfo.setFollow(true);
            } else {
                userInfo.setFollow(false);
            }
            return userInfo;
        }
        return null;
    }

    @Override
    public UserInfo getUserInfo(Long userId) {
        UserEntity userEntity = userRepository.getOne(userId);
        return getUserInfo(userEntity);
    }

    @Override
    public UserInfo updateUserInfoDetails(UserInfoDTO userInfoDTO) {
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        // 查询用户信息
        userEntity = userRepository.getOne(userEntity.getId());
        // 修改用户信息
        // 用户昵称
        userEntity.setNickName(userInfoDTO.getNickName());
        // 头像ID
        userEntity.setPhoto(userInfoDTO.getPhotoId());
        // 生日
        userEntity.setBirthday(userInfoDTO.getBirthday());
        // 身高
        userEntity.setHeight(userInfoDTO.getHeight());
        // 体重
        userEntity.setBodyWeight(userInfoDTO.getWeight());
        // 性别
        userEntity.setSex(userInfoDTO.getSex());
        userEntity = userRepository.save(userEntity);
        return getUserInfo(userEntity);
    }

    @Override
    public void followUser(Long userId) {
        // 获取当前登录用户
        UserEntity currentUser = platformUtils.getCurrentLoginUser();
        if (currentUser.getId().equals(userId)) {
            throw new FlightyThoughtException("不能关注自身");
        }
        UserFansEntity userFansEntity = userFansRepository.findByFromUserIdAndToUserId(currentUser.getId(), userId);
        if (userFansEntity != null) {
            throw new FlightyThoughtException("该用户已关注");
        }
        userFansEntity = new UserFansEntity();
        userFansEntity.setFromUserId(currentUser.getId());
        userFansEntity.setToUserId(userId);
        userFansRepository.save(userFansEntity);
        // 推送消息
        PushMessage<UserInfo> pushMessage = new PushMessage<>();
        // 查询被关注用户
        UserInfo userInfo = this.getUserInfo(userId);
        pushMessage.setMessage(userInfo.getNickName() + "关注了你");
        pushMessage.setType(Constants.NOTICE_FANS);
        pushMessage.setTitle("新增粉丝");
        pushMessage.setCode(PushCodeEnum.ADD_FAN.getMessage());
        pushMessage.setData(userInfo);
        jPushUtils.pushData(pushMessage, userId);
    }

    @Override
    public void cancelFollowUser(Long userId) {
        UserEntity currentUser = platformUtils.getCurrentLoginUser();
        Long currentUserId = currentUser.getId();
        UserFansEntity userFansEntity = userFansRepository.findByFromUserIdAndToUserId(currentUserId, userId);
        if (userFansEntity != null) {
            userFansRepository.delete(userFansEntity);
        }
    }

    @Override
    @Transactional
    public Page<UserInfo> getFanUser(UserIdQueryDTO userIdQueryDTO) {
        PageRequest pageRequest = PageRequest.of(userIdQueryDTO.getPageNumber() - 1, userIdQueryDTO.getPageSize());
        Page<UserFansEntity> userFansEntities = userFansRepository.findByToUserId(userIdQueryDTO.getUserId(), pageRequest);
        List<UserInfo> userInfos = userFansEntities.stream()
                .map(userFansEntity -> getUserInfo(userFansEntity.getFromUser()))
                .collect(Collectors.toList());
        return new PageImpl<>(userInfos, pageRequest, userFansEntities.getTotalElements());
    }

    @Override
    public Page<UserInfo> getFollowUser(UserIdQueryDTO userIdQueryDTO) {
        PageRequest pageRequest = PageRequest.of(userIdQueryDTO.getPageNumber() - 1, userIdQueryDTO.getPageSize());
        Page<UserFansEntity> userFansEntities = userFansRepository.findByFromUserId(userIdQueryDTO.getUserId(), pageRequest);
        List<UserInfo> userInfos = userFansEntities.stream()
                .map(userFansEntity -> getUserInfo(userFansEntity.getToUser()))
                .collect(Collectors.toList());
        return new PageImpl<>(userInfos, pageRequest, userFansEntities.getTotalElements());
    }
}
