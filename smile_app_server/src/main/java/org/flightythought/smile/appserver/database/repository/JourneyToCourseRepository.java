package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyToCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyToCourseRepository
 * @CreateTime 2019/4/14 2:14
 * @Description: TODO
 */
@Repository
public interface JourneyToCourseRepository extends JpaRepository<JourneyToCourseEntity, Long> {
}
