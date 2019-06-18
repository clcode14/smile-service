package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.CharityFaultTypeContent;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.CharityFaultTypeContentEntity;
import org.flightythought.smile.admin.service.CharityFaultTypeContentService;
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
 * @CreateTime 2019/5/14 16:27
 * @Description: TODO
 */
@RestController
@RequestMapping("/charityFault/type")
@Api(value = "行善过失配置", tags = "行善过失类型内容控制层", description = "行善过失类型内容控制层")
public class CharityFaultTypeContentController {

    private static final Logger LOG = LoggerFactory.getLogger(CharityFaultTypeContentController.class);
    @Autowired
    private CharityFaultTypeContentService charityFaultTypeContentService;

    @GetMapping("/content")
    @ApiOperation(value = "获取行善过失类型内容", notes = "获取心上过失类型内容")
    public ResponseBean getCharityFaultTypeContents(Integer pageSize, Integer pageNumber, Integer cfTypeId) {
        try {
            Page<CharityFaultTypeContent> result = charityFaultTypeContentService.getCharityFaultTypeContents(pageSize, pageNumber, cfTypeId);
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取失败", e);
            return ResponseBean.error("获取失败", e.getMessage());
        }
    }

    @PostMapping("/content")
    @ApiOperation(value = "新增行善过失类型内容", notes = "新增行善过失类型内容")
    public ResponseBean addCharityFaultTypeContent(@RequestBody CharityFaultTypeContentEntity charityFaultTypeContent) {
        try {
            CharityFaultTypeContentEntity result = charityFaultTypeContentService.addCharityFaultTypeContent(charityFaultTypeContent);
            return ResponseBean.ok("新增成功", result);
        } catch (Exception e) {
            LOG.error("新增失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }
    }

    @PutMapping("/content")
    @ApiOperation(value = "修改行善过失类型内容", notes = "修改行善过失类型内容")
    public ResponseBean modifyCharityFaultTypeContent(@RequestBody CharityFaultTypeContentEntity charityFaultTypeContentEntity) {
        try {
            CharityFaultTypeContentEntity result = charityFaultTypeContentService.modifyCharityFaultTypeContent(charityFaultTypeContentEntity);
            return ResponseBean.ok("修改成功", result);
        } catch (Exception e) {
            LOG.error("修改失败", e);
            return ResponseBean.error("修改失败", e.getMessage());
        }
    }

    @DeleteMapping("/content")
    @ApiOperation(value = "删除行善过失类型内容", notes = "删除行善过失类型内容")
    public ResponseBean deleteCharityFaultTypeContent(Integer id) {
        try {
            charityFaultTypeContentService.deleteCharityFaultTypeContent(id);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
