package org.flightythought.smile.admin.controller;

import java.util.List;

import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.dto.CaseEntryDTO;
import org.flightythought.smile.admin.service.CaseInputService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

/**
 * 案例录入
 *
 * @author cl47872
 * @version $Id: CaseInputController.java, v 0.1 Jun 20, 2019 1:55:13 PM cl47872 Exp $
 */
@RestController
@RequestMapping("/case")
@Api(value = "案例录入", tags = "案例录入", description = "案例录入")
public class CaseInputController {

    @Autowired
    private CaseInputService caseInputService;

    private static final Logger LOG = LoggerFactory.getLogger(CaseInputController.class);

    @GetMapping("/getDisease")
    @ApiOperation(value = "获取疾病", notes = "获取疾病", position = 1)
    public ResponseBean getDisease() {
        try {
            List<SelectItemOption> list = caseInputService.getDisease();
            return ResponseBean.ok("返回成功", list);
        } catch (Exception e) {
            LOG.error("获取疾病失败", e.getMessage());
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("/getHealthResult")
    @ApiOperation(value = "获取治疗效果", notes = "获取治疗效果", position = 1)
    public ResponseBean getHealthResult() {
        try {
            List<SelectItemOption> list = caseInputService.getHealthResult();
            return ResponseBean.ok("返回成功", list);
        } catch (Exception e) {
            LOG.error("获取失败", e.getMessage());
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("/getHealthNormType")
    @ApiOperation(value = "获取体检指标", notes = "获取体检指标", position = 1)
    public ResponseBean getHealthNormType() {
        try {
            List<SelectItemOption> list = caseInputService.getHealthNormType();
            return ResponseBean.ok("返回成功", list);
        } catch (Exception e) {
            LOG.error("获取失败", e.getMessage());
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("/getHealth")
    @ApiOperation(value = "获取养生", notes = "获取养生", position = 1)
    public ResponseBean getHealth() {
        try {
            List<SelectItemOption> list = caseInputService.getHealth();
            return ResponseBean.ok("返回成功", list);
        } catch (Exception e) {
            LOG.error("获取失败", e.getMessage());
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("/getSolution")
    @ApiOperation(value = "获取解决方案", notes = "获取解决方案", position = 1)
    public ResponseBean getSolution() {
        try {
            List<SelectItemOption> list = caseInputService.getSolution();
            return ResponseBean.ok("返回成功", list);
        } catch (Exception e) {
            LOG.error("获取疾病失败", e.getMessage());
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @PostMapping("/caseEntry")
    @ApiOperation(value = "添加案例", notes = "添加案例")
    public ResponseBean addCase(@RequestBody CaseEntryDTO caseEntryDTO, @ApiIgnore HttpSession session) {
        try {
            caseInputService.addCase(caseEntryDTO, session);
            return ResponseBean.ok("添加成功, 请到案例审核功能进行审核");
        } catch (Exception e) {
            LOG.error("添加案例失败", e);
            return ResponseBean.error("添加失败", e.getMessage());
        }
    }
}
