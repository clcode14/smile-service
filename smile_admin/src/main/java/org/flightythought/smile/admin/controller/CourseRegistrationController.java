package org.flightythought.smile.admin.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.admin.dto.CourseRegistrationDTO;
import org.flightythought.smile.admin.service.CourseRegistrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    @PostMapping("/add")
    @ApiOperation(value = "新增课程", notes = "新增课程", position = 1)
    public ResponseBean addCourseRegistration(CourseRegistrationDTO courseRegistrationDTO,
                                              @ApiParam(value = "封面图片(只支持一张)") MultipartFile coverPicture,
                                              @ApiParam(value = "展示图(支持多张上传)") List<MultipartFile> images,
                                              @ApiIgnore HttpSession session) {
        try {
            CourseRegistrationEntity courseRegistrationEntity = new CourseRegistrationEntity();
            courseRegistrationEntity.setTitle(courseRegistrationDTO.getTitle());
            LocalDateTime startTime = LocalDateTime.parse(courseRegistrationDTO.getStartTime(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            courseRegistrationEntity.setStartTime(startTime);
            courseRegistrationEntity.setMembers(courseRegistrationDTO.getMembers());
            courseRegistrationEntity.setAddress(courseRegistrationDTO.getAddress());
            courseRegistrationEntity.setDescription(courseRegistrationDTO.getDescription());
            courseRegistrationEntity.setPrice(courseRegistrationDTO.getPrice());
            courseRegistrationEntity = courseRegistrationService.addCourseRegistration(courseRegistrationEntity, coverPicture, images, session);
            if (courseRegistrationEntity != null) {
                return ResponseBean.ok("新增成功");
            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage(), e);
            return ResponseBean.error(e.getMessage());
        }
        return ResponseBean.error(null);
    }

    @GetMapping("getCourseInfo")
    @ApiOperation(value = "获取课程", notes = "获取课程", position = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "当前页数从1开始"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示个数")

    })
    public ResponseBean getCourseRegistration(int pageNumber, int pageSize) {
        Page<CourseInfo> courseRegistrationEntities = courseRegistrationService.getCourseRegistration(pageNumber, pageSize);
        return ResponseBean.ok("返回成功", courseRegistrationEntities);
    }


}
