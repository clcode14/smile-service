package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.CourseInfo;
import org.flightythought.smile.admin.bean.HomeBanner;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.HomeBannerEntity;
import org.flightythought.smile.admin.dto.HomeBannerDTO;
import org.flightythought.smile.admin.service.HomeBannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/banner")
@Api(tags = "轮播图", description = "轮播图接口")
public class BannerController {

    private static final Logger LOG = LoggerFactory.getLogger(BannerController.class);

    @Autowired
    private HomeBannerService homeBannerService;

    @GetMapping("/home/list")
    @ApiOperation(value = "home轮播图列表", notes = "home轮播图列表", position = 1)
    public ResponseBean findAllHomeBanner() {
        try {
            List<HomeBanner> homeBannerEntities = homeBannerService.findAll();
            return ResponseBean.ok("查询成功", homeBannerEntities);
        } catch (Exception e) {
            LOG.error("查询home轮播图列表失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }

    @GetMapping("/home/{id}")
    @ApiOperation(value = "home轮播图", notes = "home轮播图", position = 1)
    public ResponseBean findHomeBanner(@PathVariable Integer id) {
        try {
            HomeBannerEntity homeBannerEntities = homeBannerService.findOne(id);
            return ResponseBean.ok("查询成功", homeBannerEntities);
        } catch (Exception e) {
            LOG.error("查询home轮播图失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }

    @PostMapping("/home/create")
    @ApiOperation(value = "新增home轮播图", notes = "新增home轮播图", position = 1)
    public ResponseBean addHomeBanner(@RequestBody HomeBannerDTO homeBannerDTO, @ApiIgnore HttpSession session) {
        try {
            HomeBannerEntity homeBannerEntities = homeBannerService.create(homeBannerDTO, session);
            return ResponseBean.ok("新增成功", homeBannerEntities);
        } catch (Exception e) {
            LOG.error("新增home轮播图失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }
    }

    @PutMapping("/home/modify")
    @ApiOperation(value = "修改home轮播图", notes = "修改home轮播图", position = 1)
    public ResponseBean modifyHomeBanner(@RequestBody HomeBannerDTO homeBannerDTO, @ApiIgnore HttpSession session) {
        try {
            HomeBannerEntity homeBannerEntities = homeBannerService.modify(homeBannerDTO, session);
            return ResponseBean.ok("修改成功", homeBannerEntities);
        } catch (Exception e) {
            LOG.error("修改home轮播图失败", e);
            return ResponseBean.error("修改失败", e.getMessage());
        }
    }

    @DeleteMapping("/home/{id}")
    @ApiOperation(value = "删除home轮播图", notes = "删除home轮播图", position = 1)
    public ResponseBean deleteHomeBanner(@PathVariable Integer id) {
        try {
            homeBannerService.deleteById(id);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除home轮播图失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }

    @PostMapping("/courseBanner")
    @ApiOperation(value = "添加课程Banner图", notes = "添加课程Banner图")
    public ResponseBean addCourseBanner(@RequestBody List<Integer> courseIds) {
        try {
            homeBannerService.addCourseBanner(courseIds);
            return ResponseBean.ok("添加成功");
        } catch (Exception e) {
            LOG.error("添加课程Banner图失败", e);
            return ResponseBean.error("添加课程Banner图失败", e.getMessage());
        }
    }

    @GetMapping("/courseBanner")
    @ApiOperation(value = "获取被设置成课程Banner图的课程", notes = "获取被设置成课程Banner图的课程")
    public ResponseBean getCourseBanner() {
        try {
            List<CourseInfo> result = homeBannerService.getBannerOfCourseInfo();
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取失败", e);
            return ResponseBean.error("获取失败", e.getMessage());
        }
    }

    @GetMapping("/courseList")
    @ApiOperation(value = "获取课程", notes = "获取课程", position = 2)
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "当前页数从1开始"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示个数")

    })
    public ResponseBean getCourseRegistration(int pageNumber, int pageSize) {
        Page<CourseInfo> courseRegistrationEntities = homeBannerService.getCourseRegistration(pageNumber, pageSize);
        return ResponseBean.ok("返回成功", courseRegistrationEntities);
    }

    @DeleteMapping("/courseBanner")
    @ApiOperation(value = "删除课程Banner图", notes = "删除课程Banner图")
    public ResponseBean deleteCourseBanner(@RequestParam(value = "courseIds") List<Integer> courseIds) {
        try {
            homeBannerService.deleteCourseBanner(courseIds);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
