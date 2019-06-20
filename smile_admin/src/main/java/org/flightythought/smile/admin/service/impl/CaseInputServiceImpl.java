package org.flightythought.smile.admin.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.database.entity.DiseaseClassDetailEntity;
import org.flightythought.smile.admin.database.entity.HealthEntity;
import org.flightythought.smile.admin.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.admin.database.entity.HealthResultEntity;
import org.flightythought.smile.admin.database.entity.SolutionEntity;
import org.flightythought.smile.admin.database.repository.DiseaseClassDetailRepository;
import org.flightythought.smile.admin.database.repository.HealthNormTypeRepository;
import org.flightythought.smile.admin.database.repository.HealthRepository;
import org.flightythought.smile.admin.database.repository.HealthResultRepository;
import org.flightythought.smile.admin.database.repository.SolutionRepository;
import org.flightythought.smile.admin.service.CaseInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CaseInputServiceImpl implements CaseInputService {

    @Autowired
    private DiseaseClassDetailRepository diseaseClassDetailRepository;

    @Autowired
    private HealthResultRepository healthResultRepository;

    @Autowired
    private HealthNormTypeRepository healthNormTypeRepository;

    @Autowired
    private HealthRepository healthRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Override
    public List<SelectItemOption> getDisease() {
        List<DiseaseClassDetailEntity> entities = diseaseClassDetailRepository.findAll();
        return entities.stream().map(disease -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(disease.getDiseaseDetailId() + "");
            option.setValue(disease.getDiseaseDetailName());
            return option;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SelectItemOption> getHealthResult() {
        List<HealthResultEntity> healthResultEntities = healthResultRepository.findAll();
        return healthResultEntities.stream().map(healthResult -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(healthResult.getId() + "");
            option.setValue(healthResult.getName());
            return option;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SelectItemOption> getSolution() {
        List<SolutionEntity> entities = solutionRepository.findAll();
        return entities.stream().map(solution -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(solution.getId() + "");
            option.setValue(solution.getTitle());
            return option;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SelectItemOption> getHealthNormType() {
        List<HealthNormTypeEntity> normTypeEntities = healthNormTypeRepository.findAll();
        return normTypeEntities.stream().map(healthNormType -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(healthNormType.getNormTypeId() + "");
            option.setValue(healthNormType.getNormName());
            return option;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SelectItemOption> getHealth() {
        List<HealthEntity> entities = healthRepository.findAll();
        return entities.stream().map(healthNormType -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(healthNormType.getHealthId() + "");
            option.setValue(healthNormType.getHealthName());
            return option;
        }).collect(Collectors.toList());
    }

}
