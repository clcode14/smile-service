package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.Commodity;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.CommodityEntity;
import org.flightythought.smile.admin.database.entity.CommodityImageEntity;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.CommodityImageRepository;
import org.flightythought.smile.admin.database.repository.CommodityRepository;
import org.flightythought.smile.admin.dto.CommodityDTO;
import org.flightythought.smile.admin.dto.CommodityQueryDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.CommodityService;
import org.flightythought.smile.admin.service.CommonService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 15:18
 * @Description: TODO
 */
@Service
public class CommodityServiceImpl implements CommodityService {
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private CommodityRepository commodityRepository;
    @Autowired
    private CommodityImageRepository commodityImageRepository;
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private CommonService commonService;

    @Override
    @Transactional
    public CommodityEntity addCommodityEntity(CommodityDTO commodityDTO) {
        CommodityEntity commodityEntity = new CommodityEntity();
        BeanUtils.copyProperties(commodityDTO, commodityEntity);
        // 登陆用户
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        // 创建者
        commodityEntity.setCreateUserName(userEntity.getLoginName());
        // 保存商品信息
        commodityEntity = commodityRepository.save(commodityEntity);
        // 商品ID
        Integer commodityId = commodityEntity.getCommodityId();
        if (commodityDTO.getImageIds() != null) {
            List<CommodityImageEntity> imageEntities = commodityDTO.getImageIds().stream().map(imageId -> {
                CommodityImageEntity commodityImageEntity = new CommodityImageEntity();
                commodityImageEntity.setCommodityId(commodityId);
                commodityImageEntity.setImageId(imageId);
                return commodityImageEntity;
            }).collect(Collectors.toList());
            if (imageEntities.size() > 0) {
                commodityImageRepository.saveAll(imageEntities);
            }
        }
        return commodityEntity;
    }

    @Override
    public CommodityEntity updateCommodityEntity(CommodityDTO commodityDTO) {
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        // 商品ID
        Integer commodityId = commodityDTO.getCommodityId();
        if (commodityId == null) {
            throw new FlightyThoughtException("请传递commodityId");
        }
        CommodityEntity commodityEntity = commodityRepository.findByCommodityId(commodityId);
        if (commodityEntity != null) {
            // SKU
            commodityEntity.setSku(commodityDTO.getSku());
            // 商品名称
            commodityEntity.setName(commodityDTO.getName());
            // 简介
            commodityEntity.setIntroduce(commodityDTO.getIntroduce());
            // 详情
            commodityEntity.setDescription(commodityDTO.getDescription());
            // 价格
            commodityEntity.setPrice(commodityDTO.getPrice());
            // 运费
            commodityEntity.setFreight(commodityDTO.getFreight());
            // 运费类型
            commodityEntity.setFreightType(commodityDTO.getFreightType());
            // 更新者
            commodityEntity.setUpdateUserName(userEntity.getLoginName());
            // 更新商品
            commodityRepository.save(commodityEntity);
        }
        // 删除图片关联
        List<CommodityImageEntity> commodityImageEntities = commodityImageRepository.findByCommodityId(commodityId);
        if (commodityImageEntities != null && commodityImageEntities.size() > 0) {
            // 删除图片
            commodityImageEntities.forEach(commodityImageEntity -> commonService.deleteImage(commodityImageEntity.getImageId()));
            // 删除数据
            commodityImageRepository.deleteAll(commodityImageEntities);
        }
        // 重新关联图片
        if (commodityDTO.getImageIds() != null) {
            List<CommodityImageEntity> imageEntities = commodityDTO.getImageIds().stream().map(imageId -> {
                CommodityImageEntity commodityImageEntity = new CommodityImageEntity();
                commodityImageEntity.setCommodityId(commodityId);
                commodityImageEntity.setImageId(imageId);
                return commodityImageEntity;
            }).collect(Collectors.toList());
            if (imageEntities.size() > 0) {
                commodityImageRepository.saveAll(imageEntities);
            }
        }
        return commodityEntity;
    }

