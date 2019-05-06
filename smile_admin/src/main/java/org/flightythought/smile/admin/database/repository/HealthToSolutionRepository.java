package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.HealthToSolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthToSolutionRepository
 * @CreateTime 2019/5/3 1:29
 * @Description: TODO
 */
@Repository
public interface HealthToSolutionRepository extends JpaRepository<HealthToSolutionEntity, Long> {
    void deleteAllByHealthId(Integer healthId);
}
