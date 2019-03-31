package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionRepository
 * @CreateTime 2019/3/27 22:00
 * @Description: 解决方案持久层
 */
@Repository
public interface SolutionRepository extends JpaRepository<SolutionEntity, Long> {
    SolutionEntity findById(Integer id);
}
