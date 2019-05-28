package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.dto.UserHeightWeightBirthdayDTO;
import org.flightythought.smile.appserver.dto.UserIdQueryDTO;
import org.flightythought.smile.appserver.dto.UserInfoDTO;
import org.flightythought.smile.appserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright 2019/5/27 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserController.java
 * @CreateTime 2019/5/27 15:45
 * @Description: TODO
 */
@RestController
@RequestMapping("/auth/user")
@Api(tags = "用户控制层", description = "用户信息等相关接口")
public class UserController {

    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/userInfo")
    @ApiOperation(value = "修改当前登陆用户身高，体重，生日信息", notes = "修改当前登陆用户身高，体重，生日信息")
    public ResponseBean updateUserInfo(@RequestBody UserHeightWeightBirthdayDTO userHeightWeightBirthdayDTO) {
        try {
            UserInfo userInfo = userService.updateUserInfo(userHeightWeightBirthdayDTO);
            return ResponseBean.ok("操作成功", userInfo);
        } catch (Exception e) {
            LOG.error("修改用户信息失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/userInfo/{userId}")
    @ApiOperation(value = "根据用户ID获取用户信息", notes = "根据用户ID获取用户信息")
    public ResponseBean getUserInfo(@PathVariable("userId") Long userId) {
        try {
            UserInfo userInfo = userService.getUserInfo(userId);
            return ResponseBean.ok("获取用户信息成功", userInfo);
        } catch (Exception e) {
            LOG.error("获取用户信息失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/userDetailInfoModify")
    @ApiOperation(value = "修改当前登陆用户信息", notes = "修改当前登陆用户信息，请谨慎传参，参数会被替换（头像不会被替换）")
    public ResponseBean updateUserInfoDetails(@RequestBody UserInfoDTO userInfoDTO) {
        try {
            UserInfo result = userService.updateUserInfoDetails(userInfoDTO);
            return ResponseBean.ok("修改当前登陆用户信息成功", result);
        } catch (Exception e) {
            LOG.error("修改当前登陆用户信息失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("followUser")
    @ApiOperation(value = "关注用户", notes = "关注用户")
    public ResponseBean followUser(Long userId) {
        try {
            userService.followUser(userId);
            return ResponseBean.ok("关注成功");
        } catch (Exception e) {
            LOG.error("关注用户失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("cancelFollowUser")
    @ApiOperation(value = "取消关注用户", notes = "取消关注用户")
    public ResponseBean cancelFollowUser(Long userId) {
        try {
            userService.cancelFollowUser(userId);
            return ResponseBean.ok("取消关注成功");
        } catch (Exception e) {
            LOG.error("取消关注失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/fans")
    @ApiOperation(value = "根据用户ID获取粉丝列表", notes = "根据用户ID获取粉丝列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize"),
            @ApiImplicitParam(name = "pageNumber", value = "pageNumber")
    })
    public ResponseBean getFansList(@RequestBody UserIdQueryDTO userIdQueryDTO) {
        try {
            Page<UserInfo> userInfos = userService.getFanUser(userIdQueryDTO);
            return ResponseBean.ok("获取成功", userInfos);
        } catch (Exception e) {
            LOG.error("获取粉丝列表失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/follows")
    @ApiOperation(value = "根据用户ID获取关注列表", notes = "根据用户ID获取关注列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID"),
            @ApiImplicitParam(name = "pageSize", value = "pageSize"),
            @ApiImplicitParam(name = "pageNumber", value = "pageNumber")
    })
    public ResponseBean getFollowList(@RequestBody UserIdQueryDTO userIdQueryDTO) {
        try {
            Page<UserInfo> userInfos = userService.getFollowUser(userIdQueryDTO);
            return ResponseBean.ok("获取成功", userInfos);
        } catch (Exception e) {
            LOG.error("获取关注列表失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }
}
