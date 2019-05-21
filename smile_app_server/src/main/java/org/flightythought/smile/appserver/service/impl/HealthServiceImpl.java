package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.HealthClass;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.bean.SolutionSimple;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.HealthEntity;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.SolutionEntity;
import org.flightythought.smile.appserver.database.repository.HealthClassRepository;
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
    private PlatformUtils platformUtils;

    @Override
    @Transactional
    public Page<HealthClass> findHealth(Integer pageSize, Integer pageNumber) {
        if (pageSize == null || pageSize == 0 || pageNumber == null || pageNumber == 0) {
            // 获取全部
            List<HealthEntity> healthClassEntities = healthClassRepository.findAll();
            List<HealthClass> healthClasses = getHealthClass(healthClassEntities);
            return new PageImpl<>(healthClasses, PageRequest.of(0, healthClasses.size()), healthClasses.size());
        } else {
            PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            Page<HealthEntity> healthClassEntities = healthClassRepository.findAll(pageRequest);
            List<HealthClass> healthClasses = getHealthClass(healthClassEntities.getContent());
            return new PageImpl<>(healthClasses, pageRequest, healthClassEntities.getTotalElements());
        }
    }

    public List<HealthClass> getHealthClass(List<HealthEntity> healthEntities) {
        String domainPort = platformUtils.getDomainPort();
        List<HealthClass> result = new ArrayList<>();
        if (healthEntities != null && healthEntities.size() > 0) {
            healthEntities.forEach(healthEntity -> {
                HealthClass healthClass = new HealthClass();
                // 养生ID
                healthClass.setHealthId(healthEntity.getHealthId());
                // 编码
                healthClass.setNumber(healthEntity.getNumber());
                // 养生名称
                healthClass.setHealthName(healthEntity.getHealthName());
                // 背景图片URL
                ImagesEntity imagesEntity = healthEntity.getBgImage();
                if (imagesEntity != null) {
                    ImageInfo bgImage = platformUtils.getImageInfo(imagesEntity, domainPort);
                    healthClass.setBgImage(bgImage);
                }
                // 内容介绍
                healthClass.setContent(healthEntity.getContent());
                // 解决方案
                List<SolutionSimple> solutionSimples = new ArrayList<>();
                List<SolutionEntity> solutionEntities = healthEntity.getSolutions();
                if (solutionEntities != null && solutionEntities.size() > 0) {
                    solutionEntities.forEach(solutionEntity -> {
                        SolutionSimple solutionSimple = new SolutionSimple();
                        // 解决方案ID
                        solutionSimple.setSolutionId(solutionEntity.getId());
                        // 康复人数
                        solutionSimple.setRecoverNumber(solutionEntity.getRecoverNumber());
                        // 解决方案标题
                        solutionSimple.setTitle(solutionEntity.getTitle());
                        // 图片
                        List<String> imageUrls = new ArrayList<>();
                        List<ImagesEntity> imagesEntities = solutionEntity.getImages();
                        if (imagesEntities != null && imagesEntities.size() > 0) {
                            imagesEntities.forEach(image -> {
                                String url = platformUtils.getImageInfo(image, domainPort).getUrl();
                                imageUrls.add(url);
                            });
                        }
                        solutionSimple.setImageUrls(imageUrls);
                        solutionSimples.add(solutionSimple);
                    });
                }
                healthClass.setSolutions(solutionSimples);
                result.add(healthClass);
            });
        }
        return result;
    }

    @Override
    public HealthClass getHealthClass(Integer healthClassId) {
        // 获取养生大类
        HealthEntity healthEntity = healthClassRepository.findByHealthId(healthClassId);
        String domainPort = platformUtils.getDomainPort();
        if (healthEntity != null) {
            HealthClass healthClass = new HealthClass();
            // 养生大类ID
            healthClass.setHealthId(healthEntity.getHealthId());
            // 编码
            healthClass.setNumber(healthEntity.getNumber());
            // 养生大类名称
            healthClass.setHealthName(healthEntity.getHealthName());
            // 背景图片
            ImagesEntity imagesEntity = healthEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domainPort);
                healthClass.setBgImage(imageInfo);
            }
            // 内容介绍
            healthClass.setContent(healthEntity.getContent());
            return healthClass;
        }
        return null;
    }
}