    @Override
    @Transactional
    public Page<Commodity> getCommodities(CommodityQueryDTO commodityQueryDTO) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) AS total FROM (";
        String sql = "SELECT c.commodity_id FROM `tb_commodity` c WHERE 1 = 1";
        // 商品SKU
        String sku = commodityQueryDTO.getSku();
        if (StringUtils.isNotBlank(sku)) {
            sql += " AND c.sku LIKE :sku";
        }
        // 商品名称
        String name = commodityQueryDTO.getName();
        if (StringUtils.isNotBlank(name)) {
            sql += " AND c.name LIKE :commodityName";
        }
        // 价格最大值
        BigDecimal priceMax = commodityQueryDTO.getPriceMax();
        if (priceMax != null) {
            sql += " AND c.price <= " + priceMax.doubleValue();
        }
        // 价格最小值
        BigDecimal priceMin = commodityQueryDTO.getPriceMin();
        if (priceMin != null) {
            sql += " AND c.price >= " + priceMin.doubleValue();
        }
        totalSql += sql + ") T";
        // 获取ToTal总数
        Query queryTotal = entityManager.createNativeQuery(totalSql);
        if (StringUtils.isNotBlank(sku)) {
            queryTotal.setParameter("sku", "%" + sku + "%");
        }
        if (StringUtils.isNotBlank(name)) {
            queryTotal.setParameter("commodityName", "%" + name + "%");
        }
        // 获取ToTal总数
        Integer total = (Integer) queryTotal
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
        Query query = entityManager.createNativeQuery(sql);
        if (StringUtils.isNotBlank(sku)) {
            query.setParameter("sku", "%" + sku + "%");
        }
        if (StringUtils.isNotBlank(name)) {
            query.setParameter("commodityName", "%" + name + "%");
        }
        List<Integer> commodityIds = query.unwrap(NativeQueryImpl.class)
                .addScalar("commodity_id", IntegerType.INSTANCE)
                .list();
        List<CommodityEntity> commodityEntities = commodityRepository.findByCommodityIdIn(commodityIds);
        List<Commodity> commodities = getCommodities(commodityEntities);
        return new PageImpl<>(commodities, pageable, total);
    }

    @Override
    @Transactional
    public Commodity getCommodity(Integer commodityId) {
        if (commodityId != null) {
            CommodityEntity commodityEntity = commodityRepository.findByCommodityId(commodityId);
            if (commodityEntity != null) {
                String domainPort = platformUtils.getDomainPort();
                Commodity commodity = new Commodity();
                BeanUtils.copyProperties(commodityEntity, commodity);
                List<ImagesEntity> imagesEntities = commodityEntity.getImages();
                if (imagesEntities != null && imagesEntities.size() > 0) {
                    List<ImageInfo> imageInfos = imagesEntities.stream()
                            .map(imagesEntity -> platformUtils.getImageInfo(imagesEntity, domainPort))
                            .collect(Collectors.toList());
                    commodity.setImages(imageInfos);
                }
                return commodity;
            }
        }
        return null;
    }

    @Override
    public List<Commodity> getCommodities(List<CommodityEntity> commodityEntities) {
        if (commodityEntities != null && commodityEntities.size() > 0) {
            String domainPort = platformUtils.getDomainPort();
            return commodityEntities.stream().map(commodityEntity -> {
                Commodity commodity = new Commodity();
                BeanUtils.copyProperties(commodityEntity, commodity);
                List<ImagesEntity> imagesEntities = commodityEntity.getImages();
                if (imagesEntities != null && imagesEntities.size() > 0) {
                    List<ImageInfo> imageInfos = imagesEntities.stream()
                            .map(imagesEntity -> platformUtils.getImageInfo(imagesEntity, domainPort))
                            .collect(Collectors.toList());
                    commodity.setImages(imageInfos);
                }
                return commodity;
            }).collect(Collectors.toList());
        }
        return null;
    }

    @Override
    public void deleteCommodity(Integer commodityId) {
        CommodityEntity commodityEntity = commodityRepository.findByCommodityId(commodityId);
        if (commodityEntity != null) {
            commodityRepository.delete(commodityEntity);
        }
    }
}
