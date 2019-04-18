package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.HealthResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthResultRepository
 * @CreateTime 2019/4/18 0:40
 * @Description: TODO
 */
@Repository
public interface HealthResultRepository extends JpaRepository<HealthResultEntity, Long> {
}
