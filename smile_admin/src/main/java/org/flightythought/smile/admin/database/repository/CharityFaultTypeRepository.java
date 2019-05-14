package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.CharityFaultTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
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
public interface CharityFaultTypeRepository extends JpaRepository<CharityFaultTypeEntity, Long> {
    CharityFaultTypeEntity findByCfTypeId(Integer cfTypeId);

    List<CharityFaultTypeEntity> findByCfTypeIdIn(Collection<Integer> cfTypeIds);
}
