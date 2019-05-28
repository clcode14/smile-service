package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserFansEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserFansRepository
 * @CreateTime 2019/5/27 15:35
 * @Description: TODO
 */
@Repository
public interface UserFansRepository extends JpaRepository<UserFansEntity, Long> {
    UserFansEntity findByFromUserIdAndToUserId(Long fromUserId, Long toUserId);

    /**
     * 获取关注人数
     */
    int countByFromUserId(Long userId);

    /**
     * 获取粉丝人数
     */
    int countByToUserId(Long userId);

    Page<UserFansEntity> findByFromUserId(Long userId, Pageable pageable);

    Page<UserFansEntity> findByToUserId(Long userId, Pageable pageable);
}
