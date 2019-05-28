package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.HealthWaySimple;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.database.entity.HealthWayMusicEntity;
import org.flightythought.smile.appserver.database.entity.HealthWayValueEntity;
import org.flightythought.smile.appserver.dto.HealthWayQueryDTO;
import org.flightythought.smile.appserver.dto.HealthWayValueDTO;
import org.flightythought.smile.appserver.dto.HealthWayValueQueryDTO;
import org.flightythought.smile.appserver.service.HealthWayService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(tags = "养生方式控制层", description = "养生方式相关接口")
@RequestMapping("/auth/healthWay")
public class HealthWayController {

    @Autowired
    private HealthWayService healthWayService;

    private static final Logger LOG = LoggerFactory.getLogger(HealthWayController.class);

    @PostMapping("/list")
    @ApiOperation(value = "获取养生方式", notes = "获取养生方式，养生方式ID可不传")
    public ResponseBean getHealthWays(@RequestBody HealthWayQueryDTO healthWayQueryDTO) {
        try {
            Page<HealthWaySimple> result = healthWayService.getHealthWays(healthWayQueryDTO);
            return ResponseBean.ok("获取养生方式", result);
        } catch (Exception e) {
            LOG.error("获取养生方式", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/music/{healthWayId}")
    @ApiOperation(value = "获取养生方式音乐列表", notes = "获取养生方式音乐列表")
    public ResponseBean getHealthWayMusic(@PathVariable Integer healthWayId) {
        try {
            List<HealthWayMusicEntity> result = healthWayService.getHealthWayMusics(healthWayId);
            return ResponseBean.ok("获取养生方式音乐列表成功", result);
        } catch (Exception e) {
            LOG.error("获取养生方式音乐失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/recordValue")
    @ApiOperation(value = "养生方式时间记录", notes = "养生方式时间记录")
    public ResponseBean recordHealthWayValue(@RequestBody HealthWayValueDTO healthWayValueDTO) {
        try {
            HealthWayValueEntity result = healthWayService.saveHealthWayValue(healthWayValueDTO);
            return ResponseBean.ok("操作成功", result);
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/queryRecord")
    @ApiOperation(value = "养生方式记录查询", notes = "养生方式记录查询")
    public ResponseBean getRecordByHealthWayId(@RequestBody HealthWayValueQueryDTO healthWayValueQueryDTO) {
        try {
            Page<HealthWayValueEntity> result = healthWayService.getHealthWayValue(healthWayValueQueryDTO);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("返回失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }
}
