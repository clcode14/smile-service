package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.JourneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyRepository
 * @CreateTime 2019/4/10 2:43
 * @Description: TODO
 */
@Repository
public interface JourneyRepository extends JpaRepository<JourneyEntity, Integer> {
    @Transactional
    @Query("update JourneyEntity j set j.audit=true where j.journeyId=?1")
    @Modifying
    void updateCheckStatus(Integer journeyId);
}
