package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.database.entity.SolutionEntity;
import org.flightythought.smile.admin.dto.SolutionDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.SolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

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

    @PostMapping("/add")
    @ApiOperation(value = "增加解决方案", notes = "增加解决方案", position = 1)
    public ResponseBean addSolution(SolutionDTO solutionDTO,
                                    @ApiParam(value = "配图(支持多张上传)") List<MultipartFile> images,
                                    @ApiIgnore HttpSession session) {
        try {
            SolutionEntity solutionEntity = solutionService.addSolution(solutionDTO, images, session);
            return ResponseBean.ok("新增成功", solutionEntity);
        } catch (Exception e) {
            LOG.error("新增解决方案失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }
    }
}
