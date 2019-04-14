package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.HealthClassInfo;
import org.flightythought.smile.admin.bean.HealthClassDetailInfo;
import org.flightythought.smile.admin.database.entity.HealthClassEntity;
import org.flightythought.smile.admin.dto.HealthClassDTO;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthClassService
 * @CreateTime 2019/4/9 18:12
 * @Description: TODO
 */
public interface HealthClassService {

    Page<HealthClassInfo> findHealthClass(Map<String,String> params);

    HealthClassInfo getHealthClass(Integer healthClassId);

    HealthClassEntity saveHealthClass(HealthClassDTO healthClassDTO, HttpSession session);

    HealthClassEntity modifyHealthClass(HealthClassDTO healthClassDTO, HttpSession session);

    void deleteHealthClass(Long healthId, HttpSession session);
}
