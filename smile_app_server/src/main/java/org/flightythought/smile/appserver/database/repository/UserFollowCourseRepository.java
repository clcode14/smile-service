package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserFollowCourseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserFollowCourseRepository
 * @CreateTime 2019/4/13 19:03
 * @Description: TODO
 */
@Repository
public interface UserFollowCourseRepository extends JpaRepository<UserFollowCourseEntity, Long> {
    UserFollowCourseEntity findByUserIdAndCourseId(Long userId, Integer courseId);

    Page<UserFollowCourseEntity> findByUserId(Long userId, Pageable pageable);

    List<UserFollowCourseEntity> findByUserId(Long userId);
}
