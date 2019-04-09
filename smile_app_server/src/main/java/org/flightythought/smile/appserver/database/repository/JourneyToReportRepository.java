package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyToReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyToReportRepository
 * @CreateTime 2019/4/10 2:58
 * @Description: TODO
 */
@Repository
public interface JourneyToReportRepository extends JpaRepository<JourneyToReportEntity, Long> {
}
