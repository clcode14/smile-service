package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserToMessageLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/1 15:29
 * @Description: TODO
 */
@Repository
public interface UserToMessageLikeRepository extends JpaRepository<UserToMessageLikeEntity, Long> {
    List<UserToMessageLikeEntity> findByUserIdAndMessageIdIn(Long userId, List<Integer> messageIds);

    UserToMessageLikeEntity findByUserIdAndMessageId(Long userId, Integer messageId);
}
