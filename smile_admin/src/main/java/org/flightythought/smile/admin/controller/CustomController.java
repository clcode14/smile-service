package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.CustomEntity;
import org.flightythought.smile.admin.database.entity.OfficeEntity;
import org.flightythought.smile.admin.dto.CustomDTO;
import org.flightythought.smile.admin.dto.OfficeDTO;
import org.flightythought.smile.admin.service.CustomService;
import org.flightythought.smile.admin.service.OfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/custom")
@Api(value = "其他配置", tags = "客服电话", description = "客服电话")
public class CustomController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomController.class);

    @Autowired
    private CustomService customService;

    @GetMapping("/list")
    @ApiOperation(value = "客服列表", notes = "客服列表", position = 1)
    public ResponseBean findAllCustom(Map<String, String> params) {
        try {
            Page<CustomEntity> customEntities = customService.findAll(params);
            return ResponseBean.ok("查询成功", customEntities);
        } catch (Exception e) {
            LOG.error("查询客服列表失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }

    @GetMapping("{id}")
    @ApiOperation(value = "客服详情", notes = "客服详情", position = 1)
    public ResponseBean findCustom(@PathVariable Long id) {
        try {
            CustomEntity customEntity = customService.findCustom(id);
            return ResponseBean.ok("查询成功", customEntity);
        } catch (Exception e) {
            LOG.error("查询客服列表失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增客服", notes = "新增客服", position = 1)
    public ResponseBean createCustom(@RequestBody @ApiParam CustomDTO customDTO, @ApiIgnore HttpSession session) {
        try {
            CustomEntity customEntity = customService.save(customDTO, session);
            return ResponseBean.ok("新增成功", customEntity);
        } catch (Exception e) {
            LOG.error("新增客服失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }

    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改客服", notes = "修改客服", position = 1)
    public ResponseBean modifyCustom(@RequestBody @ApiParam CustomDTO customDTO, @ApiIgnore HttpSession session) {
        try {
            CustomEntity customEntity = customService.modify(customDTO, session);
            return ResponseBean.ok("修改成功", customEntity);
        } catch (Exception e) {
            LOG.error("修改客服失败", e);
            return ResponseBean.error("修改失败", e.getMessage());
        }

    }

    @DeleteMapping("/delete/{id}")
    @ApiOperation(value = "删除客服", notes = "删除客服", position = 1)
    public ResponseBean modifyCustom(@PathVariable Long id, @ApiIgnore HttpSession session) {
        try {
            customService.deleteById(id);
            return ResponseBean.ok("删除客服成功");
        } catch (Exception e) {
            LOG.error("删除客服失败", e);
            return ResponseBean.error("删除客服失败", e.getMessage());
        }

    }

}
