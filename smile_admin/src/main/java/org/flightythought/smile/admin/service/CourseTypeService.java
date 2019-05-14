package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.CourseTypeEntity;
import org.springframework.data.domain.Page;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 12:28
 * @Description: TODO
 */
public interface CourseTypeService {
    Page<CourseTypeEntity> getCourseTypes(Integer pageSize, Integer pageNumber);

    CourseTypeEntity addCourseType(CourseTypeEntity courseTypeEntity);

    CourseTypeEntity modifyCourseType(CourseTypeEntity courseTypeEntity);

    void deleteCourseType(Integer id);
}
