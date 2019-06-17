package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.RecoverCaseSimple;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.bean.UserCharityFaultRecord;
import org.flightythought.smile.appserver.dto.SearchDTO;
import org.flightythought.smile.appserver.service.SearchService;
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
 * @ClassName SearchController
 * @CreateTime 2019/6/9 22:30
 * @Description:
 */
@Api(tags = "搜索控制层", description = "搜索控制层")
@RestController
@RequestMapping("/search")
public class SearchController {

    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);

    @Autowired
    private SearchService searchService;

    @PostMapping("/recoverCase")
    @ApiOperation(value = "搜索康复案例", notes = "搜索康复案例")
    public ResponseBean searchRecoverCase(@RequestBody SearchDTO searchDTO) {
        try {
            Page<RecoverCaseSimple> result = searchService.searchRecoverCase(searchDTO);
            return ResponseBean.ok("搜索成功", result);
        } catch (Exception e) {
            LOG.error("搜索失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/charityFault")
    @ApiOperation(value = "搜索行善过失（爱心养生）", notes = "搜索行善过失（爱心养生）")
    public ResponseBean searchCharityFault(@RequestBody SearchDTO searchDTO) {
        try {
            Page<UserCharityFaultRecord> result = searchService.searchCharityFaultRecord(searchDTO);
            return ResponseBean.ok("搜索成功", result);
        } catch (Exception e) {
            LOG.error("搜索失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }


}
