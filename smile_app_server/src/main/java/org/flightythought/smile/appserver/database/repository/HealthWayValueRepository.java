package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.HealthWayValueEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthWayValueRepository
 * @CreateTime 2019/5/26 14:11
 * @Description: TODO
 */
@Repository
public interface HealthWayValueRepository extends JpaRepository<HealthWayValueEntity, Integer> {
    Page<HealthWayValueEntity> findByHealthWayId(Integer healthWayId, Pageable pageable);

    List<HealthWayValueEntity> findByHealthWayId(Integer healthWayId);
}
