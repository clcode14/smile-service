package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.HealthWaySimple;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.dto.HealthWayQueryDTO;
import org.flightythought.smile.appserver.service.HealthWayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "养生方式控制层", description = "养生方式相关接口")
@RequestMapping("/auth/healthWay")
public class HealthWayController {

    @Autowired
    private HealthWayService healthWayService;

    private static final Logger LOG = LoggerFactory.getLogger(HealthWayController.class);

    @PostMapping("/list")
    @ApiOperation(value = "获取养生方式", notes = "获取养生方式，养生方式ID可不穿，获取最近使用的养生方式需要" +
            "APP那里缓存下来对应的养生方式ID，再调用该接口")
    public ResponseBean getHealthWays(@RequestBody HealthWayQueryDTO healthWayQueryDTO) {
        try {
            Page<HealthWaySimple> result = healthWayService.getHealthWays(healthWayQueryDTO);
            return ResponseBean.ok("获取养生方式", result);
        } catch (Exception e) {
            LOG.error("获取养生方式", e);
            return ResponseBean.error("获取养生方式", e.getMessage());
        }
    }
}
