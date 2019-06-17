package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.AppUpdateData;
import org.flightythought.smile.appserver.bean.HiddenConfig;
import org.flightythought.smile.appserver.bean.NoticeNumber;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.common.Constants;
import org.flightythought.smile.appserver.database.entity.PushDataEntity;
import org.flightythought.smile.appserver.database.entity.UserSettingEntity;
import org.flightythought.smile.appserver.dto.HiddenConfigDTO;
import org.flightythought.smile.appserver.dto.NoticeQueryDTO;
import org.flightythought.smile.appserver.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SystemController
 * @CreateTime 2019/5/26 22:47
 * @Description: TODO
 */
@RestController
@RequestMapping(value = "/system")
@Api(tags = "系统控制层", description = "系统控制层")
public class SystemController {
    @Autowired
    private SystemService systemService;

    private static final Logger LOG = LoggerFactory.getLogger(SystemController.class);

    @GetMapping("/update")
    @ApiOperation(value = "升级APP接口", notes = "升级APP接口")
    public ResponseBean updateApp() {
        try {
            AppUpdateData result = systemService.updateApp();
            return ResponseBean.ok("调用成功", result);
        } catch (Exception e) {
            LOG.error("调用失败", e.getMessage());
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/hiddenConfig")
    @ApiOperation(value = "获取隐藏配置项", notes = "获取隐藏配置项")
    public ResponseBean getHiddenConfig() {
        try {
            HiddenConfig result = systemService.getHiddenConfig();
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取隐私配置项失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("hiddenConfig")
    @ApiOperation(value = "配置隐藏配置项", notes = "配置隐藏配置项")
    public ResponseBean updateHiddenConfig(@RequestBody HiddenConfigDTO hiddenConfig) {
        try {
            HiddenConfig result = systemService.saveHiddenConfig(hiddenConfig);
            return ResponseBean.ok("配置成功", result);
        } catch (Exception e) {
            LOG.error("配置失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/charityFaultHiddenConfig")
    @ApiOperation(value = "获取当前用户爱心养生隐私设置", notes = "获取当前用户爱心养生隐私设置")
    @Deprecated
    public ResponseBean getCharityFaultHiddenConfig() {
        try {
            UserSettingEntity userSettingEntity = systemService.getSettingByCode(Constants.CHARITY_FAULT_HIDDEN, "false");
            return ResponseBean.ok("请求成功", userSettingEntity);
        } catch (Exception e) {
            LOG.error("获取当前用户爱心养生隐私设置失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/charityFaultHiddenConfig")
    @ApiOperation(value = "更改当前用户爱心养生隐私设置", notes = "更改当前用户爱心养生隐私设置")
    @Deprecated
    public ResponseBean updateCharityFaultHiddenConfig(Boolean charityFaultHidden) {
        try {
            UserSettingEntity result = systemService.updateSettingByCode(Constants.CHARITY_FAULT_HIDDEN, charityFaultHidden + "");
            return ResponseBean.ok("更改成功", result);
        } catch (Exception e) {
            LOG.error("更改当前用户爱心养生隐私设置失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/dynamicHiddenConfig")
    @ApiOperation(value = "获取当前用户动态评论隐私设置", notes = "获取当前用户动态评论隐私设置")
    @Deprecated
    public ResponseBean getDynamicHiddenConfig() {
        try {
            UserSettingEntity userSettingEntity = systemService.getSettingByCode(Constants.DYNAMIC_HIDDEN, "false");
            return ResponseBean.ok("请求成功", userSettingEntity);
        } catch (Exception e) {
            LOG.error("获取当前用户动态评论隐私设置失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/dynamicHiddenConfig")
    @ApiOperation(value = "更改当前用户动态评论隐私设置", notes = "更改当前用户动态评论隐私设置")
    @Deprecated
    public ResponseBean updateDynamicHiddenConfig(Boolean dynamicHidden) {
        try {
            UserSettingEntity result = systemService.updateSettingByCode(Constants.DYNAMIC_HIDDEN, dynamicHidden + "");
            return ResponseBean.ok("更改成功", result);
        } catch (Exception e) {
            LOG.error("更改当前用户爱心养生隐私设置失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/readNotice/{pushDataId}")
    @ApiOperation(value = "消息推送阅读回调接口", notes = "消息推送阅读回调接口")
    public ResponseBean readNotice(@PathVariable Integer pushDataId) {
        try {
            PushDataEntity result = systemService.readNotice(pushDataId);
            return ResponseBean.ok("请求成功", result);
        } catch (Exception e) {
            LOG.error("请求失败", e.getMessage());
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/notReadNoticeNumber")
    @ApiOperation(value = "获取当前用户未读消息数量", notes = "获取当前用户未读消息数量")
    public ResponseBean getNotReadNoticeNumber() {
        try {
            NoticeNumber noticeNumber = systemService.getNotReadNoticeNumber();
            return ResponseBean.ok("请求成功", noticeNumber);
        } catch (Exception e) {
            LOG.error("请求失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/notReadNotice")
    @ApiOperation(value = "获取未读推送消息", notes = "获取未读推送消息, 只需传递type参数, 请求完该接口，未读消息就会变为已读消息")
    public ResponseBean getNotReadNotice(@RequestBody NoticeQueryDTO noticeQueryDTO) {
        try {
            Page<PushDataEntity> result = systemService.getNotReadNotice(noticeQueryDTO);
            return ResponseBean.ok("请求成功", result);
        } catch (Exception e) {
            LOG.error("请求失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/notice")
    @ApiOperation(value = "获取推送消息记录", notes = "获取推送消息记录, 该接口不会将未读消息变为已读消息")
    public ResponseBean getNotice(@RequestBody NoticeQueryDTO noticeQueryDTO) {
        try {
            Page<PushDataEntity> result = systemService.getNotice(noticeQueryDTO);
            return ResponseBean.ok("请求成功", result);
        } catch (Exception e) {
            LOG.error("请求失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }
}
