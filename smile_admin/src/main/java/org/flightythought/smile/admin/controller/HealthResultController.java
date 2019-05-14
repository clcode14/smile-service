package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.HealthResultEntity;
import org.flightythought.smile.admin.service.HealthResultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 15:16
 * @Description: TODO
 */
@RestController
@RequestMapping("/health")
@Api(tags = "养生成果控制层", description = "养生成果控制层")
public class HealthResultController {

    private static final Logger LOG = LoggerFactory.getLogger(HealthResultController.class);

    @Autowired
    private HealthResultService healthResultService;

    @GetMapping("/result")
    @ApiOperation(value = "获取养生成果", notes = "获取养生成果")
    public ResponseBean getHealthResult(Integer pageSize, Integer pageNumber) {
        try {
            Page<HealthResultEntity> result = healthResultService.getHealthResult(pageSize, pageNumber);
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取失败", e);
            return ResponseBean.error("获取失败", e.getMessage());
        }
    }

    @PostMapping("/result")
    @ApiOperation(value = "新增养生成果", notes = "新增养生成果")
    public ResponseBean addHealthResult(@RequestBody HealthResultEntity healthResultEntity) {
        try {
            HealthResultEntity result = healthResultService.addHealthResult(healthResultEntity);
            return ResponseBean.ok("新增成功", result);
        } catch (Exception e) {
            LOG.error("新增失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }
    }

    @PutMapping("/result")
    @ApiOperation(value = "修改养生成果", notes = "修改养生成果")
    public ResponseBean modifyHealthResult(@RequestBody HealthResultEntity healthResultEntity) {
        try {
            HealthResultEntity result = healthResultService.modifyHealthResult(healthResultEntity);
            return ResponseBean.ok("修改成功", result);
        } catch (Exception e) {
            LOG.error("修改失败", e);
            return ResponseBean.error("修改失败", e.getMessage());
        }
    }

    @DeleteMapping("/result/{id}")
    @ApiOperation(value = "删除养生成果", notes = "删除养生成果")
    public ResponseBean deleteHealthResult(@PathVariable Integer id) {
        try {
            healthResultService.deleteHealthResult(id);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
