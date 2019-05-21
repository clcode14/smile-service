package org.flightythought.smile.appserver.service.impl;

import io.jsonwebtoken.lang.Collections;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.bean.OfficeSimple;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.OfficeEntity;
import org.flightythought.smile.appserver.database.repository.OfficeRepository;
import org.flightythought.smile.appserver.dto.OfficeQueryDTO;
import org.flightythought.smile.appserver.service.OfficeService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.hibernate.type.LongType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeServiceImpl
 * @CreateTime 2019/5/21 23:51
 * @Description: TODO
 */
@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    @Transactional
    public Page<OfficeSimple> getOffices(OfficeQueryDTO officeQueryDTO) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) AS total FROM (";
        String sql = "SELECT DISTINCT o.`office_id` " +
                "FROM `tb_office` o " +
                "LEFT JOIN `tb_solution_office` so " +
                "ON o.`office_id` = so.`office_id` WHERE 1 = 1";
        // 解决方案ID
        Integer solutionId = officeQueryDTO.getSolutionId();
        if (solutionId != null) {
            sql += " AND so.solution_id = " + solutionId;
        }
        // 机构ID
        Long officeId = officeQueryDTO.getOfficeId();
        if (officeId != null) {
            sql += " AND o.office_id = " + officeId;
        }
        totalSql += sql + ") T";
        // 获取ToTal总数
        Integer total = (Integer) entityManager.createNativeQuery(totalSql)
                .unwrap(NativeQueryImpl.class)
                .addScalar("total", IntegerType.INSTANCE).getSingleResult();
        if (total == 0) {
            throw new FlightyThoughtException("未查询到相关结果");
        }
        // 是否存在分页查询
        Integer pageNumber = officeQueryDTO.getPageNumber();
        Integer pageSize = officeQueryDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql += " LIMIT " + (pageNumber - 1) * pageSize + "," + pageSize;
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Long> officeIds = entityManager.createNativeQuery(sql)
                .unwrap(NativeQueryImpl.class)
                .addScalar("office_id", LongType.INSTANCE)
                .list();
        List<OfficeEntity> officeEntities = officeRepository.findByOfficeIdIn(officeIds);
        List<OfficeSimple> officeSimples = getOfficeSimples(officeEntities);
        return new PageImpl<>(officeSimples, pageable, total);
    }

    @Override
    public List<OfficeSimple> getOfficeSimples(List<OfficeEntity> officeEntities) {
        if (!Collections.isEmpty(officeEntities)) {
            String domainPort = platformUtils.getDomainPort();
            return officeEntities.stream().map(officeEntity -> {
                OfficeSimple officeSimple = new OfficeSimple();
                BeanUtils.copyProperties(officeEntity, officeSimple);
                List<ImagesEntity> imagesEntities = officeEntity.getImages();
                if (!Collections.isEmpty(imagesEntities)) {
                    List<ImageInfo> imageInfos = imagesEntities.stream()
                            .map(imagesEntity -> platformUtils.getImageInfo(imagesEntity, domainPort))
                            .collect(Collectors.toList());
                    officeSimple.setImages(imageInfos);
                }
                return officeSimple;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
