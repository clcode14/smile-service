package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.UserRepository;
import org.flightythought.smile.appserver.dto.UserHeightWeightBirthdayDTO;
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
                String photoUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
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
            return userInfo;
        }
        return null;
    }
}
