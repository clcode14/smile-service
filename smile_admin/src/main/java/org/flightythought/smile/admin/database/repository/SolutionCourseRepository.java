package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SolutionCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionCourseRepository
 * @CreateTime 2019/4/1 21:50
 * @Description: TODO
 */
@Repository
public interface SolutionCourseRepository extends JpaRepository<SolutionCourseEntity, Long> {
    void deleteAllBySolutionId(Integer solutionId);
}
