package org.flightythought.smile.admin.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.admin.dto.CourseRegistrationDTO;
import org.flightythought.smile.admin.service.CourseRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CourseRegistrationController.java
 * @CreateTime 2019/3/27 16:56
 * @Description: 课程报名控制层
 */
@RestController
@RequestMapping("/course")
@Api(value = "课程报名", tags = "课程报名")
public class CourseRegistrationController {
    @Autowired
    private CourseRegistrationService courseRegistrationService;

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRegistrationController.class);

    @ApiOperation(value = "获取课程类型下拉选", notes = "获取课程类型下拉选")
    @GetMapping("/courseType")
    public ResponseBean getCourseType() {
        try {
            List<SelectItemOption> result = courseRegistrationService.getCourseType();
            return ResponseBean.ok("返回课程类型成功", result);
        } catch (Exception e) {
            LOGGER.error("获取课程类型失败", e);
            return ResponseBean.error("返回课程类型失败", e.getMessage());
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增课程", notes = "新增课程", position = 1)
    public ResponseBean addCourseRegistration(@RequestBody CourseRegistrationDTO courseRegistrationDTO, @ApiIgnore HttpSession session) {
        try {
            CourseRegistrationEntity courseRegistrationEntity = courseRegistrationService.addCourseRegistration(courseRegistrationDTO, session);
            if (courseRegistrationEntity != null) {
                return ResponseBean.ok("添加成功");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
        return ResponseBean.error(null);
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改课程", notes = "修改课程")
    public ResponseBean modifyCourseRegistration(@RequestBody CourseRegistrationDTO courseRegistrationDTO, @ApiIgnore HttpSession session) {
        try {
            CourseRegistrationEntity courseRegistrationEntity = courseRegistrationService.addCourseRegistration(courseRegistrationDTO, session);
            if (courseRegistrationEntity != null) {
                return ResponseBean.ok("修改成功");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
        return ResponseBean.error(null);
    }

    @GetMapping("/list")
    @ApiOperation(value = "获取课程", notes = "获取课程", position = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "当前页数从1开始"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示个数")

    })
    public ResponseBean getCourseRegistration(int pageNumber, int pageSize) {
        Page<CourseInfo> courseRegistrationEntities = courseRegistrationService.getCourseRegistration(pageNumber, pageSize);
        return ResponseBean.ok("返回成功", courseRegistrationEntities);
    }

    @GetMapping("/detail/{courseId}")
    @ApiOperation(value = "获取课程详情", notes = "获取课程", position = 2)
    public ResponseBean getCourseRegistrationDetail(@PathVariable @ApiParam("课程id") Integer courseId) {
        CourseInfo courseRegistrationEntities = courseRegistrationService.getCourseRegistrationDetail(courseId);
        return ResponseBean.ok("返回成功", courseRegistrationEntities);
    }

    @DeleteMapping("/delete/{courseId}")
    @ApiOperation(value = "删除课程", notes = "获取课程", position = 2)
    public ResponseBean deleteCourseRegistrationDetail(@PathVariable @ApiParam("课程id") Integer courseId) {
        courseRegistrationService.deleteCourseRegistration(courseId);
        return ResponseBean.ok("删除成功");
    }
}
