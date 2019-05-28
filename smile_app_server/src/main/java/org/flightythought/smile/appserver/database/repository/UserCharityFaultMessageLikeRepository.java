package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserCharityFaultMessageLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserCharityFaultMessageLikeRepository
 * @CreateTime 2019/5/26 22:12
 * @Description: TODO
 */
@Repository
public interface UserCharityFaultMessageLikeRepository extends JpaRepository<UserCharityFaultMessageLikeEntity, Long> {
    UserCharityFaultMessageLikeEntity findByUserIdAndMessageId(Long userId, Integer messageId);

    List<UserCharityFaultMessageLikeEntity> findByUserIdAndMessageIdIn(Long userId, List<Integer> messageIds);
}
