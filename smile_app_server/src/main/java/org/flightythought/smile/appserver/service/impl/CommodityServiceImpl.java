package org.flightythought.smile.appserver.service.impl;

import io.jsonwebtoken.lang.Collections;
import org.flightythought.smile.appserver.bean.CommoditySimple;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.CommodityEntity;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.repository.CommodityRepository;
import org.flightythought.smile.appserver.dto.CommodityQueryDTO;
import org.flightythought.smile.appserver.service.CommodityService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 13:55
 * @Description: TODO
 */
@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    public Page<CommoditySimple> getCommofities(CommodityQueryDTO commodityQueryDTO) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) AS total FROM (";
        String sql = "SELECT DISTINCT c.commodity_id " +
                "FROM `tb_commodity` c " +
                "LEFT JOIN tb_solution_commodity sc " +
                "ON c.commodity_id = sc.commodity_id WHERE 1 = 1";
        // 解决方案ID
        Integer solutionId = commodityQueryDTO.getSolutionId();
        if (solutionId != null) {
            sql += " AND sc.solution_id = " + solutionId;
        }
        // 商品ID
        Integer commodityId = commodityQueryDTO.getCommodityId();
        if (commodityId != null) {
            sql += " AND c.commodity_id = " + commodityId;
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
        Integer pageNumber = commodityQueryDTO.getPageNumber();
        Integer pageSize = commodityQueryDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql += " LIMIT " + (pageNumber - 1) * pageSize + "," + pageSize;
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Integer> commodityIds = entityManager.createNativeQuery(sql)
                .unwrap(NativeQueryImpl.class)
                .addScalar("commodity_id", IntegerType.INSTANCE)
                .list();
        List<CommodityEntity> commodityEntities = commodityRepository.findByCommodityIdIn(commodityIds);
        List<CommoditySimple> commoditySimples = getCommoditySimples(commodityEntities);
        return new PageImpl<>(commoditySimples, pageable, total);
    }

    @Override
    public List<CommoditySimple> getCommoditySimples(List<CommodityEntity> commodityEntities) {
        if (!Collections.isEmpty(commodityEntities)) {
            String domainPort = platformUtils.getDomainPort();
            return commodityEntities.stream().map(commodityEntity -> {
                CommoditySimple commoditySimple = new CommoditySimple();
                BeanUtils.copyProperties(commodityEntity, commoditySimple);
                List<ImagesEntity> imagesEntities = commodityEntity.getImages();
                if (!Collections.isEmpty(imagesEntities)) {
                    List<ImageInfo> imageInfos = imagesEntities.stream()
                            .map(imagesEntity -> platformUtils.getImageInfo(imagesEntity, domainPort))
                            .collect(Collectors.toList());
                    commoditySimple.setImages(imageInfos);
                }
                return commoditySimple;
            }).collect(Collectors.toList());
        }
        return null;
    }
}
