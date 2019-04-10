package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

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
}
