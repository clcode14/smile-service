package org.flightythought.smile.admin.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.AppVersionEntity;
import org.flightythought.smile.admin.service.SystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SystemController
 * @CreateTime 2019/5/27 21:06
 * @Description: TODO
 */
@RestController
@RequestMapping("/system")
@Api(tags = "系统配置控制层", description = "系统配置控制层")
public class SystemController {

    private static final Logger LOG = LoggerFactory.getLogger(SystemController.class);
    @Autowired
    private SystemService systemService;


    @PostMapping("/uploadApp")
    @ApiOperation(value = "上传APP更新", notes = "上传APP更新")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "file", value = "APP安装文件"),
            @ApiImplicitParam(name = "versionId", value = "版本序号"),
            @ApiImplicitParam(name = "version", value = "版本号"),
            @ApiImplicitParam(name = "forceUpdate", value = "是否强制更新"),
            @ApiImplicitParam(name = "description", value = "描述")
    })
    public ResponseBean uploadAppFile(MultipartFile file, Integer versionId, String version,
                                      Boolean forceUpdate, String description) {
        try {
            AppVersionEntity result = systemService.uploadAppFile(file, versionId, version, forceUpdate, description);
            return ResponseBean.ok("操作成功", result);
        } catch (Exception e) {
            LOG.error("上传APP更新失败", e);
            return ResponseBean.error("上传App失败", e.getMessage());
        }
    }
}
