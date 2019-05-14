package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.CaseAuditInfo;
import org.flightythought.smile.admin.bean.JourneyNoteInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.service.CaseAuditService;
import org.flightythought.smile.admin.service.JourneyNoteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthController
 * @CreateTime 2019/4/9 18:02
 * @Description: TODO
 */
@RestController
@RequestMapping("/audit")
@Api(tags = "案例管理", description = "案例管理相关接口")
public class CaseAuditController {

    @Autowired
    private CaseAuditService caseAuditService;

    @Autowired
    private JourneyNoteService journeyNoteService;

    private static final Logger LOG = LoggerFactory.getLogger(CaseAuditController.class);

    @GetMapping("/list")
    @ApiOperation(value = "案例审核列表", notes = "获取案例审核列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页码"),
            @ApiImplicitParam(name = "pageSize", value = "分页大小"),
            @ApiImplicitParam(name = "audit", value = "是否审核 ，0未审核，1已审核"),
            @ApiImplicitParam(name = "recoverCase", value = "是否评为案例 ，0不是，1是")

    })
    public ResponseBean findCaseAudits(@RequestParam Map<String, String> params) {
        try {
            Page<CaseAuditInfo> result = caseAuditService.findAll(params);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("案例审核列表失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }


    @GetMapping("/doAudit")
    @ApiOperation(value = "案例审核", notes = "案例审核")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "journeyId", value = "旅程id"),
            @ApiImplicitParam(name = "solutionIds", value = "解决方案ids，多个以逗号分开"),
    })
    public ResponseBean doAudit(@RequestParam Map<String, String> params, @ApiIgnore HttpSession session) {
        try {
            caseAuditService.doAudit(params, session);
            return ResponseBean.ok("案例审核成功");
        } catch (Exception e) {
            LOG.error("案例审核失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("/case/add")
    @ApiOperation(value = "案例添加", notes = "案例添加")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "journeyId", value = "旅程id"),
            @ApiImplicitParam(name = "solutionIds", value = "解决方案ids，多个以逗号分开"),
            @ApiImplicitParam(name = "coverImage", value = "养生旅程对应的封面图")
    })
    public ResponseBean addCase(@RequestParam Map<String, String> params, @ApiIgnore HttpSession session) {
        try {
            caseAuditService.addCase(params, session);
            return ResponseBean.ok("案例审核成功");
        } catch (Exception e) {
            LOG.error("案例审核失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }


    @DeleteMapping("/case/{id}")
    @ApiOperation(value = "案例删除", notes = "案例删除")
    public ResponseBean deleteCase(@PathVariable Integer id, @ApiIgnore HttpSession session) {
        try {
            caseAuditService.cancelCase(id);
            return ResponseBean.ok("案例删除成功");
        } catch (Exception e) {
            LOG.error("案例删除失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }


    @GetMapping("/journey-notes")
    @ApiOperation(value = "养生日记列表", notes = "养生日记")
    public ResponseBean journeyNotes(@RequestParam Map<String, String> params) {
        try {
            List<JourneyNoteInfo> journeyNoteInfos = journeyNoteService.findAll(params);
            return ResponseBean.ok("返回成功", journeyNoteInfos);
        } catch (Exception e) {
            LOG.error("案例审核列表失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

}
