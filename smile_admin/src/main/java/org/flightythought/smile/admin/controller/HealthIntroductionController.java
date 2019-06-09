package org.flightythought.smile.admin.controller;

import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.dto.HealthIntroductionDTO;
import org.flightythought.smile.admin.service.HealthIntroductionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 养身介绍配置控制层
 * 
 * @author cl47872
 * @version $Id: HealthIntroductionController.java, v 0.1 Jun 8, 2019 10:24:03 PM cl47872 Exp $
 */
@RestController
@RequestMapping("/health")
@Api(tags = "养身介绍配置控制层", description = "养身介绍配置控制层")
public class HealthIntroductionController {

    private static final Logger LOG = LoggerFactory.getLogger(HealthIntroductionController.class);
    @Autowired
    private HealthIntroductionService healthIntroductionService;

    @PostMapping("/saveIntroduction")
    @ApiOperation(value = "保存养身介绍", notes = "保存养身介绍")
    public ResponseBean saveIntroduction(@RequestBody HealthIntroductionDTO healthIntroductionDTO) {
        try {
            healthIntroductionService.saveIntroduction(healthIntroductionDTO.getContent());
            return ResponseBean.ok("保存养身介绍成功");
        } catch (Exception e) {
            LOG.error("保存养身介绍失败", e);
            return ResponseBean.error("保存养身介绍失败", e.getMessage());
        }
    }

    @GetMapping("/getIntroduction")
    @ApiOperation(value = "查询养身介绍", notes = "查询养身介绍")
    public ResponseBean getIntroduction() {
        try {
            String content = healthIntroductionService.getIntroduction();
            return ResponseBean.ok("查询养身介绍成功", content);
        } catch (Exception e) {
            LOG.error("查询养身介绍失败", e);
            return ResponseBean.error("查询养身介绍失败", e.getMessage());
        }
    }

}
