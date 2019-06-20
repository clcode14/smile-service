package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.DiseaseClass;
import org.flightythought.smile.admin.bean.DiseaseClassDetailInfo;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.DiseaseClassDetailEntity;
import org.flightythought.smile.admin.database.entity.DiseaseClassEntity;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.DiseaseClassDetailRepository;
import org.flightythought.smile.admin.database.repository.DiseaseClassRepository;
import org.flightythought.smile.admin.dto.DiseaseClassDetailDTO;
import org.flightythought.smile.admin.service.CommonService;
import org.flightythought.smile.admin.service.DiseaseDetailConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class DiseaseDetailConfigServiceImpl implements DiseaseDetailConfigService {

    @Autowired
    private DiseaseClassRepository diseaseClassRepository;
    @Autowired
    private DiseaseClassDetailRepository diseaseClassDetailRepository;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private CommonService commonService;
    @Value("${html}")
    private String html;

    @Override
    public List<DiseaseClass> getDiseaseClass() {
        List<DiseaseClassEntity> diseaseClassEntities = diseaseClassRepository.findAll();
        List<DiseaseClass> result = diseaseClassEntities.stream().map(diseaseClassEntity -> {
            DiseaseClass diseaseClass = new DiseaseClass();
            BeanUtils.copyProperties(diseaseClassEntity, diseaseClass);
            return diseaseClass;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public Page<DiseaseClassDetailInfo> getDiseaseDetails(int diseaseId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<DiseaseClassDetailEntity> diseaseClassDetailEntities = diseaseClassDetailRepository.findByDiseaseId(diseaseId, pageable);
        return getDiseaseClassDetailInfo(diseaseClassDetailEntities);
    }

    @Override
    public Page<DiseaseClassDetailInfo> getDiseaseClassDetailInfo(Page<DiseaseClassDetailEntity> diseaseClassDetailEntities) {
        if (diseaseClassDetailEntities != null && diseaseClassDetailEntities.getContent().size() > 0) {
            List<DiseaseClassDetailInfo> diseaseClassDetailInfos = new ArrayList<>();
            Pageable pageable = diseaseClassDetailEntities.getPageable();
            long total = diseaseClassDetailEntities.getTotalElements();
            List<DiseaseClassDetailEntity> diseaseClassDetailEntityList = diseaseClassDetailEntities.getContent();
            String domainPort = platformUtils.getDomainPort();
            List<Integer> diseaseIds = diseaseClassDetailEntityList.stream().map(DiseaseClassDetailEntity::getDiseaseId).collect(Collectors.toList());
            List<DiseaseClassEntity> diseaseClassEntities = diseaseClassRepository.findByDiseaseIdIn(diseaseIds);
            Map<Integer, DiseaseClassEntity> diseaseClassEntityMap = diseaseClassEntities.stream().collect(Collectors.toMap(DiseaseClassEntity::getDiseaseId, Function.identity()));
            // 获取疾病大类
            diseaseClassDetailEntityList.forEach(diseaseClassDetailEntity -> {
                DiseaseClassDetailInfo diseaseClassDetailInfo = new DiseaseClassDetailInfo();
                // 疾病小类ID
                diseaseClassDetailInfo.setDiseaseDetailId(diseaseClassDetailEntity.getDiseaseDetailId());
                // 疾病大类ID
                diseaseClassDetailInfo.setDiseaseId(diseaseClassDetailEntity.getDiseaseId());
                // 编码
                diseaseClassDetailInfo.setNumber(diseaseClassDetailEntity.getNumber());
                // 类型
                diseaseClassDetailInfo.setType(diseaseClassDetailEntity.getType());
                // 疾病小类名称
                diseaseClassDetailInfo.setDiseaseDetailName(diseaseClassDetailEntity.getDiseaseDetailName());
                // 背景图片
                ImagesEntity bgImageEntity = diseaseClassDetailEntity.getBgImage();
                if (bgImageEntity != null) {
                    diseaseClassDetailInfo.setBgImages(platformUtils.getImageInfo(bgImageEntity, domainPort));
                }
                // 疾病小类描述
                diseaseClassDetailInfo.setContent(diseaseClassDetailEntity.getContent());
                // 疾病小类图标
                ImagesEntity iconEntity = diseaseClassDetailEntity.getIcon();
                if (iconEntity != null) {
                    diseaseClassDetailInfo.setIcon(platformUtils.getImageInfo(iconEntity, domainPort));
                }
                // 疾病大类名称
                DiseaseClassEntity diseaseClassEntity = diseaseClassEntityMap.get(diseaseClassDetailEntity.getDiseaseId());
                if (diseaseClassEntity != null) {
                    diseaseClassDetailInfo.setDiseaseName(diseaseClassEntity.getDiseaseName());
                }
                // 创建者
                diseaseClassDetailInfo.setCreateUserName(diseaseClassDetailEntity.getCreateUserName());
                // 创建时间
                diseaseClassDetailInfo.setCreateTime(diseaseClassDetailEntity.getCreateTime());
                // 修改者
                diseaseClassDetailInfo.setUpdateUserName(diseaseClassDetailEntity.getUpdateUserName());
                // 修改时间
                diseaseClassDetailInfo.setUpdateTime(diseaseClassDetailEntity.getUpdateTime());
                diseaseClassDetailInfos.add(diseaseClassDetailInfo);
            });
            return new PageImpl<>(diseaseClassDetailInfos, pageable, total);
        }
        return null;
    }

    @Override
    public DiseaseClassDetailEntity saveDiseaseClassDetail(SysUserEntity sysUserEntity, DiseaseClassDetailDTO diseaseClassDetailDTO) {
        DiseaseClassDetailEntity diseaseClassDetailEntity = new DiseaseClassDetailEntity();
        // 疾病小类名称
        diseaseClassDetailEntity.setDiseaseDetailName(diseaseClassDetailDTO.getDiseaseDetailName());
        // 编码
        diseaseClassDetailEntity.setNumber(diseaseClassDetailDTO.getNumber());
        // 疾病大类ID
        diseaseClassDetailEntity.setDiseaseId(diseaseClassDetailDTO.getDiseaseId());
        // 疾病小类类型
        diseaseClassDetailEntity.setType(diseaseClassDetailDTO.getType());
        // 背景图片
        if (diseaseClassDetailDTO.getBgImages() != null) {
            diseaseClassDetailEntity.setBgImagesId(diseaseClassDetailDTO.getBgImages().getImageId());
        }
        // 疾病小类图标
        if (diseaseClassDetailDTO.getIcon() != null) {
            diseaseClassDetailEntity.setIconId(diseaseClassDetailDTO.getIcon().getImageId());
        }
        // 疾病小类描述
        if (StringUtils.isNotBlank(diseaseClassDetailDTO.getContent())) {
            diseaseClassDetailEntity.setContent(html + diseaseClassDetailDTO.getContent());
        }
        // 创建者
        diseaseClassDetailEntity.setCreateUserName(sysUserEntity.getLoginName());
        // 创建时间
        diseaseClassDetailEntity.setCreateTime(LocalDateTime.now());

        DiseaseClassDetailInfo result = new DiseaseClassDetailInfo();
        BeanUtils.copyProperties(diseaseClassDetailEntity, result);

        return diseaseClassDetailRepository.save(diseaseClassDetailEntity);
    }

    @Override
    public DiseaseClassDetailEntity updateDiseaseClassDetail(SysUserEntity sysUserEntity, DiseaseClassDetailDTO diseaseClassDetailDTO) {
        int diseaseDetailId = diseaseClassDetailDTO.getDiseaseDetailId();
        DiseaseClassDetailEntity diseaseClassDetailEntity = diseaseClassDetailRepository.findByDiseaseDetailId(diseaseDetailId);
        // 疾病小类名称
        diseaseClassDetailEntity.setDiseaseDetailName(diseaseClassDetailDTO.getDiseaseDetailName());
        // 编码
        diseaseClassDetailEntity.setNumber(diseaseClassDetailDTO.getNumber());
        // 疾病大类ID
        diseaseClassDetailEntity.setDiseaseId(diseaseClassDetailDTO.getDiseaseId());
        // 创建者
        diseaseClassDetailEntity.setCreateUserName(sysUserEntity.getLoginName());
        // 创建时间
        diseaseClassDetailEntity.setCreateTime(LocalDateTime.now());
        // 疾病小类类型
        diseaseClassDetailEntity.setType(diseaseClassDetailDTO.getType());
        // 背景图片
        if (diseaseClassDetailDTO.getBgImages() != null) {
            if (!diseaseClassDetailDTO.getBgImages().getImageId().equals(diseaseClassDetailEntity.getBgImagesId())) {
                if (diseaseClassDetailEntity.getBgImagesId() != null) {
                    // 删除原来背景图
                    commonService.deleteImage(diseaseClassDetailEntity.getBgImagesId());
                }
            }
            diseaseClassDetailEntity.setBgImagesId(diseaseClassDetailDTO.getBgImages().getImageId());
        }
        // 疾病小类图标
        if (diseaseClassDetailDTO.getIcon() != null) {
            if (!diseaseClassDetailDTO.getIcon().getImageId().equals(diseaseClassDetailEntity.getIconId())) {
                if (diseaseClassDetailEntity.getIconId() != null) {
                    // 删除原来的图标
                    commonService.deleteImage(diseaseClassDetailEntity.getIconId());
                }
            }
            diseaseClassDetailEntity.setIconId(diseaseClassDetailDTO.getIcon().getImageId());
        }
        // 疾病小类描述
        if (StringUtils.isNotBlank(diseaseClassDetailDTO.getContent())) {
            diseaseClassDetailEntity.setContent(html + diseaseClassDetailDTO.getContent());
        }
        // 修改者
        diseaseClassDetailEntity.setUpdateUserName(sysUserEntity.getLoginName());
        // 修改时间
        diseaseClassDetailEntity.setUpdateTime(LocalDateTime.now());
        return diseaseClassDetailRepository.save(diseaseClassDetailEntity);
    }

    @Override
    public void deleteDiseaseDetail(Integer id) {
        DiseaseClassDetailEntity diseaseClassDetailEntity = diseaseClassDetailRepository.findByDiseaseDetailId(id);
        if (diseaseClassDetailEntity != null) {
            // 删除ICON图片
            if (diseaseClassDetailEntity.getIconId() != null) {
                commonService.deleteImage(diseaseClassDetailEntity.getIconId());
            }
            // 删除背景图片
            if (diseaseClassDetailEntity.getBgImagesId() != null) {
                commonService.deleteImage(diseaseClassDetailEntity.getBgImagesId());
            }
            diseaseClassDetailRepository.delete(diseaseClassDetailEntity);
        }
    }
}
