package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.CharityFaultTypeContent;
import org.flightythought.smile.admin.database.entity.CharityFaultTypeContentEntity;
import org.springframework.data.domain.Page;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 16:43
 * @Description: TODO
 */
public interface CharityFaultTypeContentService {
    Page<CharityFaultTypeContent> getCharityFaultTypeContents(Integer pageSize, Integer pageNumber, Integer cfTypeId);

    CharityFaultTypeContentEntity addCharityFaultTypeContent(CharityFaultTypeContentEntity charityFaultTypeContentEntity);

    CharityFaultTypeContentEntity modifyCharityFaultTypeContent(CharityFaultTypeContentEntity charityFaultTypeContentEntity);

    void deleteCharityFaultTypeContent(Integer id);
}
