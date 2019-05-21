package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.OfficeSimple;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.dto.OfficeQueryDTO;
import org.flightythought.smile.appserver.service.OfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeController
 * @CreateTime 2019/5/21 23:48
 * @Description: TODO
 */
@RestController
@RequestMapping("/office")
@Api(tags = "相关机构控制层", description = "相关机构控制层")
public class OfficeController {

    private static final Logger LOG = LoggerFactory.getLogger(OfficeController.class);

    @Autowired
    private OfficeService officeService;

    @PostMapping("/result")
    @ApiOperation(value = "相关机构查询接口", notes = "可以根据solutionId(解决方案ID)、officeId(相关机构ID)、分页查询获得结果")
    public ResponseBean getOffices(@RequestBody OfficeQueryDTO officeQueryDTO) {
        try {
            Page<OfficeSimple> result = officeService.getOffices(officeQueryDTO);
            return ResponseBean.ok("查询成功", result);
        } catch (Exception e) {
            LOG.error("查询失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }
}
