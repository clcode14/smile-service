package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.admin.dto.CourseRegistrationDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CourseRegistrationService {

    /**
     * 新增课程
     */
    CourseRegistrationEntity addCourseRegistration(CourseRegistrationDTO courseRegistrationDTO, HttpSession session);

    /**
     * 获取课程
     *
     * @param pageNumber 页数
     * @param pageSize   页面显示数
     * @return 分页课程实体
     */
    Page<CourseInfo> getCourseRegistration(int pageNumber, int pageSize);

    /**
     * 查看课程详情
     *
     * @param courseId
     * @return
     */
    CourseInfo getCourseRegistrationDetail(Integer courseId);

    /**
     * 获取课程信息
     *
     * @param courseRegistrationEntity
     * @return
     */
    CourseInfo getCourseInfo(CourseRegistrationEntity courseRegistrationEntity);

    /**
     * 删除课程
     *
     * @param courseId
     */
    void deleteCourseRegistration(Integer courseId);

    /**
     * 获取课程类型
     *
     * @return
     */
    List<SelectItemOption> getCourseType();
}
