package org.flightythought.smile.admin.controller;

import java.util.List;

import org.flightythought.smile.admin.bean.HotSearchInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.dto.HotSearchDTO;
import org.flightythought.smile.admin.service.HotSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 热门搜索配置控制层
 * 
 * @author cl47872
 * @version $Id: HotSearchController.java, v 0.1 Jun 29, 2019 8:52:18 PM cl47872 Exp $
 */
@RestController
@RequestMapping("/search")
@Api(value = "热门搜索配置", tags = "热门搜索配置控制层", description = "热门搜索配置控制层")
public class HotSearchController {

    private static final Logger LOG = LoggerFactory.getLogger(HotSearchController.class);
    @Autowired
    private HotSearchService hotSearchService;

    @PostMapping("/entity")
    @ApiOperation(value = "添加热门搜索", notes = "添加添加热门搜索")
    public ResponseBean addHotSearch(@RequestBody HotSearchDTO hotSearchDTO) {
        try {
            hotSearchService.addHotSearch(hotSearchDTO);
            return ResponseBean.ok("添加成功");
        } catch (Exception e) {
            LOG.error("添加热门搜索失败", e);
            return ResponseBean.error("添加热门搜索失败", e.getMessage());
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询热门搜索", notes = "查询热门搜索")
    public ResponseBean getHotSearchList() {
        try {
            List<HotSearchInfo> result = hotSearchService.getHotSearchList();
            return ResponseBean.ok("查询热门搜索成功", result);
        } catch (Exception e) {
            LOG.error("查询热门搜索失败", e);
            return ResponseBean.error("查询热门搜索失败", e.getMessage());
        }
    }

    @GetMapping("/entity/{id}")
    @ApiOperation(value = "查询热门搜索详情", notes = "查询热门搜索详情")
    public ResponseBean getHotSearch(@PathVariable Integer id) {
        try {
            HotSearchInfo hotSearchInfo = hotSearchService.getHotSearch(id);
            return ResponseBean.ok("查询热门搜索详情成功", hotSearchInfo);
        } catch (Exception e) {
            LOG.error("查询热门搜索详情失败", e);
            return ResponseBean.error("查询热门搜索详情失败", e.getMessage());
        }
    }

    @PutMapping("/entity")
    @ApiOperation(value = "更新热门搜索", notes = "更新热门搜索")
    public ResponseBean updateHotSearch(@RequestBody HotSearchDTO hotSearchDTO) {
        try {
            hotSearchService.updateHotSearch(hotSearchDTO);
            return ResponseBean.ok("更新成功");
        } catch (Exception e) {
            LOG.error("更新失败", e);
            return ResponseBean.error("更新失败", e.getMessage());
        }
    }

    @DeleteMapping("/entity")
    @ApiOperation(value = "删除热门搜索", notes = "删除热门搜索")
    public ResponseBean deleteHotSearch(Integer id) {
        try {
            hotSearchService.deleteHotSearch(id);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
