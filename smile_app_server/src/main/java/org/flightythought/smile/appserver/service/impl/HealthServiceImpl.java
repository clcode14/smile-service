package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.HealthClass;
import org.flightythought.smile.appserver.bean.HealthClassDetail;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.HealthClassDetailEntity;
import org.flightythought.smile.appserver.database.entity.HealthClassEntity;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.repository.HealthClassDetailRepository;
import org.flightythought.smile.appserver.database.repository.HealthClassRepository;
import org.flightythought.smile.appserver.dto.HealthDetailQueryDTO;
import org.flightythought.smile.appserver.service.HealthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthServiceImpl
 * @CreateTime 2019/4/9 18:13
 * @Description: TODO
 */
@Service
public class HealthServiceImpl implements HealthService {

    @Autowired
    private HealthClassRepository healthClassRepository;
    @Autowired
    private HealthClassDetailRepository healthClassDetailRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    @Transactional
    public Page<HealthClass> findHealthClass(Integer pageSize, Integer pageNumber) {
        String domainPort = platformUtils.getDomainPort();
        if (pageSize == null || pageSize == 0 || pageNumber == null || pageNumber == 0) {
            // 获取全部
            List<HealthClassEntity> healthClassEntities = healthClassRepository.findAll();
            List<HealthClass> healthClasses = new ArrayList<>();
            healthClassEntities.forEach(healthClassEntity -> {
                HealthClass healthClass = new HealthClass();
                // 养生大类ID
                healthClass.setHealthId(healthClassEntity.getHealthId());
                // 编码
                healthClass.setNumber(healthClassEntity.getNumber());
                // 养生大类名称
                healthClass.setHealthName(healthClassEntity.getHealthName());
                // 背景图片URL
                ImagesEntity imagesEntity = healthClassEntity.getBgImage();
                if (imagesEntity != null) {
                    String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                    ImageInfo bgImage = new ImageInfo();
                    bgImage.setId(imagesEntity.getId());
                    bgImage.setName(imagesEntity.getFileName());
                    bgImage.setSize(imagesEntity.getSize());
                    bgImage.setUrl(imageUrl);
                    healthClass.setBgImage(bgImage);
                }
                // 内容介绍
                healthClass.setContent(healthClassEntity.getContent());
                healthClasses.add(healthClass);
            });
            return new PageImpl<>(healthClasses, PageRequest.of(0, healthClasses.size()), healthClasses.size());
        } else {
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            Page<HealthClassEntity> healthClassEntities = healthClassRepository.findAll(pageRequest);
            List<HealthClass> healthClasses = new ArrayList<>();
            healthClassEntities.forEach(healthClassEntity -> {
                HealthClass healthClass = new HealthClass();
                // 养生大类ID
                healthClass.setHealthId(healthClassEntity.getHealthId());
                // 编码
                healthClass.setNumber(healthClassEntity.getNumber());
                // 养生大类名称
                healthClass.setHealthName(healthClassEntity.getHealthName());
                // 背景图片URL
                ImagesEntity imagesEntity = healthClassEntity.getBgImage();
                if (imagesEntity != null) {
                    String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                    ImageInfo bgImage = new ImageInfo();
                    bgImage.setId(imagesEntity.getId());
                    bgImage.setName(imagesEntity.getFileName());
                    bgImage.setSize(imagesEntity.getSize());
                    bgImage.setUrl(imageUrl);
                    healthClass.setBgImage(bgImage);
                }
                // 内容介绍
                healthClass.setContent(healthClassEntity.getContent());
                healthClasses.add(healthClass);
            });
            return new PageImpl<>(healthClasses, pageRequest, healthClassEntities.getTotalElements());
        }
    }

