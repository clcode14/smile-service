package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.HealthClassDetailInfo;
import org.flightythought.smile.admin.bean.HealthClassInfo;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.HealthClassDetailEntity;
import org.flightythought.smile.admin.database.entity.HealthClassEntity;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.HealthClassDetailRepository;
import org.flightythought.smile.admin.database.repository.HealthClassRepository;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.flightythought.smile.admin.dto.HealthClassDTO;
import org.flightythought.smile.admin.service.HealthClassDetailService;
import org.flightythought.smile.admin.service.HealthClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthServiceImpl
 * @CreateTime 2019/4/9 18:13
 * @Description: TODO
 */
@Service
public class HealthClassDetailServiceImpl implements HealthClassDetailService {

    @Autowired
    private HealthClassRepository healthClassRepository;
    @Autowired
    private HealthClassDetailRepository healthClassDetailRepository;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Value("${image-url}")
    private String imageRequest;
    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Override
    public Page<HealthClassDetailInfo> findHealthDetailClass(Map<String, String> params) {

        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");
        String healthId = params.get("healthId");
        String title = params.get("title");
        String healthDetailName = params.get("healthDetailName");
        String domainPort = platformUtils.getDomainPort();

        HealthClassDetailEntity healthClassDetailEntity = new HealthClassDetailEntity();

        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        if (StringUtils.isBlank(healthId)) {
            healthClassDetailEntity.setHealthId(Integer.parseInt(healthId));
        }
        if (StringUtils.isBlank(title)) {
            healthClassDetailEntity.setTitle(title);
        }
        if (StringUtils.isBlank(healthDetailName)) {
            healthClassDetailEntity.setHealthDetailName(healthDetailName);
        }

        PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNumber) - 1, Integer.parseInt(pageSize));
        Page<HealthClassDetailEntity> healthClassDetailEntityPage = healthClassDetailRepository.findAll(Example.of(healthClassDetailEntity), pageRequest);

        List<HealthClassDetailInfo> healthClassDetailInfos = healthClassDetailEntityPage.getContent()
                .stream()
                .map(healthClassDetailEntity1 -> {
                    HealthClassDetailInfo healthClassDetailInfo = new HealthClassDetailInfo();
                    // 养生小类ID
                    healthClassDetailInfo.setHealthDetailId(healthClassDetailEntity.getHealthDetailId());
                    // 养生大类ID
                    healthClassDetailInfo.setHealthId(healthClassDetailEntity.getHealthId());
                    // 养生小类名称
                    healthClassDetailInfo.setHealthDetailName(healthClassDetailEntity.getHealthDetailName());
                    // 背景图片
                    ImagesEntity imagesEntity = healthClassDetailEntity.getBgImage();
                    if (imagesEntity != null) {
                        ImageInfo imageInfo = new ImageInfo();
                        imageInfo.setId(imagesEntity.getId());
                        imageInfo.setSize(imagesEntity.getSize());
                        imageInfo.setName(imagesEntity.getFileName());
                        String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                        imageInfo.setUrl(imageUrl);
                        healthClassDetailInfo.setBgImage(imageInfo);
                    }
                    // 标题
                    healthClassDetailInfo.setTitle(healthClassDetailEntity.getTitle());
                    // 编码
                    healthClassDetailInfo.setNumber(healthClassDetailEntity.getNumber());
                    // 内容介绍
                    healthClassDetailInfo.setContent(healthClassDetailEntity.getContent());
                    return healthClassDetailInfo;
                }).collect(Collectors.toList());
        return new PageImpl<>(healthClassDetailInfos, pageRequest, healthClassDetailEntityPage.getTotalElements());
    }

    @Override
    public HealthClassDetailInfo getHealthDetailClass(Integer healthDetailClassId) {
        HealthClassDetailEntity healthClassDetailEntity = healthClassDetailRepository.findByHealthDetailId(healthDetailClassId);
        String domainPort = platformUtils.getDomainPort();
        if (healthClassDetailEntity != null) {
            HealthClassDetailInfo healthClassDetailInfo = new HealthClassDetailInfo();
            // 养生小类ID
            healthClassDetailInfo.setHealthDetailId(healthClassDetailEntity.getHealthDetailId());
            // 养生大类ID
            healthClassDetailInfo.setHealthId(healthClassDetailEntity.getHealthId());
            // 养生小流名称
            healthClassDetailInfo.setHealthDetailName(healthClassDetailEntity.getHealthDetailName());
            // 背景图片
            ImagesEntity imagesEntity = healthClassDetailEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(imagesEntity.getId());
                imageInfo.setName(imagesEntity.getFileName());
                imageInfo.setSize(imagesEntity.getSize());
                String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                imageInfo.setUrl(imageUrl);
                healthClassDetailInfo.setBgImage(imageInfo);
            }
            // 标题
            healthClassDetailInfo.setTitle(healthClassDetailEntity.getTitle());
            // 编码
            healthClassDetailInfo.setNumber(healthClassDetailEntity.getNumber());
            // 内容介绍
            healthClassDetailInfo.setContent(healthClassDetailEntity.getContent());
            return healthClassDetailInfo;
        }
        return null;
    }

}
