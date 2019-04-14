package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.HealthClassDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthClassDetailRepository
 * @CreateTime 2019/4/9 18:51
 * @Description: TODO
 */
@Repository
public interface HealthClassDetailRepository extends JpaRepository<HealthClassDetailEntity, Long> {
    Page<HealthClassDetailEntity> findByHealthId(Integer healthId, Pageable pageable);

    List<HealthClassDetailEntity> findByHealthId(Integer healthId);

    HealthClassDetailEntity findByHealthDetailId(Integer healthDetailId);
}
