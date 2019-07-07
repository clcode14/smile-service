package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.dto.UserHeightWeightBirthdayDTO;
import org.flightythought.smile.appserver.dto.UserIdQueryDTO;
import org.flightythought.smile.appserver.dto.UserInfoDTO;
import org.springframework.data.domain.Page;

public interface UserService {
    /**
     * 修改用户信息
     *
     * @param userHeightWeightBirthdayDTO 用户身高、体重、生日DTO
     */
    UserInfo updateUserInfo(UserHeightWeightBirthdayDTO userHeightWeightBirthdayDTO);

    /**
     * 获得用户信息对象
     *
     * @param userEntity 用户对象实体类
     */
    UserInfo getUserInfo(UserEntity userEntity);

    /**
     * 根据用户ID获取用户信息
     *
     * @param userId 用户ID
     */
    UserInfo getUserInfo(Long userId);

    /**
     * 修改用户信息
     *
     * @param userInfo
     * @return
     */
    UserInfo updateUserInfoDetails(UserInfoDTO userInfoDTO);

    UserInfo updateUserNickName(String nickname);

    UserInfo updateUserPhoto(Integer imageId);

    void followUser(Long userId);

    void cancelFollowUser(Long userId);

    Page<UserInfo> getFanUser(UserIdQueryDTO userIdQueryDTO);

    Page<UserInfo> getFollowUser(UserIdQueryDTO userIdQueryDTO);
}
