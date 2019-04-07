package org.flightythought.smile.appserver.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.appserver.bean.DiseaseReason;
import org.flightythought.smile.appserver.bean.SolutionSimple;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.DiseaseReasonEntity;
import org.flightythought.smile.appserver.database.entity.DiseaseReasonType;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.SolutionEntity;
import org.flightythought.smile.appserver.database.repository.DiseaseReasonRepository;
import org.flightythought.smile.appserver.database.repository.DiseaseReasonTypeRepository;
import org.flightythought.smile.appserver.dto.AboutDiseaseDetailQueryDTO;
import org.flightythought.smile.appserver.service.DiseaseReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class DiseaseReasonServiceImpl implements DiseaseReasonService {
    @Autowired
    private DiseaseReasonRepository diseaseReasonRepository;
    @Autowired
    private DiseaseReasonTypeRepository diseaseReasonTypeRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    public Page<DiseaseReasonEntity> getDiseaseReason(AboutDiseaseDetailQueryDTO diseaseDetailQueryDTO) throws FlightyThoughtException {
        // 疾病小类ID
        Integer diseaseDetailId = diseaseDetailQueryDTO.getDiseaseDetailId();
        if (diseaseDetailId == null || diseaseDetailId == 0) {
            throw new FlightyThoughtException("diseaseDetailId不能为NULL");
        }
        // 判断有无传递pageSize和pageNumber
        Integer pageSize = diseaseDetailQueryDTO.getPageSize();
        Integer pageNumber = diseaseDetailQueryDTO.getPageNumber();
        boolean isPage = (pageSize != null && pageSize != 0) && (pageNumber != null && pageNumber != 0);
        // 类型 1：中医， 2：西医
        String type = diseaseDetailQueryDTO.getType();
        if (isPage) {
            PageRequest pageRequest = PageRequest.of(diseaseDetailQueryDTO.getPageNumber() - 1, diseaseDetailQueryDTO.getPageSize());
            // 获取疾病
            if (StringUtils.isNotBlank(type)) {
                return diseaseReasonRepository.findByDiseaseDetailIdAndType(diseaseDetailId, type, pageRequest);
            } else {
                return diseaseReasonRepository.findByDiseaseDetailId(diseaseDetailId, pageRequest);
            }
        } else {
            List<DiseaseReasonEntity> diseaseReasonEntities;
            if (StringUtils.isNotBlank(type)) {
                diseaseReasonEntities = diseaseReasonRepository.findByDiseaseDetailIdAndType(diseaseDetailId, type);
            } else {
                diseaseReasonEntities = diseaseReasonRepository.findByDiseaseDetailId(diseaseDetailId);
            }
            if (diseaseReasonEntities != null) {
                Pageable pageable = PageRequest.of(0, diseaseReasonEntities.size());
                return new PageImpl<>(diseaseReasonEntities, pageable, diseaseReasonEntities.size());
            }
        }
        return null;
    }

    @Override
    public List<Map<String, String>> getDiseaseTypes() {
        List<DiseaseReasonType> diseaseReasonTypes = diseaseReasonTypeRepository.findAll();
        List<Map<String, String>> result = new ArrayList<>();
        diseaseReasonTypes.forEach(diseaseReasonType -> {
            Map<String, String> map = new HashMap<>();
            map.put("type", diseaseReasonType.getTypeName());
            map.put("value", diseaseReasonType.getTypeId().toString());
            result.add(map);
        });
        return result;
    }

    @Override
    @Transactional
    public DiseaseReason getDiseaseReasonInfo(String id) throws FlightyThoughtException {
        // 根据疾病原因ID获取疾病原因
        Integer reasonId = null;
        try {
            reasonId = Integer.parseInt(id);
        } catch (NumberFormatException e) {
            throw new FlightyThoughtException("所传疾病ID无效", e);
        }
        DiseaseReasonEntity diseaseReasonEntity = diseaseReasonRepository.findById(reasonId);
        if (diseaseReasonEntity != null) {
            DiseaseReason diseaseReason = new DiseaseReason();
            // 编码
            diseaseReason.setNumber(diseaseReasonEntity.getNumber());
            // 标题
            diseaseReason.setTitle(diseaseReasonEntity.getTitle());
            // 内容
            diseaseReason.setContent(diseaseReasonEntity.getContent());
            // 疾病原因ID
            diseaseReason.setReasonId(diseaseReasonEntity.getId());
            // 类型ID
            diseaseReason.setTypeId(diseaseReasonEntity.getType());
            // 获取疾病原因对应的解决方案
            List<SolutionEntity> solutionEntities = diseaseReasonEntity.getSolutions();
            List<SolutionSimple> solutionSimples = new ArrayList<>();
            String domainPort = platformUtils.getDomainPort();
            solutionEntities.forEach(solutionEntity -> {
                SolutionSimple solutionSimple = new SolutionSimple();
                // 获取解决方案对应的配图
                List<ImagesEntity> solutionImagesEntities = solutionEntity.getImages();
                List<String> imageUrls = new ArrayList<>();
                solutionImagesEntities.forEach(imagesEntity -> {
                    String imageUrl = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                    imageUrls.add(imageUrl);
                });
                // 解决方案配图
                solutionSimple.setImageUrls(imageUrls);
                // 康复人数
                solutionSimple.setRecoverNumber(solutionEntity.getRecoverNumber());
                // 解决方案ID
                solutionSimple.setSolutionId(solutionEntity.getId());
                // 解决方案标题
                solutionSimple.setTitle(solutionEntity.getTitle());
                // 增加解决方案简单对象
                solutionSimples.add(solutionSimple);
            });
            diseaseReason.setSolutions(solutionSimples);
            return diseaseReason;
        }
        return null;
    }
}
