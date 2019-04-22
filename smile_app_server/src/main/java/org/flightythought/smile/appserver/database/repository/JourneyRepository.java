package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyRepository
 * @CreateTime 2019/4/10 2:43
 * @Description: TODO
 */
@Repository
public interface JourneyRepository extends JpaRepository<JourneyEntity, Long> {
    List<JourneyEntity> findByUserId(Long userId);

    List<JourneyEntity> findByUserIdAndFinished(Long userId, Boolean finished);

    Page<JourneyEntity> findByUserId(Long userId, Pageable pageable);

    Page<JourneyEntity> findByUserIdAndFinished(Long userId, Boolean finished, Pageable pageable);

    JourneyEntity findByJourneyId(Integer journeyId);

    JourneyEntity findByJourneyIdAndUserId(Integer journeyId, Long userId);
}
