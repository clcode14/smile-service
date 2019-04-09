package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.HealthClass;
import org.flightythought.smile.appserver.bean.HealthClassDetail;
import org.flightythought.smile.appserver.dto.HealthDetailQueryDTO;
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

    Page<HealthClass> findHealthClass(Integer pageSize, Integer pageNumber);

    Page<HealthClassDetail> findHealthDetailClass(HealthDetailQueryDTO healthDetailQueryDTO);

    HealthClassDetail getHealthDetailClass(Integer healthDetailClassId);
}
