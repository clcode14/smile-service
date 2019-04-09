package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.DiseaseReason;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.database.entity.DiseaseReasonEntity;
import org.flightythought.smile.appserver.dto.AboutDiseaseDetailQueryDTO;
import org.flightythought.smile.appserver.service.DiseaseReasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/auth/disease")
@Api(tags = "疾病原因控制层", description = "疾病原因以及对应的解决方案获取")
public class DiseaseReasonController {

    @Autowired
    private DiseaseReasonService diseaseReasonService;

    private static final Logger LOG = LoggerFactory.getLogger(DiseaseReasonController.class);


    @ApiOperation(value = "获取疾病原因", notes = "根据疾病小类ID获取疾病原因")
    @PostMapping("/reason")
    public ResponseBean getDiseaseReason(@RequestBody AboutDiseaseDetailQueryDTO diseaseDetailQueryDTO) {
        try {
            Page<DiseaseReasonEntity> diseaseReasonEntities = diseaseReasonService.getDiseaseReason(diseaseDetailQueryDTO);
            return ResponseBean.ok("返回成功", diseaseReasonEntities);
        } catch (Exception e) {
            LOG.error("返回失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @ApiOperation(value = "获取疾病原因类型", notes = "获取疾病原因类型")
    @GetMapping("diseaseReasonType")
    public ResponseBean getDiseaseReasonType() {
        List<Map<String, String>> result = diseaseReasonService.getDiseaseTypes();
        return ResponseBean.ok("返回成功", result);
    }

    @ApiOperation(value = "获取疾病原因详情", notes = "获取疾病原因详情")
    @GetMapping("/reason/{reasonId}")
    public ResponseBean getDiseaseReasonInfo(@PathVariable String reasonId) {
        try {
            DiseaseReason diseaseReason = diseaseReasonService.getDiseaseReasonInfo(reasonId);
            if (diseaseReason == null) {
                return ResponseBean.error("没有找到对应的原因详情");
            }
            return ResponseBean.ok("获取疾病原因详情成功", diseaseReason);
        } catch (Exception e) {
            LOG.error("获取疾病原因详情失败", e);
            return ResponseBean.error("获取疾病原因详情失败", e.getMessage());
        }
    }
}
