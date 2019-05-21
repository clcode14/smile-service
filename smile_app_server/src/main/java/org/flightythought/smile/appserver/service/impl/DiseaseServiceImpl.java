package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.DiseaseClass;
import org.flightythought.smile.appserver.bean.DiseaseClassDetailSimple;
import org.flightythought.smile.appserver.common.Constants;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.DiseaseClassDetailRepository;
import org.flightythought.smile.appserver.database.repository.DiseaseClassRepository;
import org.flightythought.smile.appserver.database.repository.ImagesRepository;
import org.flightythought.smile.appserver.database.repository.UserFollowDiseaseRepository;
import org.flightythought.smile.appserver.dto.UserFollowDiseaseDTO;
import org.flightythought.smile.appserver.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    @Autowired
    private DiseaseClassDetailRepository diseaseClassDetailRepository;
    @Autowired
    private UserFollowDiseaseRepository userFollowDiseaseRepository;
    @Autowired
    private DiseaseClassRepository diseaseClassRepository;
    @Autowired
    private ImagesRepository imagesRepository;
    @Autowired
    private PlatformUtils platformUtils;


    @Override
    @Transactional
    public List<DiseaseClassDetailSimple> getCommonDiseases() {
        List<DiseaseClassDetailEntity> diseaseClassDetailEntities = diseaseClassDetailRepository.findByType(Constants.DISEASE_COMMON);
        return getDiseaseClassDetailSimple(diseaseClassDetailEntities);
    }

    @Override
    public List<DiseaseClassDetailSimple> getDiseaseClassDetailSimple(List<DiseaseClassDetailEntity> diseaseClassDetailEntities) {
        List<DiseaseClassDetailSimple> result = new ArrayList<>();
        String domain = platformUtils.getDomainPort();
        diseaseClassDetailEntities.forEach(diseaseClassDetailEntity -> {
            DiseaseClassDetailSimple diseaseClassDetailSimple = new DiseaseClassDetailSimple();
            // 疾病小类ID
            diseaseClassDetailSimple.setDiseaseDetailId(diseaseClassDetailEntity.getDiseaseDetailId());
            // 疾病大类ID
            diseaseClassDetailSimple.setDiseaseId(diseaseClassDetailEntity.getDiseaseId());
            // 疾病小类名称
            diseaseClassDetailSimple.setDiseaseClassDetailName(diseaseClassDetailEntity.getDiseaseDetailName());
            // 背景图片
            ImagesEntity bgImage = diseaseClassDetailEntity.getBgImage();
            if (bgImage != null) {
                diseaseClassDetailSimple.setBgImageUrl(platformUtils.getImageInfo(bgImage, domain).getUrl());
            }
            // 内容介绍
            diseaseClassDetailSimple.setContent(diseaseClassDetailEntity.getContent());
            // 图标URL
            ImagesEntity icon = diseaseClassDetailEntity.getIcon();
            if (icon != null) {
                diseaseClassDetailSimple.setIconUrl(platformUtils.getImageInfo(icon, domain).getUrl());
            }
            result.add(diseaseClassDetailSimple);
        });
        return result;
    }

    @Override
    public void saveUserFollowDiseases(List<UserFollowDiseaseDTO> userFollowDiseases) {
        // 根据疾病小类ID获取疾病小类
        List<Integer> diseaseDetailIds = new ArrayList<>();
        userFollowDiseases.forEach(userFollowDiseaseDTO -> diseaseDetailIds.add(userFollowDiseaseDTO.getDiseaseDetailId()));
        List<DiseaseClassDetailEntity> diseaseClassEntities = diseaseClassDetailRepository.findByDiseaseDetailIdIn(diseaseDetailIds);
        // 获取当前登陆用户
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 查看用户已关注的
        List<UserFollowDiseaseEntity> followDiseaseEntities = userFollowDiseaseRepository.findByUserId(userEntity.getId());
        Map<Integer, UserFollowDiseaseEntity> map = new HashMap<>();
        followDiseaseEntities.forEach(userFollowDiseaseEntity -> map.put(userFollowDiseaseEntity.getDiseaseDetailId(), userFollowDiseaseEntity));
        List<UserFollowDiseaseEntity> userFollowDiseaseEntities = new ArrayList<>();
        diseaseClassEntities.forEach(diseaseClassDetailEntity -> {
            if (map.get(diseaseClassDetailEntity.getDiseaseDetailId()) == null) {
                UserFollowDiseaseEntity userFollowDiseaseEntity = new UserFollowDiseaseEntity();
                userFollowDiseaseEntity.setUserId(userEntity.getId());
                userFollowDiseaseEntity.setDiseaseClassDetailEntity(diseaseClassDetailEntity);
                userFollowDiseaseEntities.add(userFollowDiseaseEntity);
            }
        });
        userFollowDiseaseRepository.saveAll(userFollowDiseaseEntities);
    }

    @Override
    @Transactional
    public List<DiseaseClass> getAllThousandDisease() {
        // 获取当前登陆用户
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 获取用户关注的疾病
        List<UserFollowDiseaseEntity> userFollowDiseaseEntities = userFollowDiseaseRepository.findByUserId(userEntity.getId());
        // 获取疾病大类ID
        List<Integer> diseaseIds = new ArrayList<>();
        userFollowDiseaseEntities.forEach(userFollowDiseaseEntity -> diseaseIds.add(userFollowDiseaseEntity.getDiseaseClassDetailEntity().getDiseaseId()));
        // 获取疾病大类
        List<DiseaseClassEntity> diseaseClassEntities = new ArrayList<>();
        List<DiseaseClassEntity> diseaseClassEntitiesIn = diseaseClassRepository.findByDiseaseIdIn(diseaseIds);
        if (diseaseClassEntitiesIn != null && diseaseClassEntitiesIn.size() > 0) {
            diseaseClassEntities.addAll(diseaseClassEntitiesIn);
            List<DiseaseClassEntity> diseaseClassEntitiesNotIn = diseaseClassRepository.findByDiseaseIdNotIn(diseaseIds);
            diseaseClassEntities.addAll(diseaseClassEntitiesNotIn);
        } else {
            diseaseClassEntities.addAll(diseaseClassRepository.findAll());
        }
        return getDiseaseClass(diseaseClassEntities);
    }

    @Override
    public List<DiseaseClass> getDiseaseClass(List<DiseaseClassEntity> diseaseClassEntities) {
        List<DiseaseClass> result = new ArrayList<>();
        if (diseaseClassEntities != null) {
            String domain = platformUtils.getDomainPort();
            diseaseClassEntities.forEach(diseaseClassEntity -> {
                DiseaseClass diseaseClass = new DiseaseClass();
                // 疾病大类ID
                diseaseClass.setDiseaseId(diseaseClassEntity.getDiseaseId());
                // 编码
                diseaseClass.setNumber(diseaseClassEntity.getNumber());
                // 疾病类目名称
                diseaseClass.setDiseaseName(diseaseClassEntity.getDiseaseName());
                // 疾病小类
                List<DiseaseClassDetailSimple> diseaseClassDetails = new ArrayList<>();
                List<DiseaseClassDetailEntity> diseaseClassDetailEntities = diseaseClassEntity.getDiseaseClassDetails();
                if (diseaseClassDetailEntities != null) {
                    diseaseClassDetailEntities.forEach(diseaseClassDetailEntity -> {
                        DiseaseClassDetailSimple diseaseClassDetailSimple = new DiseaseClassDetailSimple();
                        // 疾病小类ID
                        diseaseClassDetailSimple.setDiseaseDetailId(diseaseClassDetailEntity.getDiseaseDetailId());
                        // 疾病大类ID
                        diseaseClassDetailSimple.setDiseaseId(diseaseClassDetailEntity.getDiseaseId());
                        // 疾病大类名称
                        diseaseClassDetailSimple.setDiseaseClassName(diseaseClassEntity.getDiseaseName());
                        // 疾病小类名称
                        diseaseClassDetailSimple.setDiseaseClassDetailName(diseaseClassDetailEntity.getDiseaseDetailName());
                        // 背景图片URL
                        ImagesEntity bgImage = diseaseClassDetailEntity.getBgImage();
                        if (bgImage != null) {
                            diseaseClassDetailSimple.setBgImageUrl(platformUtils.getImageInfo(bgImage, domain).getUrl());
                        }
                        // 内容介绍
                        diseaseClassDetailSimple.setContent(diseaseClassDetailEntity.getContent());
                        // 图标URL
                        ImagesEntity icon = diseaseClassDetailEntity.getIcon();
                        if (icon != null) {
                            diseaseClassDetailSimple.setIconUrl(platformUtils.getImageInfo(icon, domain).getUrl());
                        }
                        diseaseClassDetails.add(diseaseClassDetailSimple);
                    });
                }
                diseaseClass.setDiseaseClassDetails(diseaseClassDetails);
                result.add(diseaseClass);
            });
        }
        return result;
    }

    @Override
    public DiseaseClassDetailSimple getThousandDiseaseDetail(Integer diseaseDetailId) throws FlightyThoughtException {
        // 根据疾病小类ID获取疾病小类
        DiseaseClassDetailEntity diseaseClassDetailEntity = diseaseClassDetailRepository.findByDiseaseDetailId(diseaseDetailId);
        if (diseaseClassDetailEntity == null) {
            throw new FlightyThoughtException("DiseaseDetailId:" + diseaseDetailId + "没有对应的疾病小类");
        }
        // 获取疾病小类对应的疾病大类
        DiseaseClassEntity diseaseClassEntity = diseaseClassRepository.findByDiseaseId(diseaseClassDetailEntity.getDiseaseId());
        if (diseaseClassEntity == null) {
            throw new FlightyThoughtException("数据有误！DiseaseDetailId:" + diseaseDetailId + "没有找到其对应的疾病大类");
        }
        // 疾病小类简单对象
        DiseaseClassDetailSimple result = new DiseaseClassDetailSimple();
        // 疾病小类ID
        result.setDiseaseDetailId(diseaseClassDetailEntity.getDiseaseDetailId());
        // 疾病大类ID
        result.setDiseaseId(diseaseClassEntity.getDiseaseId());
        // 疾病大类名称
        result.setDiseaseClassName(diseaseClassEntity.getDiseaseName());
        // 疾病小类名称
        result.setDiseaseClassDetailName(diseaseClassDetailEntity.getDiseaseDetailName());
        // 获取背景图片
        String domainPort = platformUtils.getDomainPort();
        if (diseaseClassDetailEntity.getBgImagesId() != null) {
            ImagesEntity bgImage = imagesRepository.findById(diseaseClassDetailEntity.getBgImagesId());
            if (bgImage != null) {
                // 背景图片URL
                String bgImageUrl = platformUtils.getImageInfo(bgImage, domainPort).getUrl();
                result.setBgImageUrl(bgImageUrl);
            }
        }

        // 内容介绍
        result.setContent(diseaseClassDetailEntity.getContent());
        // 图标ICON
        if (diseaseClassDetailEntity.getIconId() != null) {
            ImagesEntity icon = imagesRepository.findById(diseaseClassDetailEntity.getIconId());
            if (icon != null) {
                // 图标URL
                String iconUrl = platformUtils.getImageInfo(icon, domainPort).getUrl();
                result.setIconUrl(iconUrl);
            }
        }
        return result;
    }
}
