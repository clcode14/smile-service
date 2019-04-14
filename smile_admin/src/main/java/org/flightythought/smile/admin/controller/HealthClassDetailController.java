package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.HealthClassDetailInfo;
import org.flightythought.smile.admin.bean.HealthClassInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.HealthClassEntity;
import org.flightythought.smile.admin.dto.HealthClassDTO;
import org.flightythought.smile.admin.service.HealthClassDetailService;
import org.flightythought.smile.admin.service.HealthClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthController
 * @CreateTime 2019/4/9 18:02
 * @Description: TODO
 */
@RestController
@RequestMapping("healthClassDetail")
@Api(tags = "健康养生小类控制层", description = "健康养生小类控制层相关接口")
public class HealthClassDetailController {

    @Autowired
    private HealthClassDetailService healthClassDetailService;

    private static final Logger LOG = LoggerFactory.getLogger(HealthClassDetailController.class);

    @GetMapping("/list")
    @ApiOperation(value = "获取养生小类", notes = "获取养生小类，healthId为养生大类ID，可以不用传递，分页查询参数不传递是返回全部数据")
    public ResponseBean findHealthDetailClass(Map<String, String> params) {
        try {
            Page<HealthClassDetailInfo> result = healthClassDetailService.findHealthDetailClass(params);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生小类失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("{healthDetailClassId}")
    @ApiOperation(value = "获取养生小类明细", notes = "根据养生小类ID获取养生小类明细")
    public ResponseBean getHealthDetailClass(@PathVariable("healthDetailClassId") Integer healthDetailClassId) {
        try {
            HealthClassDetailInfo result = healthClassDetailService.getHealthDetailClass(healthDetailClassId);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生小类明细失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }
}
