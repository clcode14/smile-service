package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;

import org.flightythought.smile.admin.bean.ResourceInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.bean.RoleInfo;
import org.flightythought.smile.admin.dto.RoleDTO;
import org.flightythought.smile.admin.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName RoleController
 * @CreateTime 2019/6/20 1:25
 * @Description: TODO
 */
@Api(value = "角色管理", tags = "角色管理", description = "角色管理控制层")
@RestController
@RequestMapping("/roleManage")
public class RoleController {

    public static final Logger LOG = LoggerFactory.getLogger(RoleController.class);

    @Autowired
    private RoleService roleService;

    @PostMapping("/role")
    @ApiOperation(value = "添加角色", notes = "添加角色")
    public ResponseBean addRole(@RequestBody RoleDTO roleDTO) {
        try {
            RoleInfo result = roleService.addRole(roleDTO);
            return ResponseBean.ok("添加成功", result);
        } catch (Exception e) {
            LOG.error("添加角色失败", e);
            return ResponseBean.error("添加角色失败", e.getMessage());
        }
    }

    @PutMapping("/role")
    @ApiOperation(value = "修改角色", notes = "修改角色")
    public ResponseBean updateRole(@RequestBody RoleDTO roleDTO) {
        try {
            RoleInfo result = roleService.updateRole(roleDTO);
            return ResponseBean.ok("修改成功", result);
        } catch (Exception e) {
            LOG.error("修改角色失败", e);
            return ResponseBean.error("修改角色失败", e.getMessage());
        }
    }

    @GetMapping("/role")
    @ApiOperation(value = "获取角色", notes = "获取角色")
    public ResponseBean getRoles(int pageNumber, int pageSize) {
        try {
            Page<RoleInfo> result = roleService.getRoles(pageNumber, pageSize);
            return ResponseBean.ok("获取角色成功", result);
        } catch (Exception e) {
            LOG.error("获取角色失败", e);
            return ResponseBean.error("获取角色失败", e.getMessage());
        }
    }

    @DeleteMapping("/role")
    @ApiOperation(value = "删除角色", notes = "删除角色")
    public ResponseBean deleteRole(int roleId) {
        try {
            roleService.deleteRole(roleId);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
    
    @GetMapping("/getResource")
    @ApiOperation(value = "获取角色资源", notes = "获取角色资源")
    public ResponseBean getResource() {
        try {
            List<ResourceInfo> result = roleService.getResource();
            return ResponseBean.ok("获取角色资源成功", result);
        } catch (Exception e) {
            LOG.error("获取角色资源失败", e);
            return ResponseBean.error("获取角色资源失败", e.getMessage());
        }
    }
}
