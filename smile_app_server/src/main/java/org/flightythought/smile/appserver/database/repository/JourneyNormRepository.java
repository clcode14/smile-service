package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyNormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
    JourneyNormEntity findByJourneyIdAndNormTypeId(Integer journeyId, Integer normTypeId);
}
