package org.flightythought.smile.admin.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.admin.bean.FileInfo;
import org.flightythought.smile.admin.bean.HealthClassInfo;
import org.flightythought.smile.admin.bean.HealthWay;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.HealthEntity;
import org.flightythought.smile.admin.database.entity.HealthWayEntity;
import org.flightythought.smile.admin.dto.HealthClassDTO;
import org.flightythought.smile.admin.dto.HealthWayDTO;
import org.flightythought.smile.admin.service.HealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthController
 * @CreateTime 2019/4/9 18:02
 * @Description: TODO
 */
@RestController
@RequestMapping("/health")
@Api(value = "养生配置", tags = "健康养生控制层", description = "健康养生相关接口")
public class HealthController {

    @Autowired
    private HealthService healthService;
    private static final Logger LOG = LoggerFactory.getLogger(HealthController.class);

    @GetMapping("/list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页数从1开始"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示个数")
    })
    @ApiOperation(value = "获取养生", notes = "获取养生，分页查询")
    public ResponseBean findHealthClass(Map<String, String> params) {
        try {
            Page<HealthClassInfo> result = healthService.findHealthClass(params);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生大类失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @GetMapping("/{healthClassId}")
    @ApiOperation(value = "获取养生大类明细", notes = "根据养生大类ID获取养生大类明细")
    public ResponseBean getHealthClass(@PathVariable("healthClassId") Integer healthClassId) {
        try {
            HealthClassInfo result = healthService.getHealthClass(healthClassId);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生大类明细失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @PostMapping("/create")
    @ApiOperation(value = "添加养生", notes = "添加养生")
    public ResponseBean createHealthClass(@RequestBody HealthClassDTO healthClassDTO, @ApiIgnore HttpSession session) {
        try {
            HealthEntity result = healthService.saveHealthClass(healthClassDTO, session);
            return ResponseBean.ok("添加成功", result);
        } catch (Exception e) {
            LOG.error("养生大类添加失败", e);
            return ResponseBean.error("添加失败", e.getMessage());
        }
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改养生", notes = "修改养生")
    public ResponseBean modifyHealthClass(@RequestBody HealthClassDTO healthClassDTO, @ApiIgnore HttpSession session) {
        try {
            healthService.modifyHealthClass(healthClassDTO, session);
            return ResponseBean.ok("修改成功");
        } catch (Exception e) {
            LOG.error("养生大类修改失败", e);
            return ResponseBean.error("修改失败", e.getMessage());
        }
    }

    @DeleteMapping("{healthId}")
    @ApiOperation(value = "删除养生", notes = "删除养生")
    public ResponseBean deleteHealthClass(@PathVariable Integer healthId, @ApiIgnore HttpSession session) {
        try {
            healthService.deleteHealthClass(healthId, session);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("养生大类删除失败", e);
            return ResponseBean.error("养生大类删除失败", e.getMessage());
        }
    }

    @PostMapping("/healthWay")
    @ApiOperation(value = "新增养生方式", notes = "新增养生方式")
    public ResponseBean addHealthWay(@RequestBody HealthWayDTO healthWayDTO) {
        try {
            HealthWayEntity result = healthService.addHealthWay(healthWayDTO);
            return ResponseBean.ok("新增成功", result);
        } catch (Exception e) {
            LOG.error("新增养生方式失败", e);
            return ResponseBean.error("新增养生方式失败", e.getMessage());
        }
    }

    @GetMapping("/healthWay")
    @ApiOperation(value = "获取查询养生方式", notes = "获取查询养生方式")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页数从1开始"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示个数")
    })
    public ResponseBean getHealthWay(@RequestParam(required = false) Map<String, String> params) {
        try {
            Page<HealthWay> result = healthService.getHealthWays(params);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("返回失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @PutMapping("/healthWay")
    @ApiOperation(value = "修改养生方式", notes = "修改养生方式")
    public ResponseBean modifyHealthWay(@RequestBody HealthWayDTO healthWayDTO) {
        try {
            healthService.modifyHealthWay(healthWayDTO);
            return ResponseBean.ok("操作成功");
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }

    @DeleteMapping("/healthWay/{healthWayId}")
    @ApiOperation(value = "删除养生方式", notes = "删除养伤方式")
    public ResponseBean deleteHealthWay(@PathVariable Integer healthWayId) {
        try {
            healthService.deleteHealthWay(healthWayId);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除养生方式失败", e);
            return ResponseBean.error("删除养生方式失败", e.getMessage());
        }
    }

    @ApiOperation(value = "上传文件", notes = "文件上传")
    @PostMapping("/uploadFile")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "musicName", value = "音乐名称")
    })
    public ResponseBean uploadFile(@ApiParam(value = "上传的文件") MultipartFile file, String musicName, @ApiIgnore HttpSession session) {
        try {
            FileInfo result = healthService.uploadFile(file, musicName, session);
            return ResponseBean.ok("上传文件成功", result);
        } catch (Exception e) {
            LOG.error("上传文件失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/musicList")
    @ApiOperation(value = "查询音乐列表", notes = "查询音乐列表")
    public ResponseBean getMusicFiles(Integer pageNumber, Integer pageSize, String musicName) {
        if (pageNumber == null || pageNumber == 0 || pageSize == null || pageSize == 0) {
            // 获取全部音乐
            try {
                List<FileInfo> result = healthService.getAllMusicFile();
                return ResponseBean.ok("获取成功", result);
            } catch (Exception e) {
                LOG.error("获取音乐列表失败", e);
                return ResponseBean.error(e.getMessage());
            }
        } else {
            // 查询音乐
            try {
                Page<FileInfo> result = healthService.getMusics(pageNumber, pageSize, musicName);
                return ResponseBean.ok("查询成功", result);
            } catch (Exception e) {
                LOG.error("查询音乐列表失败", e);
                return ResponseBean.error(e.getMessage());
            }
        }
    }

    @ApiOperation(value = "删除图片", notes = "删除图片")
    @DeleteMapping("/deleteFile/{fileId}")
    public ResponseBean deleteFile(@PathVariable Integer fileId) {
        try {
            healthService.deleteFile(fileId);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
