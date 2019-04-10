package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.CourseSimple;
import org.flightythought.smile.appserver.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.appserver.dto.CourseInfoQueryDTO;
import org.flightythought.smile.appserver.dto.CourseQueryDTO;
import org.springframework.data.domain.Page;

public interface CourseService {
    Page<CourseSimple> getCourseSimples(CourseQueryDTO courseQueryDTO);

    Page<CourseRegistrationEntity> getCourses(CourseQueryDTO courseQueryDTO);

    Page<CourseSimple> getCoursesInfo(CourseInfoQueryDTO courseInfoQueryDTO);

    Page<CourseRegistrationEntity> getCourses(CourseInfoQueryDTO courseInfoQueryDTO);

    Page<CourseSimple> getCourseSimples(Page<CourseRegistrationEntity> courseRegistrationEntities);
}
