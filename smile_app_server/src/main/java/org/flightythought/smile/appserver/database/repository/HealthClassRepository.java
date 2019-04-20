package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.HealthEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthClassRepository
 * @CreateTime 2019/4/9 18:15
 * @Description: TODO
 */
@Repository
public interface HealthClassRepository extends JpaRepository<HealthEntity, Long> {
    HealthEntity findByHealthId(Integer healthId);
}
