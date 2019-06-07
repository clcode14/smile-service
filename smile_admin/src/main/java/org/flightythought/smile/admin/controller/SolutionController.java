package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.bean.SolutionInfo;
import org.flightythought.smile.admin.database.entity.SolutionEntity;
import org.flightythought.smile.admin.dto.SolutionDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.SolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionController
 * @CreateTime 2019/3/27 18:40
 * @Description: 解决方案控制层
 */
@RestController
@RequestMapping("/solution")
@Api(value = "解决方案", tags = "解决方案")
public class SolutionController {
    private static final Logger LOG = LoggerFactory.getLogger(SolutionController.class);
    @Autowired
    private SolutionService solutionService;

    @GetMapping("/courseItems")
    @ApiOperation(value = "获取相关课程Option", notes = "获取相关课程Option", position = 0)
    public ResponseBean getCourseItems() {
        try {
            List<SelectItemOption> result = solutionService.getCourseItems();
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("返回失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("/officeItems")
    @ApiOperation(value = "获取相关机构", notes = "获取相关机构Option", position = 0)
    public ResponseBean getOfficeItems() {
        try {
            List<SelectItemOption> result = solutionService.getOfficeItems();
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("返回失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }
    
    @GetMapping("/commodities")
    @ApiOperation(value = "获取相关商品", notes = "获取相关商品Option", position = 0)
    public ResponseBean getCommodities() {
        try {
            List<SelectItemOption> result = solutionService.getCommodities();
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("返回失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "解决方案列表", notes = "查询解决方案", position = 1)
    public ResponseBean findAllSolution(Map<String, String> params,
                                        @ApiIgnore HttpSession session) {
        try {
            Page<SolutionInfo> solutionEntity = solutionService.findAllSolution(params, session);
            return ResponseBean.ok("查询成功", solutionEntity);
        } catch (Exception e) {
            LOG.error("查询方案失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "查询解决方案", notes = "查询解决方案", position = 1)
    public ResponseBean findSolution(@PathVariable @ApiParam("方案id") Integer id,
                                     @ApiIgnore HttpSession session) {
        try {
            SolutionEntity solutionEntity = solutionService.findSolution(id, session);
            return ResponseBean.ok("查询成功", solutionEntity);
        } catch (Exception e) {
            LOG.error("查询方案失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "增加解决方案", notes = "增加解决方案", position = 1)
    public ResponseBean saveSolution(@RequestBody @ApiParam SolutionDTO solutionDTO,
                                     @ApiIgnore HttpSession session) {
        try {
            SolutionEntity solutionEntity = solutionService.saveSolution(solutionDTO, session);
            return ResponseBean.ok("新增成功", solutionEntity);
        } catch (Exception e) {
            LOG.error("新增解决方案失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }
    }

    @PostMapping("/modify")
    @ApiOperation(value = "编辑解决方案", notes = "增加解决方案", position = 1)
    public ResponseBean modifySolution(@RequestBody @ApiParam SolutionDTO solutionDTO,
                                       @ApiIgnore HttpSession session) {
        try {
            SolutionEntity solutionEntity = solutionService.modifySolution(solutionDTO, session);
            return ResponseBean.ok("编辑成功", solutionEntity);
        } catch (Exception e) {
            LOG.error("编辑方案失败", e);
            return ResponseBean.error("编辑失败", e.getMessage());
        }
    }
}
