package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.HomeBanner;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HomeController
 * @CreateTime 2019/4/14 19:29
 * @Description: TODO
 */
@RestController
@RequestMapping("/auth/home")
@Api(tags = "主页控制层", description = "主页控制层")
public class HomeController {

    private static final Logger LOG = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private HomeService homeService;

    @GetMapping("/banners")
    @ApiOperation(value = "获取主页Banner图", notes = "获取主页Banner图")
    public ResponseBean getHomeBanners() {
        try {
            List<HomeBanner> homeBanners = homeService.getHomeBanners();
            return ResponseBean.ok("获取成功", homeBanners);
        } catch (Exception e) {
            LOG.error("获取主页Banner图失败", e);
            return ResponseBean.error("获取失败", e.getMessage());
        }
    }
}
