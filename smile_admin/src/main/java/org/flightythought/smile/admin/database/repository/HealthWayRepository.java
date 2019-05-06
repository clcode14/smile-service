package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.HealthWayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthWayRepository
 * @CreateTime 2019/5/4 22:19
 * @Description: TODO
 */
@Repository
public interface HealthWayRepository extends JpaRepository<HealthWayEntity, Long> {

    HealthWayEntity findByHealthWayId(Integer healthWayId);
}
