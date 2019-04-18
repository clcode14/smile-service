package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.HealthClass;
import org.springframework.data.domain.Page;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthService
 * @CreateTime 2019/4/9 18:12
 * @Description: TODO
 */
public interface HealthService {

    Page<HealthClass> findHealth(Integer pageSize, Integer pageNumber);

    HealthClass getHealthClass(Integer healthId);
}
