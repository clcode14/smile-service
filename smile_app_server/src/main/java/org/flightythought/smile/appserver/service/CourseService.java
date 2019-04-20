package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.CourseSimple;
import org.flightythought.smile.appserver.bean.SelectItemOption;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.appserver.database.entity.UserFollowCourseEntity;
import org.flightythought.smile.appserver.dto.ApplyCourseDTO;
import org.flightythought.smile.appserver.dto.CourseInfoQueryDTO;
import org.flightythought.smile.appserver.dto.CourseQueryDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface CourseService {
    Page<CourseSimple> getCourseSimples(CourseQueryDTO courseQueryDTO);

    Page<CourseRegistrationEntity> getCourses(CourseQueryDTO courseQueryDTO);

    Page<CourseSimple> getCoursesInfo(CourseInfoQueryDTO courseInfoQueryDTO);

    Page<CourseRegistrationEntity> getCourses(CourseInfoQueryDTO courseInfoQueryDTO);

    Page<CourseSimple> getCourseSimples(Page<CourseRegistrationEntity> courseRegistrationEntities);

    UserFollowCourseEntity applyCourse(ApplyCourseDTO applyCourseDTO) throws FlightyThoughtException;

    Page<CourseSimple> getUserCourses(PageFilterDTO pageFilterDTO);

    List<CourseSimple> getCourseSimple(List<CourseRegistrationEntity> courseRegistrationEntities);

    Page<SelectItemOption> getCourseType(PageFilterDTO pageFilterDTO);

    List<CourseSimple> getCourseBanner();
}
