package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.CommoditySimple;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.dto.CommodityQueryDTO;
import org.flightythought.smile.appserver.service.CommodityService;
import org.flightythought.smile.appserver.vo.CommoditySimpleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 13:53
 * @Description: TODO
 */
@RestController
@RequestMapping("/commodity")
@Api(tags = "相关商品控制层", description = "相关商品控制层")
public class CommodityController {

    private static final Logger LOG = LoggerFactory.getLogger(CommodityController.class);

    @Autowired
    private CommodityService commodityService;

    @PostMapping("/result")
    @ApiOperation(value = "获取相关商品", notes = "可以根据solutionId(解决方案ID)、commodityId(商品ID)，分页查询获取数据")
    public ResponseBean getCommodities(@RequestBody CommodityQueryDTO commodityQueryDTO) {
        try {
            Page<CommoditySimple> result = commodityService.getCommofities(commodityQueryDTO);
            Page<CommoditySimpleVo> voResult = result.map(entity -> {
                CommoditySimpleVo vo = new CommoditySimpleVo();
                BeanUtils.copyProperties(entity, vo);
                return vo;
            });
            return ResponseBean.ok("获取成功", voResult);
        } catch (Exception e) {
            LOG.error("获取相关商品失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }
}
