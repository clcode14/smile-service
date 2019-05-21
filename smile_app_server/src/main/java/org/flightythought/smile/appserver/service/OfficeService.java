package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.OfficeSimple;
import org.flightythought.smile.appserver.database.entity.OfficeEntity;
import org.flightythought.smile.appserver.dto.OfficeQueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeService
 * @CreateTime 2019/5/21 23:51
 * @Description: TODO
 */
public interface OfficeService {
    Page<OfficeSimple> getOffices(OfficeQueryDTO officeQueryDTO);

    List<OfficeSimple> getOfficeSimples(List<OfficeEntity> officeEntities);
}
