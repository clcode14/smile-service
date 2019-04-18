package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.dto.UserHeightWeightBirthdayDTO;
import org.flightythought.smile.appserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }
}
