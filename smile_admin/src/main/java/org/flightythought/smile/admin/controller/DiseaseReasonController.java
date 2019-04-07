package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.DiseaseReasonEntity;
import org.flightythought.smile.admin.dto.DiseaseReasonDTO;
import org.flightythought.smile.admin.service.DiseaseReasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/diseaseReason")
@Api(value = "疾病配置", tags = "疾病原因")
public class DiseaseReasonController {

    @Autowired
    private DiseaseReasonService diseaseReasonService;

    private static final Logger LOG = LoggerFactory.getLogger(DiseaseReasonController.class);

    @PostMapping("/save")
    @ApiOperation(value = "新增或修改疾病原因")
    public ResponseBean saveDiseaseReason(@RequestBody DiseaseReasonDTO diseaseReasonDTO, @ApiIgnore HttpSession session) {
        try {
            DiseaseReasonEntity diseaseReasonEntity = diseaseReasonService.saveDiseaseReason(diseaseReasonDTO, session);
            return ResponseBean.ok("操作成功", diseaseReasonEntity);
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }
}
