package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.HealthClassDetailInfo;
import org.flightythought.smile.admin.bean.HealthClassInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.HealthClassEntity;
import org.flightythought.smile.admin.dto.HealthClassDTO;
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
@RequestMapping("healthClass")
@Api(tags = "健康养生控制层", description = "健康养生相关接口")
public class HealthClassController {

    @Autowired
    private HealthClassService healthService;
    private static final Logger LOG = LoggerFactory.getLogger(HealthClassController.class);

    @GetMapping("/list")
    @ApiOperation(value = "获取养生大类", notes = "获取养生大类，分页查询，不传递参数为获取全部")
    public ResponseBean findHealthClass(Map<String, String> params) {
        try {
            Page<HealthClassInfo> result = healthService.findHealthClass(params);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生大类失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("{healthClassId}")
    @ApiOperation(value = "获取养生大类明细", notes = "根据养生大类ID获取养生大类明细")
    public ResponseBean getHealthClass(@PathVariable("healthClassId") Integer healthClassId) {
        try {
            HealthClassInfo result = healthService.getHealthClass(healthClassId);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生大类明细失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @PostMapping("/create")
    @ApiOperation(value = "养生大类添加", notes = "养生大类添加")
    public ResponseBean createHealthClass(@RequestBody HealthClassDTO healthClassDTO,@ApiIgnore HttpSession session) {
        try {
            HealthClassEntity result = healthService.saveHealthClass(healthClassDTO,session);
            return ResponseBean.ok("添加成功", result);
        } catch (Exception e) {
            LOG.error("养生大类添加失败", e);
            return ResponseBean.error("添加失败", e.getMessage());
        }
    }

    @PutMapping("/modify")
    @ApiOperation(value = "养生大类修改", notes = "养生大类修改")
    public ResponseBean modifyHealthClass(@RequestBody HealthClassDTO healthClassDTO,@ApiIgnore HttpSession session) {
        try {
            HealthClassEntity result = healthService.modifyHealthClass(healthClassDTO,session);
            return ResponseBean.ok("修改成功", result);
        } catch (Exception e) {
            LOG.error("养生大类修改失败", e);
            return ResponseBean.error("修改失败", e.getMessage());
        }
    }

    @DeleteMapping("{healthId}")
    @ApiOperation(value = "养生大类删除", notes = "养生大类删除")
    public ResponseBean deleteHealthClass(@PathVariable Long healthId,@ApiIgnore HttpSession session) {
        try {
            healthService.deleteHealthClass(healthId,session);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("养生大类删除失败", e);
            return ResponseBean.error("养生大类删除失败", e.getMessage());
        }
    }

}
