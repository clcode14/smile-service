package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.HealthWaySimple;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.HealthWayMusicRepository;
import org.flightythought.smile.appserver.database.repository.HealthWayRepository;
import org.flightythought.smile.appserver.database.repository.HealthWayValueRepository;
import org.flightythought.smile.appserver.dto.HealthWayQueryDTO;
import org.flightythought.smile.appserver.dto.HealthWayValueDTO;
import org.flightythought.smile.appserver.dto.HealthWayValueQueryDTO;
import org.flightythought.smile.appserver.service.HealthWayService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.List;

@Service
public class HealthWayServiceImpl implements HealthWayService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private HealthWayRepository healthWayRepository;
    @Autowired
    private HealthWayMusicRepository healthWayMusicRepository;
    @Autowired
    private HealthWayValueRepository healthWayValueRepository;


    @Override
    public Page<HealthWayEntity> getHealthWayEntities(HealthWayQueryDTO healthWayQueryDTO) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) AS total FROM (";
        StringBuilder sql = new StringBuilder("SELECT DISTINCT a.`health_way_id` AS healthWayId FROM `tb_health_way` a WHERE 1 = 1");
        // 判断是否传递Id
        List<Integer> healthWayIds = healthWayQueryDTO.getHealthWayIds();
        if (healthWayIds != null && healthWayIds.size() > 0) {
            sql.append(" OR a.`health_way_id` IN (");
            healthWayIds.forEach(integer -> sql.append(integer).append(","));
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        totalSql += sql.toString() + ") T";
        // 获取Total总数
        Integer total = (Integer) entityManager.createNativeQuery(totalSql).unwrap(NativeQueryImpl.class).addScalar("total", IntegerType.INSTANCE).getSingleResult();
        if (total == 0) {
            throw new FlightyThoughtException("没有查询到对应的解决方案");
        }
        // 是否存在分页查询
        Integer pageNumber = healthWayQueryDTO.getPageNumber();
        Integer pageSize = healthWayQueryDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql.append(" LIMIT ").append((pageNumber - 1) * pageSize).append(",").append(pageSize);
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Integer> ids = entityManager.createNativeQuery(sql.toString())
                .unwrap(NativeQueryImpl.class)
                .addScalar("healthWayId", IntegerType.INSTANCE)
                .list();
        List<HealthWayEntity> healthWayEntities = healthWayRepository.findByHealthWayIdIn(ids);
        return new PageImpl<>(healthWayEntities, pageable, total);
    }

    @Override
    public Page<HealthWaySimple> getHealthWays(HealthWayQueryDTO healthWayQueryDTO) {
        Page<HealthWayEntity> healthWayEntities = getHealthWayEntities(healthWayQueryDTO);
        List<HealthWaySimple> healthWaySimples = new ArrayList<>();
        String domainPort = platformUtils.getDomainPort();
        healthWayEntities.forEach(healthWayEntity -> {
            HealthWaySimple healthWaySimple = new HealthWaySimple();
            // 养生方式ID
            healthWaySimple.setHealthWayId(healthWayEntity.getHealthWayId());
            // 养生方式名称
            healthWaySimple.setWayName(healthWayEntity.getWayName());
            // 背景图
            ImagesEntity imagesEntity = healthWayEntity.getBgImage();
            if (imagesEntity != null) {
                healthWaySimple.setBgImage(platformUtils.getImageInfo(imagesEntity, domainPort));
            }
            // 编码
            healthWaySimple.setNumber(healthWayEntity.getNumber());
            // 介绍描述
            healthWaySimple.setContent(healthWayEntity.getContent());
            // 音乐链接
            healthWaySimple.setMusicUrl(healthWayEntity.getMusicUrl());
            // 养生方式类型
            healthWaySimple.setType(healthWayEntity.getType());
            healthWaySimples.add(healthWaySimple);
        });
        return new PageImpl<>(healthWaySimples, healthWayEntities.getPageable(), healthWayEntities.getTotalElements());
    }

    @Override
    public List<HealthWayMusicEntity> getHealthWayMusics(Integer healthWayId) {
        return healthWayMusicRepository.findByHealthWayId(healthWayId);
    }

    @Override
    public HealthWayValueEntity saveHealthWayValue(HealthWayValueDTO healthWayValueDTO) {
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        HealthWayValueEntity healthWayValueEntity = new HealthWayValueEntity();
        // 持续时间
        healthWayValueEntity.setDuration(healthWayValueDTO.getDurationSec());
        // 开始时间
        healthWayValueEntity.setStartTime(healthWayValueDTO.getStartTime());
        // 养生方式ID
        healthWayValueEntity.setHealthWayId(healthWayValueDTO.getHealthWayId());
        // 用户ID
        healthWayValueEntity.setUserId(userId);
        // 创建者
        healthWayValueEntity.setCreateUserName(userId + "");
        // 保存实体类
        healthWayValueEntity = healthWayValueRepository.save(healthWayValueEntity);
        return healthWayValueEntity;
    }

    @Override
    public Page<HealthWayValueEntity> getHealthWayValue(HealthWayValueQueryDTO healthWayValueQueryDTO) {
        // 是否有分页查询
        Integer pageSize = healthWayValueQueryDTO.getPageSize();
        Integer pageNumber = healthWayValueQueryDTO.getPageNumber();

        Integer healthWayId = healthWayValueQueryDTO.getHealthWayId();
        if (pageNumber == null || pageNumber == 0 || pageSize == null || pageSize == 0) {
            List<HealthWayValueEntity> healthWayValueEntities = healthWayValueRepository.findByHealthWayId(healthWayId);
            PageRequest pageRequest = PageRequest.of(0, healthWayValueEntities.size() + 1);
            return new PageImpl<>(healthWayValueEntities, pageRequest, healthWayValueEntities.size());
        } else {
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            return healthWayValueRepository.findByHealthWayId(healthWayId, pageRequest);
        }
    }
}
