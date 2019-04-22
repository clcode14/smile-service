package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.appserver.bean.DiseaseClassDetailSimple;
import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.bean.ImageInfo;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.flightythought.smile.appserver.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/auth/common")
@Api(tags = "公共接口控制层", description = "平台公共接口")
public class CommonController {
    @Autowired
    private CommonService commonService;

    private static final Logger LOG = LoggerFactory.getLogger(CommonController.class);

    @ApiOperation(value = "上传图片", notes = "上传图片接口，model具体参数如下：<table><thead><tr><td>model</td><td>所属模块</td></tr></thead><tbody><tr><td>1001</td><td>行善记录图片地址</td></tr><tr><td>1002</td><td>忏悔记录图片地址</td></tr></tbody></table>", position = 0)
    @PostMapping("/uploadImage")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "model", value = "上传图片所属模块，用来确定保存在服务器那个文件夹下")
    })
    public ResponseBean uploadImage(@ApiParam(value = "上传的图片") MultipartFile image, String model, @ApiIgnore HttpSession session) {
        try {
            ImageInfo images = commonService.uploadImage(image, model, session);
            return ResponseBean.ok("上传图片成功", images);
        } catch (FlightyThoughtException e) {
            LOG.error("上传图片失败", e);
            return ResponseBean.error("上传图片失败", e.getMessage());
        }
    }

    @ApiOperation(value = "上传图片", notes = "上传图片接口，多文件上传Swagger支持不了，请用Postman进行测试，model具体参数如下：<table><thead><tr><td>model</td><td>所属模块</td></tr></thead><tbody><tr><td>1001</td><td>行善记录图片地址</td></tr><tr><td>1002</td><td>忏悔记录图片地址</td></tr></tbody></table>", position = 1)
    @PostMapping("/uploadImages")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "model", value = "上传图片所属模块，用来确定保存在服务器那个文件夹下")
    })
    public ResponseBean uploadImages(@ApiParam(value = "上传的图片") List<MultipartFile> images, String model, @ApiIgnore HttpSession session) {
        try {
            List<ImageInfo> result = commonService.uploadImages(images, model, session);
            return ResponseBean.ok("上传图片成功", result);
        } catch (FlightyThoughtException e) {
            LOG.error("上传图片失败", e);
            return ResponseBean.error("上传图片失败", e.getMessage());
        }
    }

    @ApiOperation(value = "上传文件（图片或视频、动态模块专用）", notes = "文件上传（图片或视频、动态模块专用）关于model，创建动态上传文件model：1，创建动态明细上传文件model:2")
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
            return ResponseBean.error("上传文件失败", e.getMessage());
        }
    }

    @ApiOperation(value = "上传文件（图片或视频、动态模块专用）", notes = "多文件上传（图片或视频、动态模块专用）")
    @PostMapping("/uploadFiles")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "model", value = "上传文件所属模块，用来确定保存在服务器那个文件夹下")
    })
    public ResponseBean uploadFiles(@ApiParam(value = "上传的文件") List<MultipartFile> files, String model, @ApiIgnore HttpSession session) {
        try {
            List<FileInfo> result = commonService.uploadFiles(files, model, session);
            return ResponseBean.ok("上传文件成功", result);
        } catch (Exception e) {
            LOG.error("上传文件失败", e);
            return ResponseBean.error("上传文件失败", e.getMessage());
        }
    }


    @ApiOperation(value = "获取疾病小类", notes = "获取疾病小类，分页查询，不传递参数则返回全部数据")
    @PostMapping("/diseaseDetails")
    public ResponseBean getDiseaseDetails(@RequestBody PageFilterDTO pageFilterDTO) {
        try {
            Page<DiseaseClassDetailSimple> result = commonService.getDiseaseClassDetails(pageFilterDTO);
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取疾病小类失败", e);
            return ResponseBean.error("获取失败", e.getMessage());
        }
    }


}
