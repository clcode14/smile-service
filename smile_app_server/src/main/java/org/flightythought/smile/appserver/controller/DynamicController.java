package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.DynamicDetailMessageSimple;
import org.flightythought.smile.appserver.bean.DynamicDetailSimple;
import org.flightythought.smile.appserver.bean.DynamicSimple;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.database.entity.DynamicDetailMessageEntity;
import org.flightythought.smile.appserver.dto.AddDynamicDTO;
import org.flightythought.smile.appserver.dto.AddDynamicDetailDTO;
import org.flightythought.smile.appserver.dto.DynamicDetailMessageDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.flightythought.smile.appserver.service.DynamicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/auth/dynamic")
@Api(tags = "动态控制层", description = "动态相关接口控制层")
public class DynamicController {
    @Autowired
    private DynamicService dynamicService;

    private static final Logger LOG = LoggerFactory.getLogger(DynamicController.class);


    @PostMapping("/addDynamic")
    @ApiOperation(value = "新增动态", notes = "新增动态")
    public ResponseBean addDynamic(@RequestBody AddDynamicDTO addDynamicDTO) {
        try {
            DynamicSimple result = dynamicService.addDynamic(addDynamicDTO);
            return ResponseBean.ok("新增动态成功", result);
        } catch (Exception e) {
            LOG.error("新增动态失败", e);
            return ResponseBean.error("新增动态失败", e.getMessage());
        }
    }

    @PostMapping("/myDynamic")
    @ApiOperation(value = "获取我的动态", notes = "获取我的动态")
    public ResponseBean getMyDynamic(PageFilterDTO pageFilterDTO) {
        try {
            Page<DynamicSimple> result = dynamicService.getMyDynamic(pageFilterDTO);
            return ResponseBean.ok("获取我的动态成功", result);
        } catch (Exception e) {
            LOG.error("获取我的动态失败", e);
            return ResponseBean.error("获取我的动态失败", e.getMessage());
        }
    }

    @PostMapping("/addDynamicDetail")
    @ApiOperation(value = "新增动态明细", notes = "新增动态明细")
    public ResponseBean addDynamicDetail(@RequestBody AddDynamicDetailDTO addDynamicDetailDTO) {
        try {
            DynamicDetailSimple result = dynamicService.addDynamicDetail(addDynamicDetailDTO);
            return ResponseBean.ok("新增动态明细成功", result);
        } catch (Exception e) {
            LOG.error("新增动态明细失败", e);
            return ResponseBean.error("新增动态明细失败", e.getMessage());
        }
    }

    @PostMapping("/addMessage")
    @ApiOperation(value = "评论动态明细", notes = "评论动态明细和回复评论，如果是回复评论则需要传递被回复的评论主键ID，即parentId")
    public ResponseBean addDynamicDetailMessage(@RequestBody DynamicDetailMessageDTO dynamicDetailMessageDTO) {
        try {
            DynamicDetailMessageEntity dynamicDetailMessageEntity = dynamicService.addDynamicDetailMessage(dynamicDetailMessageDTO);
            return ResponseBean.ok("评论成功", dynamicDetailMessageEntity);
        } catch (Exception e) {
            LOG.error("评论失败", e);
            return ResponseBean.error("评论失败", e.getMessage());
        }
    }

    @GetMapping("/message/{dynamicDetailId}")
    @ApiOperation(value = "获取动态明细评论")
    public ResponseBean getDynamicDetailMessage(@PathVariable("dynamicDetailId") Integer dynamicDetailId) {
        try {
            List<DynamicDetailMessageSimple> result = dynamicService.getDynamicDetailMessage(dynamicDetailId);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取动态明细评论失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }
}
