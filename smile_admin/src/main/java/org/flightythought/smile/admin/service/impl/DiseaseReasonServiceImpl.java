package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.DiseaseReasonEntity;
import org.flightythought.smile.admin.database.entity.DiseaseReasonToSolutionEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.DiseaseReasonRepository;
import org.flightythought.smile.admin.database.repository.DiseaseReasonToSolutionRepository;
import org.flightythought.smile.admin.dto.DiseaseReasonDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.DiseaseReasonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
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
 * @ClassName DiseaseReasonServiceImpl
 * @CreateTime 2019/4/2 0:36
 * @Description: TODO
 */
@Service
public class DiseaseReasonServiceImpl implements DiseaseReasonService {

    @Autowired
    private DiseaseReasonRepository diseaseReasonRepository;
    @Autowired
    private DiseaseReasonToSolutionRepository diseaseReasonToSolutionRepository;

    @Override
    @Transactional
    public DiseaseReasonEntity saveDiseaseReason(DiseaseReasonDTO diseaseReasonDTO, HttpSession session) throws FlightyThoughtException {
        // 获取当前登录用户
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        DiseaseReasonEntity diseaseReasonEntity;
        Integer diseaseReasonId = diseaseReasonDTO.getId();
        if (diseaseReasonId == null || diseaseReasonId == 0) {
            // 新增疾病原因
            diseaseReasonEntity = new DiseaseReasonEntity();
            diseaseReasonEntity.setCreateUserName(sysUserEntity.getLoginName());
            diseaseReasonToSolutionRepository.deleteAllByDiseaseReasonId(diseaseReasonId);
        } else {
            // 修改疾病原因
            diseaseReasonEntity = diseaseReasonRepository.findById(diseaseReasonId);
            if (diseaseReasonEntity == null) {
                throw new FlightyThoughtException("疾病原因ID有误");
            }
            diseaseReasonEntity.setUpdateUserName(sysUserEntity.getLoginName());
        }
        // 疾病大类ID
        diseaseReasonEntity.setDiseaseId(diseaseReasonDTO.getDiseaseId());
        // 疾病小类ID
        diseaseReasonEntity.setDiseaseDetailId(diseaseReasonDTO.getDiseaseDetailId());
        // 编码
        diseaseReasonEntity.setNumber(diseaseReasonDTO.getNumber());
        // 标题
        diseaseReasonEntity.setTitle(diseaseReasonDTO.getTitle());
        // 内容
        diseaseReasonEntity.setContent(diseaseReasonDTO.getContent());
        // 类型
        diseaseReasonEntity.setType(diseaseReasonDTO.getType());
        // 保存疾病原因
        diseaseReasonRepository.save(diseaseReasonEntity);
        // 保存关联的解决方案
        List<Integer> solutionIds = diseaseReasonDTO.getSolutionIds();
        if (solutionIds != null && solutionIds.size() > 0) {
            List<DiseaseReasonToSolutionEntity> diseaseReasonToSolutionEntities = new ArrayList<>();
            solutionIds.forEach(solutionId -> {
                DiseaseReasonToSolutionEntity diseaseReasonToSolutionEntity = new DiseaseReasonToSolutionEntity();
                diseaseReasonToSolutionEntity.setDiseaseReasonId(diseaseReasonEntity.getId());
                diseaseReasonToSolutionEntity.setSolutionId(solutionId);
                diseaseReasonToSolutionEntities.add(diseaseReasonToSolutionEntity);
            });
            diseaseReasonToSolutionRepository.saveAll(diseaseReasonToSolutionEntities);
        }
        return diseaseReasonRepository.findById(diseaseReasonEntity.getId());
    }

    @Override
    @Transactional
    public DiseaseReasonEntity modifyDiseaseReason(DiseaseReasonDTO diseaseReasonDTO, HttpSession session) throws FlightyThoughtException {
        // 获取当前登录用户
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        DiseaseReasonEntity diseaseReasonEntity;
        Integer diseaseReasonId = diseaseReasonDTO.getId();

        // 修改疾病原因
        diseaseReasonEntity = diseaseReasonRepository.findById(diseaseReasonId);
        if (diseaseReasonEntity == null) {
            throw new FlightyThoughtException("疾病原因ID有误");
        }
        diseaseReasonEntity.setUpdateUserName(sysUserEntity.getLoginName());

        // 疾病大类ID
        diseaseReasonEntity.setDiseaseId(diseaseReasonDTO.getDiseaseId());
        // 疾病小类ID
        diseaseReasonEntity.setDiseaseDetailId(diseaseReasonDTO.getDiseaseDetailId());
        // 编码
        diseaseReasonEntity.setNumber(diseaseReasonDTO.getNumber());
        // 标题
        diseaseReasonEntity.setTitle(diseaseReasonDTO.getTitle());
        // 内容
        diseaseReasonEntity.setContent(diseaseReasonDTO.getContent());
        // 类型
        diseaseReasonEntity.setType(diseaseReasonDTO.getType());
        // 保存疾病原因
        diseaseReasonRepository.save(diseaseReasonEntity);
        // 保存关联的解决方案
        List<Integer> solutionIds = diseaseReasonDTO.getSolutionIds();
        if (solutionIds != null && solutionIds.size() > 0) {
            List<DiseaseReasonToSolutionEntity> diseaseReasonToSolutionEntities = new ArrayList<>();
            solutionIds.forEach(solutionId -> {
                DiseaseReasonToSolutionEntity diseaseReasonToSolutionEntity = new DiseaseReasonToSolutionEntity();
                diseaseReasonToSolutionEntity.setDiseaseReasonId(diseaseReasonEntity.getId());
                diseaseReasonToSolutionEntity.setSolutionId(solutionId);
                diseaseReasonToSolutionEntities.add(diseaseReasonToSolutionEntity);
            });
            diseaseReasonToSolutionRepository.saveAll(diseaseReasonToSolutionEntities);
        }
        return diseaseReasonRepository.findById(diseaseReasonEntity.getId());
    }

    @Override
    public Page<DiseaseReasonEntity> findAllDiseaseReason(Map<String, String> params, HttpSession session) {
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");
        String diseaseId = params.get("diseaseId");
        String diseaseDetailId = params.get("diseaseDetailId");

        DiseaseReasonEntity diseaseReasonEntity = new DiseaseReasonEntity();
        if (StringUtils.isNotBlank(diseaseId)) {
            diseaseReasonEntity.setDiseaseId(Integer.valueOf(diseaseId));
        }
        if (StringUtils.isNotBlank(diseaseDetailId)) {
            diseaseReasonEntity.setDiseaseDetailId(Integer.valueOf(diseaseDetailId));
        }
        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        PageRequest pageRequest = PageRequest.of(Integer.valueOf(pageNumber)-1, Integer.valueOf(pageSize));
        return diseaseReasonRepository.findAll(Example.of(diseaseReasonEntity), pageRequest);
    }

    @Override
    public DiseaseReasonEntity findDiseaseReasonById(Integer id, HttpSession session) {
        return diseaseReasonRepository.findById(id);
    }

    @Override
    @Transactional
    public void deleteDiseaseReason(Long id, HttpSession session) {
        diseaseReasonRepository.deleteById(id);
    }
}
