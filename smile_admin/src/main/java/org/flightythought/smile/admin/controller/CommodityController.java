package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.Commodity;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.dto.CommodityDTO;
import org.flightythought.smile.admin.dto.CommodityQueryDTO;
import org.flightythought.smile.admin.service.CommodityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 14:54
 * @Description: TODO
 */
@RestController
@RequestMapping("/commodity")
@Api(value = "商品管理", tags = "相关商品控制层", description = "相关商品控制层")
public class CommodityController {

    private static final Logger LOG = LoggerFactory.getLogger(CommodityController.class);
    @Autowired
    private CommodityService commodityService;

    @PostMapping("/entity")
    @ApiOperation(value = "添加商品", notes = "添加商品")
    public ResponseBean addCommodity(@RequestBody CommodityDTO commodityDTO) {
        try {
            commodityService.addCommodityEntity(commodityDTO);
            return ResponseBean.ok("添加成功");
        } catch (Exception e) {
            LOG.error("添加商品失败", e);
            return ResponseBean.error("添加商品失败", e.getMessage());
        }
    }

    @PostMapping("/list")
    @ApiOperation(value = "查询商品", notes = "查询商品")
    public ResponseBean getCommodities(@RequestBody CommodityQueryDTO commodityQueryDTO) {
        try {
            Page<Commodity> result = commodityService.getCommodities(commodityQueryDTO);
            return ResponseBean.ok("查询商品成功", result);
        } catch (Exception e) {
            LOG.error("查询商品失败", e);
            return ResponseBean.error("查询商品失败", e.getMessage());
        }
    }

    @GetMapping("/entity/{commodityId}")
    @ApiOperation(value = "查询商品详情", notes = "查询商品详情")
    public ResponseBean getCommodity(@PathVariable Integer commodityId) {
        try {
            Commodity commodity = commodityService.getCommodity(commodityId);
            return ResponseBean.ok("查询商品详情成功", commodity);
        } catch (Exception e) {
            LOG.error("查询商品详情失败", e);
            return ResponseBean.error("查询商品详情失败", e.getMessage());
        }
    }

    @PutMapping("/entity")
    @ApiOperation(value = "更新商品", notes = "更新商品")
    public ResponseBean updateCommodity(@RequestBody CommodityDTO commodityDTO) {
        try {
            commodityService.updateCommodityEntity(commodityDTO);
            return ResponseBean.ok("更新成功");
        } catch (Exception e) {
            LOG.error("更新失败", e);
            return ResponseBean.error("更新失败", e.getMessage());
        }
    }

    @PutMapping("/sell")
    @ApiOperation(value = "售卖/停售商品", notes = "售卖/停售商品")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "commodityId", value = "商品ID"),
            @ApiImplicitParam(name = "opType", value = "操作类型，0-上架，1-下架")
    })
    public ResponseBean updateCommodity(Integer commodityId, Integer opType) {
        try {
            commodityService.updateCommodityStatus(commodityId, opType);
            return ResponseBean.ok("售卖/停售商品成功");
        } catch (Exception e) {
            LOG.error("售卖/停售商品失败", e);
            return ResponseBean.error("售卖/停售商品失败", e.getMessage());
        }
    }

    @DeleteMapping("/entity")
    @ApiOperation(value = "删除商品", notes = "删除商品")
    public ResponseBean deleteCommodity(Integer commodityId) {
        try {
            commodityService.deleteCommodity(commodityId);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
