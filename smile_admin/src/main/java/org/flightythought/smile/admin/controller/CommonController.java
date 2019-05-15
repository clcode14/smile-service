package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CommonController.java
 * @CreateTime 2019/5/6 14:45
 * @Description: TODO
 */
@RestController
@RequestMapping("/common")
@Api(tags = "公共接口控制层", description = "平台公共接口")
public class CommonController {
    @Autowired
    private CommonService commonService;

    private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    @ApiOperation(value = "上传图片", notes = "上传图片接口")
    @PostMapping("/uploadImage")
    public ResponseBean uploadImage(@ApiParam(value = "上传的图片") MultipartFile image, @ApiParam(value = "上传图片所属模块") String model, @ApiIgnore HttpSession session) {
        try {
            ImageInfo images = commonService.uploadImage(image, model, session);
            return ResponseBean.ok("上传图片成功", images);
        } catch (FlightyThoughtException e) {
            LOG.error("上传图片失败", e);
            return ResponseBean.error("上传图片失败", e.getMessage());
        }
    }

    @ApiOperation(value = "上传图片", notes = "上传图片接口")
    @PostMapping("/uploadImages")
    public ResponseBean uploadImages(@ApiParam(value = "上传的图片") List<MultipartFile> images, @ApiParam(value = "上传图片所属模块") String model, @ApiIgnore HttpSession session) {
        try {
            List<ImageInfo> result = commonService.uploadImages(images, model, session);
            return ResponseBean.ok("上传图片成功", result);
        } catch (FlightyThoughtException e) {
            LOG.error("上传图片失败", e);
            return ResponseBean.error("上传图片失败", e.getMessage());
        }
    }

    @ApiOperation(value = "删除图片", notes = "删除图片")
    @DeleteMapping("/deleteImage/{imageId}")
    public ResponseBean deleteImage(@PathVariable Integer imageId) {
        try {
            commonService.deleteImage(imageId);
            return ResponseBean.ok("删除图片成功");
        } catch (Exception e) {
            LOG.error("删除图片失败", e);
            return ResponseBean.error("删除图片失败", e.getMessage());
        }
    }

    @ApiOperation(value = "获取解决方案下拉选", notes = "获取解决方案下拉选")
    @GetMapping("/solutionOptions")
    public ResponseBean getSolutionSelectOption() {
        try {
            List<SelectItemOption> result = commonService.getSolutionOptions();
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取失败", e);
            return ResponseBean.error("获取失败", e.getMessage());
        }
    }
}
