package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyHealthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyHealthRepository
 * @CreateTime 2019/4/14 1:57
 * @Description: TODO
 */
@Repository
public interface JourneyHealthRepository extends JpaRepository<JourneyHealthEntity, Long> {
}
