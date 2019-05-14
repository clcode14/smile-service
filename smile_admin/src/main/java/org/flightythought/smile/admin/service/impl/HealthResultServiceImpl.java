package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.HealthResultEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.HealthResultRepository;
import org.flightythought.smile.admin.service.HealthResultService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 15:18
 * @Description: TODO
 */
@Service
public class HealthResultServiceImpl implements HealthResultService {

    @Autowired
    private HealthResultRepository healthResultRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    public Page<HealthResultEntity> getHealthResult(Integer pageSize, Integer pageNumber) {
        if (pageNumber == null || pageNumber == 0 || pageSize == null || pageSize == 0) {
            // 获取全部结果
            List<HealthResultEntity> healthResultEntities = healthResultRepository.findAll();
            return new PageImpl<>(healthResultEntities, PageRequest.of(0, healthResultEntities.size() + 1), healthResultEntities.size());
        } else {
            return healthResultRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
        }
    }

    @Override
    public HealthResultEntity addHealthResult(HealthResultEntity healthResultEntity) {
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        healthResultEntity.setCreateUserName(userEntity.getLoginName());
        return healthResultRepository.save(healthResultEntity);
    }

    @Override
    public HealthResultEntity modifyHealthResult(HealthResultEntity healthResultEntity) {
        HealthResultEntity entity = healthResultRepository.getOne(healthResultEntity.getId());
        BeanUtils.copyProperties(healthResultEntity, entity);
        return healthResultRepository.save(entity);
    }

    @Override
    public void deleteHealthResult(Integer healthResultId) {
        HealthResultEntity entity = healthResultRepository.getOne(healthResultId);
        healthResultRepository.delete(entity);
    }
}
