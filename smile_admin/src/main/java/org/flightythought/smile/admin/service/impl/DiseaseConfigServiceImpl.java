package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.bean.DiseaseClass;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.DiseaseClassEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.DiseaseClassRepository;
import org.flightythought.smile.admin.dto.DiseaseClassDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.DiseaseConfigService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.stream.Collectors;

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
    public Page<DiseaseClass> getDiseaseClass(int pageNumber, int pageSize) {
        if (pageNumber == 0 || pageSize == 0) {
            throw new FlightyThoughtException("pageNumber, pageSize参数传递错误");
        }
        // 分页查询
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize);
        // 获取全部疾病大类
        Page<DiseaseClassEntity> diseaseClassEntities = diseaseClassRepository.findAll(pageable);
        // 获取总数
        long total = diseaseClassEntities.getTotalElements();
        // 转换返回对象
        List<DiseaseClass> diseaseClasses = diseaseClassEntities.getContent().stream().map(diseaseClassEntity -> {
            DiseaseClass diseaseClass = new DiseaseClass();
            BeanUtils.copyProperties(diseaseClassEntity, diseaseClass);
            return diseaseClass;
        }).collect(Collectors.toList());
        return new PageImpl<>(diseaseClasses, pageable, total);
    }

    @Override
    public DiseaseClassEntity addDiseaseClass(DiseaseClassDTO diseaseClassDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        DiseaseClassEntity diseaseClassEntity = new DiseaseClassEntity();
        diseaseClassEntity.setNumber(diseaseClassDTO.getNumber());
        diseaseClassEntity.setDiseaseName(diseaseClassDTO.getDiseaseName());
        diseaseClassEntity.setCreateUserName(sysUserEntity.getLoginName());
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
    @Transactional
    public void deleteDiseaseClass(Integer id) {
        DiseaseClassEntity diseaseClassEntity = diseaseClassRepository.findByDiseaseId(id);
        if (diseaseClassEntity != null) {
            diseaseClassRepository.delete(diseaseClassEntity);
        }
    }
}
