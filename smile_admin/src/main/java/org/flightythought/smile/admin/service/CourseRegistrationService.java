package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface CourseRegistrationService {

    /**
     * 新增课程
     *
     * @param courseRegistrationEntity 课程报名实体类
     * @param coverPicture             封面图
     * @param images                   展示图
     * @return 新增课程实体类
     */
    CourseRegistrationEntity addCourseRegistration(CourseRegistrationEntity courseRegistrationEntity,
                                                   MultipartFile coverPicture, List<MultipartFile> images, HttpSession session) throws FlightyThoughtException;

    /**
     * 获取课程
     * @param pageNumber 页数
     * @param pageSize 页面显示数
     * @return 分页课程实体
     */
    Page<CourseInfo> getCourseRegistration(int pageNumber, int pageSize);
}
