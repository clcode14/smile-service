package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.DiseaseReasonInfo;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.*;
import org.flightythought.smile.admin.dto.DiseaseReasonDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.DiseaseReasonService;
import org.springframework.beans.factory.annotation.Autowired;
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
import java.util.Optional;
import java.util.stream.Collectors;

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
    private DiseaseClassDetailRepository diseaseClassDetailRepository;
    @Autowired
    private DiseaseClassRepository diseaseClassRepository;
    @Autowired
    private DiseaseReasonRepository diseaseReasonRepository;
    @Autowired
    private DiseaseReasonToSolutionRepository diseaseReasonToSolutionRepository;
    @Autowired
    private DiseaseReasonTypeRepository diseaseReasonTypeRepository;

    @Override
    public List<SelectItemOption> getDiseaseReasonType() {
        List<DiseaseReasonTypeEntity> diseaseReasonTypeEntities = diseaseReasonTypeRepository.findAll();
        List<SelectItemOption> result = diseaseReasonTypeEntities.stream().map(diseaseReasonTypeEntity -> {
            SelectItemOption selectItemOption = new SelectItemOption();
            selectItemOption.setValue(diseaseReasonTypeEntity.getTypeName());
            selectItemOption.setKey(diseaseReasonTypeEntity.getTypeId() + "");
            return selectItemOption;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    @Transactional
    public DiseaseReasonEntity saveDiseaseReason(DiseaseReasonDTO diseaseReasonDTO, HttpSession session) throws FlightyThoughtException {
        // 获取当前登录用户
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        if (diseaseReasonDTO.getDiseaseDetailId() == null || diseaseReasonDTO.getDiseaseId() == null) {
            throw new FlightyThoughtException("请选择原因所对应的疾病大类和疾病小类");
        }
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
        // 阅读数
        diseaseReasonEntity.setReadNum(0);
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
        // 先删除关联的解决方案
        diseaseReasonToSolutionRepository.deleteAllByDiseaseReasonId(diseaseReasonId);
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
    public Page<DiseaseReasonInfo> findAllDiseaseReason(Map<String, String> params, HttpSession session) {
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
        PageRequest pageRequest = PageRequest.of(Integer.valueOf(pageNumber) - 1, Integer.valueOf(pageSize));
        Page<DiseaseReasonEntity> page = diseaseReasonRepository.findAll(Example.of(diseaseReasonEntity), pageRequest);
        List<DiseaseReasonInfo> diseaseReasonInfos = page.getContent()
                .stream()
                .map(diseaseReason -> {
                    DiseaseReasonInfo diseaseReasonInfo = new DiseaseReasonInfo();
                    //获取疾病大类名和疾病详情名称
                    DiseaseClassEntity diseaseClassEntity = diseaseClassRepository.findByDiseaseId(diseaseReason.getDiseaseId());
                    DiseaseClassDetailEntity diseaseClassDetail = diseaseClassDetailRepository.findByDiseaseDetailId(diseaseReason.getDiseaseDetailId());
                    // 主键ID
                    diseaseReasonInfo.setId(diseaseReason.getId());
                    // 疾病大类名称
                    diseaseReasonInfo.setDiseaseName(diseaseClassEntity.getDiseaseName());
                    // 疾病小类名称
                    diseaseReasonInfo.setDiseaseDetailName(diseaseClassDetail.getDiseaseDetailName());
                    // 疾病大类ID
                    diseaseReasonInfo.setDiseaseId(diseaseClassEntity.getDiseaseId());
                    // 疾病小类ID
                    diseaseReasonInfo.setDiseaseDetailId(diseaseClassDetail.getDiseaseDetailId());
                    // 相关解决方案
                    List<SelectItemOption> solutions = diseaseReason.getSolutions()
                            .stream()
                            .map(solutionEntity -> {
                                SelectItemOption selectItemOption = new SelectItemOption();
                                selectItemOption.setKey(solutionEntity.getId() + "");
                                selectItemOption.setValue(solutionEntity.getTitle());
                                return selectItemOption;
                            })
                            .collect(Collectors.toList());
                    diseaseReasonInfo.setSolutionNames(solutions);
                    // 疾病原因描述内容
                    diseaseReasonInfo.setContent(diseaseReason.getContent());
                    // 编号
                    diseaseReasonInfo.setNumber(diseaseReason.getNumber());
                    // 标题
                    diseaseReasonInfo.setTitle(diseaseReason.getTitle());
                    // 类型
                    SelectItemOption type = new SelectItemOption();
                    DiseaseReasonTypeEntity diseaseReasonTypeEntity = diseaseReason.getReasonType();
                    type.setKey(diseaseReasonTypeEntity.getTypeId() + "");
                    type.setValue(diseaseReasonTypeEntity.getTypeName());
                    diseaseReasonInfo.setType(type);
                    return diseaseReasonInfo;
                }).collect(Collectors.toList());
        PageImpl<DiseaseReasonInfo> result = new PageImpl<>(diseaseReasonInfos, pageRequest, page.getTotalElements());
        return result;
    }

    @Override
    public DiseaseReasonInfo findDiseaseReasonById(Integer id, HttpSession session) {
        return Optional.ofNullable(diseaseReasonRepository
                .findById(id))
                .map(diseaseReasonEntity -> {
                    DiseaseReasonInfo diseaseReasonInfo = new DiseaseReasonInfo();
                    DiseaseClassEntity diseaseClassEntity = diseaseClassRepository.findByDiseaseId(diseaseReasonEntity.getDiseaseId());
                    DiseaseClassDetailEntity diseaseClassDetail = diseaseClassDetailRepository.findByDiseaseDetailId(diseaseReasonEntity.getDiseaseDetailId());
                    // 主键ID
                    diseaseReasonInfo.setId(diseaseReasonEntity.getId());
                    // 疾病大类名称
                    diseaseReasonInfo.setDiseaseName(diseaseClassEntity.getDiseaseName());
                    // 疾病小类名称
                    diseaseReasonInfo.setDiseaseDetailName(diseaseClassDetail.getDiseaseDetailName());
                    // 相关解决方案
                    List<SelectItemOption> solutions = diseaseReasonEntity.getSolutions()
                            .stream()
                            .map(solutionEntity -> {
                                SelectItemOption selectItemOption = new SelectItemOption();
                                selectItemOption.setValue(solutionEntity.getTitle());
                                selectItemOption.setKey(solutionEntity.getId() + "");
                                return selectItemOption;
                            })
                            .collect(Collectors.toList());
                    diseaseReasonInfo.setSolutionNames(solutions);
                    // 疾病原因内容描述
                    diseaseReasonInfo.setContent(diseaseReasonEntity.getContent());
                    // 编号
                    diseaseReasonInfo.setNumber(diseaseReasonEntity.getNumber());
                    // 标题
                    diseaseReasonInfo.setTitle(diseaseReasonEntity.getTitle());
                    // 类型
                    SelectItemOption type = new SelectItemOption();
                    DiseaseReasonTypeEntity diseaseReasonTypeEntity = diseaseReasonEntity.getReasonType();
                    type.setKey(diseaseReasonTypeEntity.getTypeId() + "");
                    type.setValue(diseaseReasonTypeEntity.getTypeName());
                    diseaseReasonInfo.setType(type);
                    return diseaseReasonInfo;
                }).orElse(null);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteDiseaseReason(Integer id, HttpSession session) {
        diseaseReasonRepository.deleteById(id);
    }
}
