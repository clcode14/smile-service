package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.CourseTypeEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.CourseTypeRepository;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.CourseTypeService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 12:28
 * @Description: TODO
 */
@Service
public class CourseTypeServiceImpl implements CourseTypeService {
    @Autowired
    private CourseTypeRepository courseTypeRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    public Page<CourseTypeEntity> getCourseTypes(Integer pageSize, Integer pageNumber) {
        if (pageSize == null || pageSize == 0 || pageNumber == null || pageNumber == 0) {
            // 获取全部课程类型
            List<CourseTypeEntity> courseTypeEntities = courseTypeRepository.findAll();
            return new PageImpl<>(courseTypeEntities, PageRequest.of(0, courseTypeEntities.size() + 1), courseTypeEntities.size());
        } else {
            return courseTypeRepository.findAll(PageRequest.of(pageNumber - 1, pageSize));
        }
    }

    @Override
    public CourseTypeEntity addCourseType(CourseTypeEntity courseTypeEntity) {
        // 获取当前登录用户
        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        courseTypeEntity.setCreateUserName(userEntity.getLoginName());
        // 保存courseTypeEntity
        courseTypeEntity = courseTypeRepository.save(courseTypeEntity);
        return courseTypeEntity;
    }

    @Override
    public CourseTypeEntity modifyCourseType(CourseTypeEntity courseTypeEntity) {
        if (courseTypeEntity.getId() == null) {
            throw new FlightyThoughtException("课程类型ID不能为Null");
        }
        CourseTypeEntity entity = courseTypeRepository.findById(courseTypeEntity.getId());
        BeanUtils.copyProperties(courseTypeEntity, entity);
        entity = courseTypeRepository.save(entity);
        return entity;
    }

    @Override
    public void deleteCourseType(Integer id) {
        CourseTypeEntity courseTypeEntity = courseTypeRepository.findById(id);
        if (courseTypeEntity != null) {
            courseTypeRepository.delete(courseTypeEntity);
        }
    }
}
