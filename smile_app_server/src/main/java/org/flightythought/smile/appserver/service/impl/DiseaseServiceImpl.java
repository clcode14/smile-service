package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.common.Constants;
import org.flightythought.smile.appserver.database.entity.DiseaseClassDetailEntity;
import org.flightythought.smile.appserver.database.entity.DiseaseClassEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.entity.UserFollowDiseaseEntity;
import org.flightythought.smile.appserver.database.repository.DiseaseClassDetailRepository;
import org.flightythought.smile.appserver.database.repository.DiseaseClassRepository;
import org.flightythought.smile.appserver.database.repository.UserFollowDiseaseRepository;
import org.flightythought.smile.appserver.dto.UserFollowDiseaseDTO;
import org.flightythought.smile.appserver.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DiseaseServiceImpl implements DiseaseService {
    @Autowired
    private DiseaseClassDetailRepository diseaseClassDetailRepository;
    @Autowired
    private UserFollowDiseaseRepository userFollowDiseaseRepository;
    @Autowired
    private DiseaseClassRepository diseaseClassRepository;


    @Override
    public List<DiseaseClassDetailEntity> getCommonDiseases() {
        return diseaseClassDetailRepository.findByType(Constants.DISEASE_COMMON);
    }

    @Override
    public void saveUserFollowDiseases(List<UserFollowDiseaseDTO> userFollowDiseases) {
        // 根据疾病小类ID获取疾病小类
        List<Integer> diseaseDetailIds = new ArrayList<>();
        userFollowDiseases.forEach(userFollowDiseaseDTO -> diseaseDetailIds.add(userFollowDiseaseDTO.getDiseaseDetailId()));
        List<DiseaseClassDetailEntity> diseaseClassEntities = diseaseClassDetailRepository.findByDiseaseDetailIdIn(diseaseDetailIds);
        // 获取当前登陆用户
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<UserFollowDiseaseEntity> userFollowDiseaseEntities = new ArrayList<>();
        diseaseClassEntities.forEach(diseaseClassDetailEntity -> {
            UserFollowDiseaseEntity userFollowDiseaseEntity = new UserFollowDiseaseEntity();
            userFollowDiseaseEntity.setUserId(userEntity.getId());
            userFollowDiseaseEntity.setDiseaseClassDetailEntity(diseaseClassDetailEntity);
            userFollowDiseaseEntities.add(userFollowDiseaseEntity);
        });
        userFollowDiseaseRepository.saveAll(userFollowDiseaseEntities);
    }

    @Override
    public List<DiseaseClassEntity> getAllThousandDisease() {
        // 获取当前登陆用户
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 获取用户关注的疾病
        List<UserFollowDiseaseEntity> userFollowDiseaseEntities = userFollowDiseaseRepository.findByUserId(userEntity.getId());
        // 获取疾病大类ID
        List<Integer> diseaseIds = new ArrayList<>();
        userFollowDiseaseEntities.forEach(userFollowDiseaseEntity -> diseaseIds.add(userFollowDiseaseEntity.getDiseaseClassDetailEntity().getDiseaseId()));
        // 获取疾病大类
        List<DiseaseClassEntity> result = diseaseClassRepository.findByDiseaseIdIn(diseaseIds);
        List<DiseaseClassEntity> diseaseClassEntities = diseaseClassRepository.findByDiseaseIdNotIn(diseaseIds);
        result.addAll(diseaseClassEntities);
        return result;
    }

    @Override
    public DiseaseClassDetailEntity getThousandDiseaseDetail(Integer diseaseDetailId) {
        return diseaseClassDetailRepository.findByDiseaseDetailId(diseaseDetailId);
    }
}
