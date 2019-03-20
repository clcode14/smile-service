package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.database.entity.DiseaseClassEntity;
import org.flightythought.smile.admin.database.repository.DiseaseClassRepository;
import org.flightythought.smile.admin.service.DiseaseConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/21 13:24
 */
@Service
public class DiseaseConfigServiceImpl implements DiseaseConfigService {
    @Autowired
    private DiseaseClassRepository diseaseClassRepository;

    @Override
    public Page<DiseaseClassEntity> getDiseaseClass(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        Page<DiseaseClassEntity> diseaseClassEntities = diseaseClassRepository.findAll(pageable);
        return diseaseClassEntities;
    }

    @Override
    public DiseaseClassEntity addDiseaseClass(DiseaseClassEntity diseaseClassEntity) {
        return diseaseClassRepository.save(diseaseClassEntity);
    }

    @Override
    public DiseaseClassEntity updateDiseaseClass(DiseaseClassEntity diseaseClassEntity) {
        DiseaseClassEntity oldEntity = diseaseClassRepository.findByDiseaseId(diseaseClassEntity.getDiseaseId());
        oldEntity.setNumber(diseaseClassEntity.getNumber());
        oldEntity.setDiseaseName(diseaseClassEntity.getDiseaseName());
        oldEntity.setUpdateUserName(diseaseClassEntity.getUpdateUserName());
        oldEntity = diseaseClassRepository.save(oldEntity);
        return oldEntity;
    }

    @Override
    public void deleteDiseaseClass(Integer id) {
        DiseaseClassEntity diseaseClassEntity = diseaseClassRepository.findByDiseaseId(id);
        if (diseaseClassEntity != null) {
            diseaseClassRepository.delete(diseaseClassEntity);
        }
    }
}
