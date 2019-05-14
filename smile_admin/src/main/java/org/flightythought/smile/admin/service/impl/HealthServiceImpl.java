package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.HealthClassInfo;
import org.flightythought.smile.admin.bean.HealthWay;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.bean.SolutionInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.HealthRepository;
import org.flightythought.smile.admin.database.repository.HealthToSolutionRepository;
import org.flightythought.smile.admin.database.repository.HealthWayRepository;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.flightythought.smile.admin.dto.HealthClassDTO;
import org.flightythought.smile.admin.dto.HealthWayDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.CommonService;
import org.flightythought.smile.admin.service.HealthService;
import org.flightythought.smile.admin.service.SolutionService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class HealthServiceImpl implements HealthService {

    @Autowired
    private HealthRepository healthRepository;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private SolutionService solutionService;
    @Autowired
    private HealthToSolutionRepository healthToSolutionRepository;
    @Autowired
    private HealthWayRepository healthWayRepository;
    @Autowired
    private CommonService commonService;

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
        Page<HealthEntity> healthClassEntities = healthRepository.findAll(pageRequest);
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
                ImageInfo bgImage = platformUtils.getImageInfo(imagesEntity, domainPort);
                healthClassInfo.setBgImage(bgImage);
            }
            // 内容介绍
            healthClassInfo.setContent(healthClassEntity.getContent());
            // 关联的解决方案
            List<SolutionInfo> solutionInfos = healthClassEntity.getSolutions().stream().map(solutionEntity -> solutionService.getSolutionInfo(solutionEntity)).collect(Collectors.toList());
            healthClassInfo.setSolutions(solutionInfos);
            healthClassInfos.add(healthClassInfo);
        });
        return new PageImpl<>(healthClassInfos, pageRequest, healthClassEntities.getTotalElements());
    }

    @Override
    public HealthClassInfo getHealthClass(Integer healthClassId) {
        // 获取养生大类
        HealthEntity healthEntity = healthRepository.findByHealthId(healthClassId);
        String domainPort = platformUtils.getDomainPort();
        if (healthEntity != null) {
            HealthClassInfo healthClassInfo = new HealthClassInfo();
            // 养生大类ID
            healthClassInfo.setHealthId(healthEntity.getHealthId());
            // 编码
            healthClassInfo.setNumber(healthEntity.getNumber());
            // 养生大类名称
            healthClassInfo.setHealthName(healthEntity.getHealthName());
            // 背景图片
            ImagesEntity imagesEntity = healthEntity.getBgImage();
            if (imagesEntity != null) {
                ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domainPort);
                healthClassInfo.setBgImage(imageInfo);
            }
            // 内容介绍
            healthClassInfo.setContent(healthEntity.getContent());
            // 关联的解决方案
            List<SolutionInfo> solutionInfos = healthEntity.getSolutions().stream().map(solutionEntity -> solutionService.getSolutionInfo(solutionEntity)).collect(Collectors.toList());
            healthClassInfo.setSolutions(solutionInfos);
            return healthClassInfo;
        }
        return null;
    }

    @Override
    public HealthEntity saveHealthClass(HealthClassDTO healthClassDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        HealthEntity healthEntity = new HealthEntity();
        // 背景图片ID
        healthEntity.setBgImageId(healthClassDTO.getBgImageId());
        // 内容描述
        healthEntity.setContent(healthClassDTO.getContent());
        // 养生名称
        healthEntity.setHealthName(healthClassDTO.getHealthName());
        // 编码
        healthEntity.setNumber(healthClassDTO.getNumber());
        // 创建者
        healthEntity.setCreateUserName(sysUserEntity.getUserName());
        healthEntity = healthRepository.save(healthEntity);
        // 保存关联的解决方案
        List<Integer> solutionIds = healthClassDTO.getSolutionIds();
        if (solutionIds != null && solutionIds.size() > 0) {
            HealthEntity finalHealthEntity = healthEntity;
            List<HealthToSolutionEntity> healthToSolutionEntities = solutionIds.stream().map(integer -> {
                HealthToSolutionEntity healthToSolutionEntity = new HealthToSolutionEntity();
                healthToSolutionEntity.setHealthId(finalHealthEntity.getHealthId());
                healthToSolutionEntity.setSolutionId(integer);
                return healthToSolutionEntity;
            }).collect(Collectors.toList());
            healthToSolutionRepository.saveAll(healthToSolutionEntities);
        }
        return healthEntity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public HealthEntity modifyHealthClass(HealthClassDTO healthClassDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        HealthEntity healthEntity = healthRepository.findByHealthId(healthClassDTO.getHealthId());
        if (healthEntity == null) {
            throw new FlightyThoughtException("没有找到对应的养生");
        }
        if (!healthEntity.getBgImageId().equals(healthClassDTO.getBgImageId())) {
            // 删除原先背景图片
            if (healthEntity.getBgImageId() != null) {
                commonService.deleteImage(healthEntity.getBgImageId());
            }
        }
        // 背景图片
        healthEntity.setBgImageId(healthClassDTO.getBgImageId());
        // 内容描述
        healthEntity.setContent(healthClassDTO.getContent());
        // 养生名称
        healthEntity.setHealthName(healthClassDTO.getHealthName());
        // 编码
        healthEntity.setNumber(healthClassDTO.getNumber());
        // 更新者
        healthEntity.setUpdateUserName(sysUserEntity.getUserName());
        // 删除关联的解决方案
        healthToSolutionRepository.deleteAllByHealthId(healthClassDTO.getHealthId());
        // 保存关联的解决方案
        List<Integer> solutionIds = healthClassDTO.getSolutionIds();
        if (solutionIds != null && solutionIds.size() > 0) {
            List<HealthToSolutionEntity> healthToSolutionEntities = solutionIds.stream().map(integer -> {
                HealthToSolutionEntity healthToSolutionEntity = new HealthToSolutionEntity();
                healthToSolutionEntity.setHealthId(healthEntity.getHealthId());
                healthToSolutionEntity.setSolutionId(integer);
                return healthToSolutionEntity;
            }).collect(Collectors.toList());
            healthToSolutionRepository.saveAll(healthToSolutionEntities);
        }
        return healthRepository.save(healthEntity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteHealthClass(Integer healthId, HttpSession session) {
        HealthEntity healthEntity = healthRepository.findByHealthId(healthId);
        if (healthEntity != null) {
            if (healthEntity.getBgImageId() != null) {
                commonService.deleteImage(healthEntity.getBgImageId());
            }
            healthRepository.deleteByHealthId(healthId);
        }
    }

    @Override
    public HealthWayEntity addHealthWay(HealthWayDTO healthWayDTO) {
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        HealthWayEntity healthWayEntity = new HealthWayEntity();
        // 养生方式名称
        healthWayEntity.setWayName(healthWayDTO.getWayName());
        // 背景图片
        healthWayEntity.setBgImage(healthWayDTO.getBgImageId());
        // 编码
        healthWayEntity.setNumber(healthWayDTO.getNumber());
        // 内容
        healthWayEntity.setContent(healthWayDTO.getContent());
        // 音乐链接
        healthWayEntity.setMusicUrl(healthWayDTO.getMusicUrl());
        // 类型
        healthWayEntity.setType(healthWayDTO.getType());
        // 创建者
        healthWayEntity.setCreateUserName(userEntity.getLoginName());
        return healthWayRepository.save(healthWayEntity);
    }

    @Override
    public Page<HealthWay> getHealthWays(Map<String, String> params) {
        String domainPort = platformUtils.getDomainPort();
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");

        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        Pageable pageable = PageRequest.of(Integer.parseInt(pageNumber) - 1, Integer.parseInt(pageSize));
        Page<HealthWayEntity> healthWayEntities = healthWayRepository.findAll(pageable);
        List<HealthWay> healthWays = healthWayEntities.stream().map(healthWayEntity -> {
            HealthWay healthWay = new HealthWay();
            BeanUtils.copyProperties(healthWayEntity, healthWay);
            // 背景图
            ImagesEntity imagesEntity = healthWayEntity.getImages();
            if (imagesEntity != null) {
                ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domainPort);
                healthWay.setBgImage(imageInfo);
            }
            return healthWay;
        }).collect(Collectors.toList());
        return new PageImpl<>(healthWays, pageable, healthWayEntities.getTotalElements());
    }

    @Override
    public HealthWayEntity modifyHealthWay(HealthWayDTO healthWayDTO) {
        // 获取养生方式
        HealthWayEntity healthWayEntity = healthWayRepository.findByHealthWayId(healthWayDTO.getHealthWayId());
        if (healthWayEntity != null) {
            SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
            // 养生方式名称
            healthWayEntity.setWayName(healthWayDTO.getWayName());
            if (!healthWayEntity.getBgImage().equals(healthWayDTO.getBgImageId())) {
                // 删除原来的背景图片
                commonService.deleteImage(healthWayEntity.getBgImage());
            }
            // 背景图片
            healthWayEntity.setBgImage(healthWayDTO.getBgImageId());
            // 编码
            healthWayEntity.setNumber(healthWayDTO.getNumber());
            // 内容
            healthWayEntity.setContent(healthWayDTO.getContent());
            // 音乐链接
            healthWayEntity.setMusicUrl(healthWayDTO.getMusicUrl());
            // 类型
            healthWayEntity.setType(healthWayDTO.getType());
            // 修改者
            healthWayEntity.setUpdateUserName(userEntity.getLoginName());
            healthWayRepository.save(healthWayEntity);
            return healthWayEntity;
        }
        return null;
    }

    @Override
    public void deleteHealthWay(Integer healthWayId) {
        HealthWayEntity healthWayEntity = healthWayRepository.findByHealthWayId(healthWayId);
        if (healthWayEntity != null) {
            // 删除背景图片
            if (healthWayEntity.getBgImage() != null) {
                commonService.deleteImage(healthWayEntity.getBgImage());
            }
            healthWayRepository.delete(healthWayEntity);
        }
    }
}
