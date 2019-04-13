package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SolutionCourseEntity;
import org.flightythought.smile.admin.database.entity.SolutionOfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionOfficeRepository
 * @CreateTime 2019/4/1 21:50
 * @Description: TODO
 */
@Repository
public interface SolutionOfficeRepository extends JpaRepository<SolutionOfficeEntity, Long> {
    void deleteAllBySolutionId(Integer solutionId);
}
