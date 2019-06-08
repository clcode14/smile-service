package org.flightythought.smile.admin.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.flightythought.smile.admin.bean.CourseFollowInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.dto.CourseFollowQueryDTO;
import org.flightythought.smile.admin.service.CourseFollowService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 报名管理控制层
 * 
 * @author cl47872
 * @version $Id: CourseFollowController.java, v 0.1 Jun 8, 2019 9:36:17 AM cl47872 Exp $
 */
@RestController
@RequestMapping("/course")
@Api(value = "报名管理", tags = "报名管理")
public class CourseFollowController {
    @Autowired
    private CourseFollowService courseFollowService;

    private static final Logger LOG = LoggerFactory.getLogger(CourseFollowController.class);

    @ApiOperation(value = "获取课程配置下拉选", notes = "获取课程配置下拉选")
    @GetMapping("/courseRegistrationList")
    public ResponseBean getCourseRegistration() {
        try {
            List<SelectItemOption> result = courseFollowService.getCourseRegistration();
            return ResponseBean.ok("返回课程配置成功", result);
        } catch (Exception e) {
            LOG.error("获取课程配置失败", e);
            return ResponseBean.error("返回课程配置失败", e.getMessage());
        }
    }
    
    @PostMapping("/getFollowPage")
    @ApiOperation(value = "查询报名列表", notes = "查询报名列表")
    public ResponseBean getFollowPage(@RequestBody CourseFollowQueryDTO courseFollowQueryDTO) {
        try {
            Page<CourseFollowInfo> result = courseFollowService.getFollowPage(courseFollowQueryDTO);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("查询报名列表失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }
    
    @GetMapping(value = "/export")
    @ApiOperation(value = "报名列表导出", notes = "报名列表导出")
    public void courseFollowExport(String phone, Integer courseId,HttpServletResponse response) {
        OutputStream os = null;
        Workbook workbook = null; //工作薄
        try {
            //查询对账单列表信息
            workbook = courseFollowService.getFollowWorkbook(phone,courseId);
            // 将Excel写入到输出流
            response.reset();
            response.setCharacterEncoding("utf-8");
            /**自动识别*/
            response.setContentType("application/vnd.ms-excel");
            response.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("报名列表_" + System.currentTimeMillis() + ".xlsx", "UTF-8"));
            os = response.getOutputStream();
            /**文件流输出到response里*/
            workbook.write(os);
            response.flushBuffer();
        } catch (Exception e) {
            LOG.error("报名列表导出失败", e);
        } finally {
            try {
                os.flush();
                os.close();
            } catch (IOException e) {
                LOG.error("导出报表记录IO异常 ", e);
            }
        }
    }
}
