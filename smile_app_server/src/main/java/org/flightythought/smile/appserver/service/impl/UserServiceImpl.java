package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.flightythought.smile.appserver.dto.UserHeightWeightBirthdayDTO;
import org.flightythought.smile.appserver.dto.UserInfoDTO;
import org.flightythought.smile.appserver.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private UserRepository userRepository;

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
}
