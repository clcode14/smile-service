package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.DiseaseClassEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.dto.DiseaseClassDTO;
import org.flightythought.smile.admin.service.DiseaseConfigService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/21 13:20
 */
@RestController
@RequestMapping("/disease")
@Api(value = "疾病配置", tags = "疾病大类")
public class DiseaseConfigController {
    private final DiseaseConfigService diseaseConfigService;

    private static final Logger LOG = LoggerFactory.getLogger(DiseaseConfigController.class);

    @Autowired
    public DiseaseConfigController(DiseaseConfigService diseaseConfigService) {
        this.diseaseConfigService = diseaseConfigService;
    }

    @GetMapping("/majorClass")
    @ApiOperation(value = "获取疾病大类", notes = "获取疾病大类", position = 1)
    public ResponseBean getDiseaseClass(int pageNumber, int pageSize) {
        Page<DiseaseClassEntity> diseaseClassEntities = diseaseConfigService.getDiseaseClass(pageNumber, pageSize);
        return ResponseBean.ok("返回成功", diseaseClassEntities);
    }

    @PostMapping("/addMajorClass")
    @ApiOperation(value = "新增疾病大类", notes = "新增疾病大类", position = 2)
    public ResponseBean addDiseaseClass(@RequestBody DiseaseClassDTO diseaseClassDTO, @ApiIgnore HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        DiseaseClassEntity diseaseClassEntity = new DiseaseClassEntity();
        diseaseClassEntity.setNumber(diseaseClassDTO.getNumber());
        diseaseClassEntity.setDiseaseName(diseaseClassDTO.getDiseaseName());
        diseaseClassEntity.setCreateUserName(sysUserEntity.getLoginName());
        DiseaseClassEntity result;
        try {
            result = diseaseConfigService.addDiseaseClass(diseaseClassEntity);
            return ResponseBean.ok("添加成功", result);
        } catch (Exception e) {
            LOG.error("新增疾病大类失败", e);
            return ResponseBean.error("新增疾病大类失败", e.getMessage());
        }
    }

    @PostMapping("/updateMajorClass")
    @ApiOperation(value = "修改疾病大类", notes = "修改疾病大类", position = 3)
    public ResponseBean updateDiseaseClass(@RequestBody DiseaseClassEntity diseaseClassEntity, @ApiIgnore HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        diseaseClassEntity.setUpdateUserName(sysUserEntity.getLoginName());
        DiseaseClassEntity result;
        try {
            result = diseaseConfigService.updateDiseaseClass(diseaseClassEntity);
            return ResponseBean.ok("修改成功", result);
        } catch (Exception e) {
            LOG.error("修改疾病大类失败", e);
            return ResponseBean.error("修改疾病大类失败", e.getMessage());
        }
    }

    @PostMapping("/deleteDiseaseClass/{id}")
    @ApiOperation(value = "删除疾病大类", notes = "删除疾病大类", position = 4)
    public ResponseBean deleteDiseaseClass(@PathVariable("id") Integer id) {
        diseaseConfigService.deleteDiseaseClass(id);
        return ResponseBean.ok("删除成功!");
    }
}
