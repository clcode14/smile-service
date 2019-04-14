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

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthServiceImpl
 * @CreateTime 2019/4/9 18:13
 * @Description: TODO
 */
@Service
public class HealthClassServiceImpl implements HealthClassService {

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
    @Transactional
    public Page<HealthClassInfo> findHealthClass(Map<String, String> params) {
        String domainPort = platformUtils.getDomainPort();
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");

        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        PageRequest pageRequest = PageRequest.of(Integer.parseInt(pageNumber) - 1, Integer.parseInt(pageSize));
        Page<HealthClassEntity> healthClassEntities = healthClassRepository.findAll(pageRequest);
        List<HealthClassInfo> healthClassInfos = new ArrayList<>();
        healthClassEntities.forEach(healthClassEntity -> {
            HealthClassInfo healthClassInfo = new HealthClassInfo();
            // 养生大类ID
            healthClassInfo.setHealthId(healthClassEntity.getHealthId());
            // 编码
            healthClassInfo.setNumber(healthClassEntity.getNumber());
            // 养生大类名称
            healthClassInfo.setHealthName(healthClassEntity.getHealthName());
            // 背景图片URL
            ImagesEntity imagesEntity = healthClassEntity.getBgImage();
            if (imagesEntity != null) {
                String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                ImageInfo bgImage = new ImageInfo();
                bgImage.setId(imagesEntity.getId());
                bgImage.setName(imagesEntity.getFileName());
                bgImage.setSize(imagesEntity.getSize());
                bgImage.setUrl(imageUrl);
                healthClassInfo.setBgImage(bgImage);
            }
            // 内容介绍
            healthClassInfo.setContent(healthClassEntity.getContent());
            healthClassInfos.add(healthClassInfo);
        });
        return new PageImpl<>(healthClassInfos, pageRequest, healthClassEntities.getTotalElements());
    }

    @Override
    public HealthClassInfo getHealthClass(Integer healthClassId) {
        // 获取养生大类
        HealthClassEntity healthClassEntity = healthClassRepository.findByHealthId(healthClassId);
        String domainPort = platformUtils.getDomainPort();
        if (healthClassEntity != null) {
            HealthClassInfo healthClassInfo = new HealthClassInfo();
            // 养生大类ID
            healthClassInfo.setHealthId(healthClassEntity.getHealthId());
            // 编码
            healthClassInfo.setNumber(healthClassEntity.getNumber());
            // 养生大类名称
            healthClassInfo.setHealthName(healthClassEntity.getHealthName());
            // 背景图片
            ImagesEntity imagesEntity = healthClassEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = new ImageInfo();
                imageInfo.setId(imagesEntity.getId());
                imageInfo.setSize(imagesEntity.getSize());
                imageInfo.setName(imagesEntity.getFileName());
                String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                imageInfo.setUrl(imageUrl);
                healthClassInfo.setBgImage(imageInfo);
            }
            // 内容介绍
            healthClassInfo.setContent(healthClassEntity.getContent());
            return healthClassInfo;
        }
        return null;
    }

    @Override
    public HealthClassEntity saveHealthClass(HealthClassDTO healthClassDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        HealthClassEntity healthClassEntity = new HealthClassEntity();
        healthClassEntity.setBgImageId(healthClassDTO.getBgImageId());
        healthClassEntity.setContent(healthClassDTO.getContent());
        healthClassEntity.setHealthName(healthClassDTO.getHealthName());
        healthClassEntity.setNumber(healthClassDTO.getNumber());
        healthClassEntity.setCreateUserName(sysUserEntity.getUserName());
        return healthClassRepository.save(healthClassEntity);
    }

    @Override
    public HealthClassEntity modifyHealthClass(HealthClassDTO healthClassDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        HealthClassEntity healthClassEntity = new HealthClassEntity();
        healthClassEntity.setBgImageId(healthClassDTO.getBgImageId());
        healthClassEntity.setContent(healthClassDTO.getContent());
        healthClassEntity.setHealthName(healthClassDTO.getHealthName());
        healthClassEntity.setNumber(healthClassDTO.getNumber());
        healthClassEntity.setCreateUserName(sysUserEntity.getUserName());
        healthClassEntity.setHealthId(healthClassDTO.getHealthId());
        return healthClassRepository.save(healthClassEntity);
    }

    @Override
    public void deleteHealthClass(Long healthId, HttpSession session) {
        healthClassRepository.deleteById(healthId);
    }
}
