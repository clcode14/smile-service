package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.JourneyHealthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyHealthRepository
 * @CreateTime 2019/6/23 0:12
 * @Description: TODO
 */
@Repository
public interface JourneyHealthRepository extends JpaRepository<JourneyHealthEntity, Integer> {
}
