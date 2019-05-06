package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.appserver.bean.DynamicDetailMessageSimple;
import org.flightythought.smile.appserver.bean.DynamicDetailSimple;
import org.flightythought.smile.appserver.bean.DynamicSimple;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.database.entity.DynamicDetailMessageEntity;
import org.flightythought.smile.appserver.dto.*;
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
    public ResponseBean getMyDynamic(@RequestBody PageFilterDTO pageFilterDTO) {
        try {
            Page<DynamicSimple> result = dynamicService.getMyDynamic(pageFilterDTO);
            return ResponseBean.ok("获取我的动态成功", result);
        } catch (Exception e) {
            LOG.error("获取我的动态失败", e);
            return ResponseBean.error("获取我的动态失败", e.getMessage());
        }
    }

    @PostMapping("/otherDynamic")
    @ApiOperation(value = "获取指定用户或全部用户公开动态信息", notes = "获取指定用户或全部用户所建立的公开动态信息，获取全部用户用户ID传NULL")
    public ResponseBean getOtherDynamic(@RequestBody UserIdQueryDTO userIdQueryDTO) {
        try {
            Page<DynamicSimple> result = dynamicService.getOtherDynamicWithNoHidden(userIdQueryDTO);
            return ResponseBean.ok("获取动态信息成功", result);
        } catch (Exception e) {
            LOG.error("获取动态信息失败", e);
            return ResponseBean.error("获取动态信息失败", e.getMessage());
        }
    }

    @GetMapping("/{dynamicId}")
    @ApiOperation(value = "获取动态信息(点击动态时调用，如果查看的动态是非自身创建会增加浏览数)", notes = "如果动态为自己创建会返回全部信息，" +
            "如果动态是其他人所创建，会判断该动态是否公开，如果是公开则返回其对应的信息以及公开的动态明细，否则返回失败")
    public ResponseBean getDynamic(@PathVariable Integer dynamicId) {
        try {
            DynamicSimple result = dynamicService.getDynamicById(dynamicId);
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取失败", e);
            return ResponseBean.error("获取失败", e.getMessage());
        }
    }

    @PostMapping("/dynamicDetails")
    @ApiOperation(value = "根据动态ID获取动态明细内容", notes = "根据动态ID获取动态明细内容")
    public ResponseBean getDynamicDetails(@RequestBody DynamicDetailQueryDTO dynamicDetailQueryDTO) {
        try {
            Page<DynamicDetailSimple> result = dynamicService.getDynamicDetailByDynamicId(dynamicDetailQueryDTO);
            return ResponseBean.ok("获取动态明细内容成功", result);
        } catch (Exception e) {
            LOG.error("获取动态明细内容失败", e);
            return ResponseBean.error("获取动态明细内容失败", e.getMessage());
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
    @ApiOperation(value = "获取动态明细评论", notes = "获取动态明细评论集合")
    public ResponseBean getDynamicDetailMessage(@PathVariable("dynamicDetailId") Integer dynamicDetailId) {
        try {
            List<DynamicDetailMessageSimple> result = dynamicService.getDynamicDetailMessage(dynamicDetailId);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取动态明细评论失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @PostMapping("/likeDynamicDetail")
    @ApiOperation(value = "动态明细点赞或取消点赞", notes = "用户点赞或取消点赞动态明细")
    @ApiImplicitParams(@ApiImplicitParam(value = "动态明细ID", name = "dynamicDetailId"))
    public ResponseBean likeDynamicDetail(Integer dynamicDetailId) {
        try {
            dynamicService.likeDynamicDetail(dynamicDetailId);
            return ResponseBean.ok("操作成功");
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }

    @PostMapping("/likeDynamicDetailMessage")
    @ApiOperation(value = "动态明细评论点赞或取消点赞", notes = "用户点赞或取消点赞动态明细的评论")
    @ApiImplicitParams(@ApiImplicitParam(value = "动态明细评论ID", name = "messageId"))
    public ResponseBean likeDynamicDetailMessage(Integer messageId) {
        try {
            dynamicService.likeDynamicDetailMessage(messageId);
            return ResponseBean.ok("操作成功");
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }
}
