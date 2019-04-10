package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.appserver.database.entity.JourneyEntity;
import org.flightythought.smile.appserver.dto.HealthJourneyStartDTO;
import org.flightythought.smile.appserver.service.JourneyHealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/auth/journey")
@Api(tags = "养生旅程控制层", description = "养生旅程等相关接口")
public class JourneyHealthController {
    @Autowired
    private JourneyHealthService journeyHealthService;

    private static final Logger LOG = LoggerFactory.getLogger(JourneyHealthController.class);

    @GetMapping("/healthNormType")
    @ApiOperation(value = "获取体检指标类型", notes = "获取体检指标类型")
    public ResponseBean getHealthNormType() {
        try {
            List<HealthNormTypeEntity> result = journeyHealthService.getHealthNormTypes();
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取体检指标类型失败", e);
            return ResponseBean.error("获取体检指标类型失败", e.getMessage());
        }
    }

    @ApiOperation(value = "上传多份体检报告", notes = "上传多份体检报告")
    @PostMapping("/healthReports")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "上传类型：0：开启养生旅程上传体检报告，1：结束养生旅程上传的体检报告")
    })
    public ResponseBean uploadReports(@ApiParam(value = "上传的体检报告") List<MultipartFile> reports, Integer type) {
        try {
            List<FileInfo> result = journeyHealthService.uploadReport(reports, type);
            return ResponseBean.ok("上传成功", result);
        } catch (Exception e) {
            LOG.error("上传体检报告失败", e);
            return ResponseBean.error("上传失败", e.getMessage());
        }
    }

    @ApiOperation(value = "上传一份体检报告", notes = "上传一份体检报告")
    @PostMapping("/healthReport")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "上传类型：0：开启养生旅程上传体检报告，1：结束养生旅程上传的体检报告")
    })
    public ResponseBean uploadReports(MultipartFile report, Integer type) {
        List<MultipartFile> reports = new ArrayList<>();
        try {
            reports.add(report);
            List<FileInfo> results = journeyHealthService.uploadReport(reports, type);
            return ResponseBean.ok("上传体检报告成功", results);
        } catch (Exception e) {
            LOG.error("上传体检报告失败", e);
            return ResponseBean.error("上传体检报告失败", e.getMessage());
        }
    }

    @ApiOperation(value = "开启养生旅程", notes = "开启养生旅程")
    @PostMapping("/startJourney")
    public ResponseBean startHealthJourney(@RequestBody HealthJourneyStartDTO healthJourneyStartDTO) {
        try {
            JourneyEntity result = journeyHealthService.startHealthJourney(healthJourneyStartDTO);
            return ResponseBean.ok("开启养生旅程成功", result);
        } catch (Exception e) {
            LOG.error("开启养生旅程失败", e);
            return ResponseBean.error("开启养生旅程失败", e.getMessage());
        }
    }
}
