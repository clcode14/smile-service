package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.CourseTypeEntity;
import org.flightythought.smile.admin.service.CourseTypeService;
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
 * @CreateTime 2019/5/14 12:27
 * @Description: TODO
 */
@RestController
@RequestMapping("/course")
@Api(tags = "课程类型配置控制层", description = "课程类型配置控制层")
public class CourseTypeController {

    private static final Logger LOG = LoggerFactory.getLogger(CourseTypeController.class);
    @Autowired
    private CourseTypeService courseTypeService;

    @GetMapping("/type")
    @ApiOperation(value = "获取课程类型", notes = "获取课程类型")
    public ResponseBean getCourseTypes(Integer pageSize, Integer pageNumber) {
        try {
            Page<CourseTypeEntity> result = courseTypeService.getCourseTypes(pageSize, pageNumber);
            return ResponseBean.ok("获取课程类型成功", result);
        } catch (Exception e) {
            LOG.error("获取课程类型失败", e);
            return ResponseBean.error("获取课程类型失败", e.getMessage());
        }
    }

    @PostMapping("/type")
    @ApiOperation(value = "新增课程类型", notes = "新增课程类型")
    public ResponseBean addCourseType(@RequestBody CourseTypeEntity courseTypeEntity) {
        try {
            CourseTypeEntity result = courseTypeService.addCourseType(courseTypeEntity);
            return ResponseBean.ok("新增课程类型成功", result);
        } catch (Exception e) {
            LOG.error("新增失败", e);
            return ResponseBean.error("新增课程类型失败", e.getMessage());
        }
    }

    @PutMapping("/type")
    @ApiOperation(value = "修改课程类型", notes = "修改课程类型")
    public ResponseBean modifyCourseType(@RequestBody CourseTypeEntity courseTypeEntity) {
        try {
            CourseTypeEntity result = courseTypeService.modifyCourseType(courseTypeEntity);
            return ResponseBean.ok("修改成功", result);
        } catch (Exception e) {
            LOG.error("修改失败", e);
            return ResponseBean.error("修改失败", e.getMessage());
        }
    }

    @DeleteMapping("/type/{id}")
    @ApiOperation(value = "删除课程类型", notes = "删除课程类型")
    public ResponseBean deleteCourseType(@PathVariable Integer id) {
        try {
            courseTypeService.deleteCourseType(id);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
