package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.HealthClass;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.flightythought.smile.appserver.service.HealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthController
 * @CreateTime 2019/4/9 18:02
 * @Description: TODO
 */
@RestController
@RequestMapping("/auth/health")
@Api(tags = "健康养生控制层", description = "健康养生相关接口")
public class HealthController {

    @Autowired
    private HealthService healthService;

    private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

    @PostMapping("/healthClass")
    @ApiOperation(value = "获取养生", notes = "获取养生，分页查询，不传递参数为获取全部")
    public ResponseBean findHealthClass(@RequestBody PageFilterDTO pageFilterDTO) {
        try {
            Integer pageSize = pageFilterDTO.getPageSize();
            Integer pageNumber = pageFilterDTO.getPageNumber();
            Page<HealthClass> result = healthService.findHealth(pageSize, pageNumber);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生大类失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }


    @GetMapping("/healthClass/{healthClassId}")
    @ApiOperation(value = "获取养生大类明细", notes = "根据养生大类ID获取养生大类明细")
    public ResponseBean getHealthClass(@PathVariable("healthClassId") Integer healthClassId) {
        try {
            HealthClass result = healthService.getHealthClass(healthClassId);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生大类明细失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }
}
