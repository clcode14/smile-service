package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.bean.SolutionPage;
import org.flightythought.smile.appserver.bean.SolutionSimple;
import org.flightythought.smile.appserver.dto.HealthOrDiseaseQuerySolutionDTO;
import org.flightythought.smile.appserver.dto.SolutionQueryDTO;
import org.flightythought.smile.appserver.service.SolutionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/solution")
@Api(tags = "解决方案控制层", description = "获取解决方案相关接口")
public class SolutionController {
    @Autowired
    private SolutionService solutionService;

    private static final Logger LOG = LoggerFactory.getLogger(SolutionController.class);


    @ApiOperation(value = "获取解决方案1（适用于：千病万方模块调用）", notes = "可以根据疾病小类ID或疾病原因ID查询获取解决方案，" +
            "疾病小类ID、疾病原因ID以及分页参数都不是必填项，可以根据自己需要选择性的传参获取对应的解决方案")
    @PostMapping("/results")
    public ResponseBean getSolutions(@RequestBody SolutionQueryDTO solutionQueryDTO) {
        try {
            Page<SolutionSimple> solutionSimples = solutionService.getSolutionSimples(solutionQueryDTO);
            return ResponseBean.ok("获取成功", solutionSimples);
        } catch (Exception e) {
            LOG.error("获取解决方案失败", e);
            return ResponseBean.error("获取解决方案失败", e.getMessage());
        }
    }

    @ApiOperation(value = "获取解决方案2（适用于：结束养生模块调用）", notes = "根据养生ID或疾病小类ID，可选择分页查询获取解决方案，重复部分滤重返回")
    @PostMapping("/resultsByHD")
    public ResponseBean getSolutions(@RequestBody HealthOrDiseaseQuerySolutionDTO querySolutionDTO) {
        try {
            Page<SolutionSimple> result = solutionService.getSolutionSimples(querySolutionDTO);
            return ResponseBean.ok("获取解决方案成功", result);
        } catch (Exception e) {
            LOG.error("获取解决方案失败", e);
            return ResponseBean.error("获取解决方案失败", e.getMessage());
        }
    }

    @ApiOperation(value = "获取解决方案页面对象", notes = "根据解决方案ID获取解决方案页面对象内容")
    @GetMapping("/page/{solutionId}")
    public ResponseBean getSolutionPage(@PathVariable("solutionId") Integer solutionId) {
        try {
            SolutionPage solutionPage = solutionService.getSolutionPage(solutionId);
            return ResponseBean.ok("返回成功", solutionPage);
        } catch (Exception e) {
            LOG.error("获取解决方案页面对象失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }
}
