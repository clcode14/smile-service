package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.HealthWaySimple;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.HealthWayEntity;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.repository.HealthWayRepository;
import org.flightythought.smile.appserver.dto.HealthWayQueryDTO;
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
        healthWayEntities.forEach(healthWayEntity -> {
            HealthWaySimple healthWaySimple = new HealthWaySimple();
            // 养生方式ID
            healthWaySimple.setHealthWayId(healthWayEntity.getHealthWayId());
            // 养生方式名称
            healthWaySimple.setWayName(healthWayEntity.getWayName());
            // 背景图
            ImagesEntity imagesEntity = healthWayEntity.getBgImage();
            if (imagesEntity != null) {
                healthWaySimple.setBgImage(platformUtils.getImageInfo(imagesEntity));
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
}
