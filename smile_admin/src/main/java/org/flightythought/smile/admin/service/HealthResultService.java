package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.HealthResultEntity;
import org.springframework.data.domain.Page;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 15:18
 * @Description: TODO
 */
public interface HealthResultService {
    Page<HealthResultEntity> getHealthResult(Integer pageSize, Integer pageNumber);

    HealthResultEntity addHealthResult(HealthResultEntity healthResultEntity);

    HealthResultEntity modifyHealthResult(HealthResultEntity healthResultEntity);

    void deleteHealthResult(Integer healthResultId);
}
