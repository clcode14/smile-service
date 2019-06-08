package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.UserFollowCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserFollowCourseRepository
 * @CreateTime 2019/4/13 19:03
 * @Description: TODO
 */
@Repository
public interface UserFollowCourseRepository extends JpaRepository<UserFollowCourseEntity, Long>,JpaSpecificationExecutor<UserFollowCourseEntity> {
}
