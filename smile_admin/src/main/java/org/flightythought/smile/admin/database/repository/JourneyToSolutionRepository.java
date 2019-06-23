package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.JourneyToSolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyToSolutionRepository
 * @CreateTime 2019/6/23 0:20
 * @Description: TODO
 */
@Repository
public interface JourneyToSolutionRepository extends JpaRepository<JourneyToSolutionEntity, Integer> {
    List<JourneyToSolutionEntity> findByJourneyId(Integer journeyId);
}
