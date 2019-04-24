package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.HealthEntity;
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
public interface HealthRepository extends JpaRepository<HealthEntity, Long> {
    HealthEntity findByHealthId(Integer healthId);
}
