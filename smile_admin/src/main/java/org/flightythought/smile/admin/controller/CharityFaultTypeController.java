package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.CharityFaultTypeEntity;
import org.flightythought.smile.admin.service.CharityFaultTypeService;
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
 * @CreateTime 2019/5/14 9:37
 * @Description: TODO
 */
@RestController
@RequestMapping("/charityFault")
@Api(value = "行善过失配置", tags = "行善过失类型配置控制层", description = "行善过失类型配置控制层")
public class CharityFaultTypeController {

    private static final Logger LOG = LoggerFactory.getLogger(CharityFaultTypeController.class);

    @Autowired
    private CharityFaultTypeService charityFaultTypeService;

    @GetMapping("/type")
    @ApiOperation(value = "获取行善过失类型", notes = "获取行善过失类型, 参数不传获取全部数据")
    public ResponseBean getCharityFaultTypes(Integer pageSize, Integer pageNumber) {
        try {
            Page<CharityFaultTypeEntity> result = charityFaultTypeService.getCharityFaultTypes(pageSize, pageNumber);
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取失败", e);
            return ResponseBean.error("获取失败", e.getMessage());
        }
    }

    @PostMapping("/type")
    @ApiOperation(value = "新增行善过失类型", notes = "新增行善过失类型")
    public ResponseBean addCharityFaultType(@RequestBody CharityFaultTypeEntity charityFaultTypeEntity) {
        try {
            CharityFaultTypeEntity result = charityFaultTypeService.addCharityFaultType(charityFaultTypeEntity);
            return ResponseBean.ok("新增成功", result);
        } catch (Exception e) {
            LOG.error("新增失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }
    }

    @PutMapping("/type")
    @ApiOperation(value = "修改行善过失类型", notes = "修改行善过失类型")
    public ResponseBean modifyCharityFaultType(@RequestBody CharityFaultTypeEntity charityFaultTypeEntity) {
        try {
            CharityFaultTypeEntity result = charityFaultTypeService.modifyCharityFaultType(charityFaultTypeEntity);
            return ResponseBean.ok("修改成功", result);
        } catch (Exception e) {
            LOG.error("修改失败", e);
            return ResponseBean.error("修改失败", e.getMessage());
        }
    }

    @DeleteMapping("/type/{cfTypeId}")
    @ApiOperation(value = "删除行善过失类型", notes = "删除行善过失类型")
    public ResponseBean deleteCharityFaultType(@PathVariable Integer cfTypeId) {
        try {
            charityFaultTypeService.deleteCharityFaultType(cfTypeId);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
