package org.flightythought.smile.admin.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.admin.bean.FileInfo;
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

    @ApiOperation(value = "上传文件（图片或视频、动态模块专用）", notes = "文件上传（图片或视频、动态模块专用）关于model，创建动态上传文件model：1，创建动态明细上传文件model:2，音乐配置model：3")
    @PostMapping("/uploadFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "model", value = "上传文件所属模块，用来确定保存在服务器那个文件夹下")
    })
    public ResponseBean uploadFile(@ApiParam(value = "上传的文件") MultipartFile file, String model, @ApiIgnore HttpSession session) {
        try {
            FileInfo result = commonService.uploadFile(file, model, session);
            return ResponseBean.ok("上传文件成功", result);
        } catch (Exception e) {
            LOG.error("上传文件失败", e);
            return ResponseBean.error( e.getMessage());
        }
    }

    @ApiOperation(value = "上传文件（图片或视频、动态模块专用）", notes = "多文件上传（图片或视频、动态模块专用），音乐配置model：3")
    @PostMapping("/uploadFiles")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "model", value = "上传文件所属模块，用来确定保存在服务器那个文件夹下，音乐配置model：3")
    })
    public ResponseBean uploadFiles(@ApiParam(value = "上传的文件") List<MultipartFile> files, String model, @ApiIgnore HttpSession session) {
        try {
            List<FileInfo> result = commonService.uploadFiles(files, model, session);
            return ResponseBean.ok("上传文件成功", result);
        } catch (Exception e) {
            LOG.error("上传文件失败", e);
            return ResponseBean.error(e.getMessage());
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
