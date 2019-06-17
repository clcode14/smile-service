package org.flightythought.smile.admin.service;

import java.util.List;

import org.apache.poi.ss.usermodel.Workbook;
import org.flightythought.smile.admin.bean.CourseFollowInfo;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.dto.CourseFollowQueryDTO;
import org.springframework.data.domain.Page;

public interface CourseFollowService {

    Page<CourseFollowInfo> getFollowPage(CourseFollowQueryDTO courseFollowQueryDTO);

    List<SelectItemOption> getCourseRegistration();

    Workbook getFollowWorkbook(String phone, Integer courseId);

}
