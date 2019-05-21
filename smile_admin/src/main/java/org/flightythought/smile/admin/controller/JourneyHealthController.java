package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.admin.service.JourneyHealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/journeyHealth")
@Api(tags = "养生旅程配置相关控制层", description = "养生旅程等相关信息配置接口")
public class JourneyHealthController {

    @Autowired
    private JourneyHealthService journeyHealthService;

    private static final Logger LOG = LoggerFactory.getLogger(JourneyHealthController.class);

    @GetMapping("/healthNormType")
    @ApiOperation(value = "获取体检指标", notes = "获取体检指标")
    public ResponseBean getHealthNormType(Integer pageSize, Integer pageNumber) {
        try {
            Page<HealthNormTypeEntity> result = journeyHealthService.getHealthNormType(pageSize, pageNumber);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("查询体检指标失败", e);
            return ResponseBean.error("返回体检指标失败", e.getMessage());
        }
    }

    @PostMapping("addOrUpdateHealthNormType")
    @ApiOperation(value = "新增或修改体检指标类型", notes = "新增或修改体检指标类型")
    public ResponseBean saveHealthNormType(@RequestBody HealthNormTypeEntity healthNormTypeEntity, @ApiIgnore HttpSession session) {
        try {
            HealthNormTypeEntity result = journeyHealthService.saveOrUpdateHealthNormType(healthNormTypeEntity, session);
            return ResponseBean.ok("操作成功", result);
        } catch (Exception e) {
            LOG.error("新增或修改体检指标类型失败", e);
            return ResponseBean.error("新增或修改体检指标类型失败", e.getMessage());
        }
    }

    @DeleteMapping("deleteHealthNormType/{normTypeId}")
    @ApiOperation(value = "删除体检指标类型", notes = "删除体检指标类型")
    public ResponseBean deleteHealthNormType(@PathVariable Integer normTypeId) {
        journeyHealthService.deleteHealthNormType(normTypeId);
        return ResponseBean.ok("删除成功");
    }
}