    @Override
    public Page<HealthClassDetail> findHealthDetailClass(HealthDetailQueryDTO healthDetailQueryDTO) {
        List<HealthClassDetailEntity> healthClassDetailEntities;
        Page<HealthClassDetailEntity> healthClassDetailEntityPage = null;
        Integer healthId = healthDetailQueryDTO.getHealthId();
        Integer pageSize = healthDetailQueryDTO.getPageSize();
        Integer pageNumber = healthDetailQueryDTO.getPageNumber();
        boolean needPage = pageNumber != null && pageNumber != 0 && pageSize != null && pageSize != 0;
        PageRequest pageRequest = null;
        String domainPort = platformUtils.getDomainPort();
        if (needPage) {
            pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        }
        if (healthId == null) {
            if (needPage) {
                // 分页
                healthClassDetailEntityPage = healthClassDetailRepository.findAll(pageRequest);
                healthClassDetailEntities = healthClassDetailEntityPage.getContent();
            } else {
                // 不用分页
                healthClassDetailEntities = healthClassDetailRepository.findAll();
            }
        } else {
            if (needPage) {
                // 分页
                healthClassDetailEntityPage = healthClassDetailRepository.findByHealthId(healthId, pageRequest);
                healthClassDetailEntities = healthClassDetailEntityPage.getContent();
            } else {
                // 不用分页
                healthClassDetailEntities = healthClassDetailRepository.findByHealthId(healthId);
            }
        }
        List<HealthClassDetail> healthClassDetails = new ArrayList<>();
        healthClassDetailEntities.forEach(healthClassDetailEntity -> {
            HealthClassDetail healthClassDetail = new HealthClassDetail();
            // 养生小类ID
            healthClassDetail.setHealthDetailId(healthClassDetailEntity.getHealthDetailId());
            // 养生大类ID
            healthClassDetail.setHealthId(healthClassDetailEntity.getHealthId());
            // 养生小类名称
            healthClassDetail.setHealthDetailName(healthClassDetailEntity.getHealthDetailName());
            // 背景图片
            ImagesEntity imagesEntity = healthClassDetailEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(imagesEntity.getId());
                imageInfo.setSize(imagesEntity.getSize());
                imageInfo.setName(imagesEntity.getFileName());
                String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                imageInfo.setUrl(imageUrl);
                healthClassDetail.setBgImage(imageInfo);
            }
            // 标题
            healthClassDetail.setTitle(healthClassDetailEntity.getTitle());
            // 编码
            healthClassDetail.setNumber(healthClassDetailEntity.getNumber());
            // 内容介绍
            healthClassDetail.setContent(healthClassDetailEntity.getContent());
            healthClassDetails.add(healthClassDetail);
        });
        if (needPage) {
            return new PageImpl<>(healthClassDetails, pageRequest, healthClassDetailEntityPage.getTotalElements());
        } else {
            return new PageImpl<>(healthClassDetails, PageRequest.of(0, healthClassDetails.size()), healthClassDetails.size());
        }
    }

    @Override
    public HealthClassDetail getHealthDetailClass(Integer healthDetailClassId) {
        HealthClassDetailEntity healthClassDetailEntity = healthClassDetailRepository.findByHealthDetailId(healthDetailClassId);
        String domainPort = platformUtils.getDomainPort();
        if (healthClassDetailEntity != null) {
            HealthClassDetail healthClassDetail = new HealthClassDetail();
            // 养生小类ID
            healthClassDetail.setHealthDetailId(healthClassDetailEntity.getHealthDetailId());
            // 养生大类ID
            healthClassDetail.setHealthId(healthClassDetailEntity.getHealthId());
            // 养生小流名称
            healthClassDetail.setHealthDetailName(healthClassDetailEntity.getHealthDetailName());
            // 背景图片
            ImagesEntity imagesEntity = healthClassDetailEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(imagesEntity.getId());
                imageInfo.setName(imagesEntity.getFileName());
                imageInfo.setSize(imagesEntity.getSize());
                String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                imageInfo.setUrl(imageUrl);
                healthClassDetail.setBgImage(imageInfo);
            }
            // 标题
            healthClassDetail.setTitle(healthClassDetailEntity.getTitle());
            // 编码
            healthClassDetail.setNumber(healthClassDetailEntity.getNumber());
            // 内容介绍
            healthClassDetail.setContent(healthClassDetailEntity.getContent());
            return healthClassDetail;
        }
        return null;
    }

    @Override
    public HealthClass getHealthClass(Integer healthClassId) {
        // 获取养生大类
        HealthClassEntity healthClassEntity = healthClassRepository.findByHealthId(healthClassId);
        String domainPort = platformUtils.getDomainPort();
        if (healthClassEntity != null) {
            HealthClass healthClass = new HealthClass();
            // 养生大类ID
            healthClass.setHealthId(healthClassEntity.getHealthId());
            // 编码
            healthClass.setNumber(healthClassEntity.getNumber());
            // 养生大类名称
            healthClass.setHealthName(healthClassEntity.getHealthName());
            // 背景图片
            ImagesEntity imagesEntity = healthClassEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(imagesEntity.getId());
                imageInfo.setSize(imagesEntity.getSize());
                imageInfo.setName(imagesEntity.getFileName());
                String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                imageInfo.setUrl(imageUrl);
                healthClass.setBgImage(imageInfo);
            }
            // 内容介绍
            healthClass.setContent(healthClassEntity.getContent());
            return healthClass;
        }
        return null;
    }
}
