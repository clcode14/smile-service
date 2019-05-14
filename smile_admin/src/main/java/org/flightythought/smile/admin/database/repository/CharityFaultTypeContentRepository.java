package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.CharityFaultTypeContentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 9:41
 * @Description: TODO
 */
@Repository
public interface CharityFaultTypeContentRepository extends JpaRepository<CharityFaultTypeContentEntity, Long> {
    List<CharityFaultTypeContentEntity> findByCfTypeId(Integer cfTypeId);

    Page<CharityFaultTypeContentEntity> findByCfTypeId(Integer cfTypeId, Pageable pageable);

    CharityFaultTypeContentEntity findById(Integer id);
}
