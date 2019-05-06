package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.CourseBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CourseBannerRepository
 * @CreateTime 2019/5/2 23:47
 * @Description: TODO
 */
@Repository
public interface CourseBannerRepository extends JpaRepository<CourseBannerEntity, Long> {
    List<CourseBannerEntity> findByCourseIdIn(List<Integer> courseId);
}
