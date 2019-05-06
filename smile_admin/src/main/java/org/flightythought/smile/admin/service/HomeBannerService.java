package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.bean.HomeBanner;
import org.flightythought.smile.admin.database.entity.HomeBannerEntity;
import org.flightythought.smile.admin.dto.HomeBannerDTO;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.List;

public interface HomeBannerService {

    List<HomeBanner> findAll();

    HomeBannerEntity findOne(Integer id);

    HomeBannerEntity create(HomeBannerDTO homebannerDTO, HttpSession session);

    HomeBannerEntity modify(HomeBannerDTO homebannerDTO, HttpSession session);

    void deleteById(Integer id);

    void addCourseBanner(List<Integer> courseIds);

    List<CourseInfo> getBannerOfCourseInfo();

    Page<CourseInfo> getCourseRegistration(int pageNumber, int pageSize);

    void deleteCourseBanner(List<Integer> courseIds);
}
