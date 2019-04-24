package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.JourneyNormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNormRepository
 * @CreateTime 2019/4/10 2:52
 * @Description: TODO
 */
@Repository
public interface JourneyNormRepository extends JpaRepository<JourneyNormEntity, Long> {

    List<JourneyNormEntity> findByJourneyId(Integer journeyId);
}
