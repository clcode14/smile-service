package org.flightythought.smile.appserver.controller;

import java.util.List;

import org.flightythought.smile.appserver.bean.HotSearchInfo;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.service.HotSearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 热门搜索控制层
 * 
 * @author cl47872
 * @version $Id: HotSearchController.java, v 0.1 Jun 29, 2019 8:52:18 PM cl47872 Exp $
 */
@RestController
@RequestMapping("/search")
@Api(value = "热门搜索", tags = "热门搜索制层", description = "热门搜索控制层")
public class HotSearchController {

    private static final Logger LOG = LoggerFactory.getLogger(HotSearchController.class);
    @Autowired
    private HotSearchService hotSearchService;

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

}
