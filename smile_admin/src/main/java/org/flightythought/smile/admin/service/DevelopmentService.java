package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.SysResourceUrlEntity;

import java.util.List;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DevelopmentService
 * @CreateTime 2019/6/11 0:50
 * @Description: TODO
 */
public interface DevelopmentService {
    List<Map<String, String>> getAllURL();

    List<SysResourceUrlEntity> saveOrUpdateResourceUrl();
}
