package org.flightythought.smile.admin.controller;

import org.flightythought.smile.admin.bean.AppUserInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.dto.AppUserQueryDTO;
import org.flightythought.smile.admin.service.AppUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/user")
@Api(value = "用户管理", tags = "用户管理")
public class UserController {
    
    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
    
    @Autowired
    private AppUserService appUserService;
    
    @GetMapping("/list")
    @ApiOperation(value = "用户信息列表", notes = "获取用户信息列表")
    public ResponseBean findUsers(@RequestBody AppUserQueryDTO appUserQueryDTO) {
        try {
            Page<AppUserInfo> result = appUserService.findUsers(appUserQueryDTO);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("查询用户信息列表失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }
}
