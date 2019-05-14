package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.CharityFaultTypeEntity;
import org.springframework.data.domain.Page;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 9:39
 * @Description: TODO
 */
public interface CharityFaultTypeService {

    Page<CharityFaultTypeEntity> getCharityFaultTypes(Integer pageSize, Integer pageNumber);

    CharityFaultTypeEntity addCharityFaultType(CharityFaultTypeEntity charityFaultTypeEntity);

    CharityFaultTypeEntity modifyCharityFaultType(CharityFaultTypeEntity charityFaultTypeEntity);

    void deleteCharityFaultType(Integer cfTypeId);
}
