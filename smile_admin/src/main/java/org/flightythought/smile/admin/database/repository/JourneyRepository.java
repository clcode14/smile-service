package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.JourneyEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.*;
import org.springframework.lang.Nullable;
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
public interface JourneyRepository extends JpaRepository<JourneyEntity, Integer>, JpaSpecificationExecutor<JourneyEntity> {
    @Transactional
    @Query("update JourneyEntity j set j.audit=true where j.journeyId=?1")
    @Modifying
    void updateCheckStatus(Integer journeyId);


    JourneyEntity findByJourneyId(Integer journeyId);

    @EntityGraph(value = "journey.Graph", type = EntityGraph.EntityGraphType.FETCH)
    Page<JourneyEntity> findAll(@Nullable Specification<JourneyEntity> var1, Pageable var2);
}
