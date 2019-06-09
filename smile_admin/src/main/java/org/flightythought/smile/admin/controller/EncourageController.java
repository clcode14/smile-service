package org.flightythought.smile.admin.controller;

import java.util.List;

import org.flightythought.smile.admin.bean.EncourageInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.dto.EncourageDTO;
import org.flightythought.smile.admin.service.EncourageService;
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
 * 鼓励话语配置控制层
 * 
 * @author cl47872
 * @version $Id: EncourageController.java, v 0.1 Jun 8, 2019 9:06:19 PM cl47872 Exp $
 */
@RestController
@RequestMapping("/encourage")
@Api(tags = "鼓励话语配置控制层", description = "鼓励话语配置控制层")
public class EncourageController {

    private static final Logger LOG = LoggerFactory.getLogger(EncourageController.class);
    @Autowired
    private EncourageService encourageService;

    @PostMapping("/entity")
    @ApiOperation(value = "添加鼓励话语", notes = "添加添加鼓励话语")
    public ResponseBean addEncourage(@RequestBody EncourageDTO encourageDTO) {
        try {
            encourageService.addEncourage(encourageDTO);
            return ResponseBean.ok("添加成功");
        } catch (Exception e) {
            LOG.error("添加鼓励话语失败", e);
            return ResponseBean.error("添加鼓励话语失败", e.getMessage());
        }
    }

    @GetMapping("/list")
    @ApiOperation(value = "查询鼓励话语", notes = "查询鼓励话语")
    public ResponseBean getEncourageList() {
        try {
            List<EncourageInfo> result = encourageService.getEncourageList();
            return ResponseBean.ok("查询鼓励话语成功", result);
        } catch (Exception e) {
            LOG.error("查询鼓励话语失败", e);
            return ResponseBean.error("查询鼓励话语失败", e.getMessage());
        }
    }

    @GetMapping("/entity/{id}")
    @ApiOperation(value = "查询鼓励话语详情", notes = "查询鼓励话语详情")
    public ResponseBean getEncourage(@PathVariable Integer id) {
        try {
            EncourageInfo encourageInfo = encourageService.getEncourage(id);
            return ResponseBean.ok("查询鼓励话语详情成功", encourageInfo);
        } catch (Exception e) {
            LOG.error("查询鼓励话语详情失败", e);
            return ResponseBean.error("查询鼓励话语详情失败", e.getMessage());
        }
    }

    @PutMapping("/entity")
    @ApiOperation(value = "更新鼓励话语", notes = "更新鼓励话语")
    public ResponseBean updateEncourage(@RequestBody EncourageDTO encourageDTO) {
        try {
            encourageService.updateEncourage(encourageDTO);
            return ResponseBean.ok("更新成功");
        } catch (Exception e) {
            LOG.error("更新失败", e);
            return ResponseBean.error("更新失败", e.getMessage());
        }
    }
    
    @DeleteMapping("/entity")
    @ApiOperation(value = "删除鼓励话语", notes = "删除鼓励话语")
    public ResponseBean deleteEncourage(Integer id) {
        try {
            encourageService.deleteEncourage(id);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
