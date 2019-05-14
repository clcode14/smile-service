package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/7 14:55
 * @Description: TODO
 */
@RestController
@RequestMapping("/reason")
@Api(tags = "疾病原因类型配置", description = "疾病原因类型配置")
public class ReasonTypeController {

    private static final Logger LOG = LoggerFactory.getLogger(ReasonTypeController.class);

    @GetMapping("/types")
    @ApiOperation(value = "获取疾病原因类型", notes = "获取疾病原因类型")
    public ResponseBean getReasonType() {
        return null;
    }
}
