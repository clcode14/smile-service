package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.appserver.bean.*;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.DynamicDetailMessageEntity;
import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.dto.*;
import org.flightythought.smile.appserver.service.CharityFaultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/charityFault")
@Api(tags = "行善过失控制层", description = "行善、过失相关的接口")
public class CharityFaultController {

    @Autowired
    private CharityFaultService charityFaultService;
    @Autowired
    private PlatformUtils platformUtils;

    private static final Logger LOG = LoggerFactory.getLogger(CharityFaultController.class);

    @GetMapping("/typeAndContents")
    @ApiOperation(value = "获取行善过失类型以及内容", notes = "获取行善过失类型和其对应的内容，cfTypeId如果不传，" +
            "则返回全部行善过失类型")
    @ApiImplicitParams({@ApiImplicitParam(name = "cfTypeId", value = "行善过失类型ID")})
    public ResponseBean getCharityFaultTypeAndContent(Integer cfTypeId) {
        try {
            CharityAndFault result = charityFaultService.getCharityAndFault(cfTypeId);
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取行善过失类型以及内容失败", e);
            return ResponseBean.error( e.getMessage());
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增行善或过失记录", notes = "新增行善或过失通用接口，根据不同行善类型和过失类型，传递不同的参数")
    public ResponseBean addCharityFaultRecord(@RequestBody CharityFaultRecordDTO charityFaultRecordDTO) {
        try {
            UserCharityFaultRecordEntity userCharityFaultRecordEntity = charityFaultService.addCharityFaultRecord(charityFaultRecordDTO);
            UserCharityFaultRecord result = charityFaultService.getUserCharityFaultRecord(userCharityFaultRecordEntity.getId());

            // 根据结果获取新的实体返回给前端
            return ResponseBean.ok("新增成功", result);
        } catch (Exception e) {
            LOG.error("新增行善或过失记录失败", e);
            return ResponseBean.error( e.getMessage());
        }
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取行善过失", notes = "获取行善过失")
    public ResponseBean getCharityFaults(@RequestBody CharityFaultQueryDTO charityFaultQueryDTO) {
        try {
            Page<UserCharityFaultRecord> result = charityFaultService.getCharityFaults(charityFaultQueryDTO);
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("myList")
    @ApiOperation(value = "获取自己创建的行善过失", notes = "获取自己创建的行善过失")
    public ResponseBean getMyCharityFaults(@RequestBody PageFilterDTO pageFilterDTO) {
        try {
            Page<UserCharityFaultRecord> result = charityFaultService.getMyCharityFaults(pageFilterDTO);
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取失败", e);
            return ResponseBean.error( e.getMessage());
        }
    }

    @GetMapping("/myStatistics")
    @ApiOperation(value = "获取我的爱心养生统计", notes = "获取我的爱心养生统计（行善次数、计分、忏悔次数）")
    public ResponseBean getMyStatistics() {
        // 获取当前登陆用户的用户ID
        try {
            UserEntity userEntity = platformUtils.getCurrentLoginUser();
            Long userId = userEntity.getId();
            Page<CharityFaultStatistics> charityFaultStatisticsPage = charityFaultService.getCharityFaultInfoOrRanking(new PageFilterDTO(), userId);
            if (charityFaultStatisticsPage.getTotalElements() > 0) {
                CharityFaultStatistics result = charityFaultStatisticsPage.getContent().get(0);
                return ResponseBean.ok("返回成功", result);
            } else {
                return ResponseBean.ok("返回成功", new CharityFaultStatistics());
            }
        } catch (Exception e) {
            LOG.error("获取我的爱心养生统计失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/ranking")
    @ApiOperation(value = "养生分数排名", notes = "养生分数排名")
    public ResponseBean getRanking(@RequestBody PageFilterDTO pageFilterDTO) {
        try {
            Page<CharityFaultStatistics> charityFaultStatisticsPage = charityFaultService.getCharityFaultInfoOrRanking(pageFilterDTO, null);
            return ResponseBean.ok("返回成功", charityFaultStatisticsPage);
        } catch (Exception e) {
            LOG.error("获取养生分数排名失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/addMessage")
    @ApiOperation(value = "评论爱心养生", notes = "评论爱心养生")
    public ResponseBean addCharityFaultMessage(@RequestBody CharityFaultMessageDTO charityFaultMessageDTO) {
        try {
             CharityFaultMessageSimple result = charityFaultService.addCharityFaultMessage(charityFaultMessageDTO);
            return ResponseBean.ok("评论成功", result);
        } catch (Exception e) {
            LOG.error("评论失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }



    @PostMapping("/message")
    @ApiOperation(value = "获取动态明细主评论", notes = "获取动态明细主评论，messageId可以不用传递")
    public ResponseBean getCharityFaultMessage(@RequestBody CharityFaultMessageQueryDTO charityFaultMessageQueryDTO) {
        try {
            Page<CharityFaultMessageSimple> result = charityFaultService.getCharityFaultMessage(charityFaultMessageQueryDTO);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取动态明细评论失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @PostMapping("/messageDetail")
    @ApiOperation(value = "根据动态明细ID，获取详情评论信息", notes = "根据动态明细ID，获取详情评论信息")
    public ResponseBean getCharityFaultMessageInfo(@RequestBody CharityFaultMessageQueryDTO charityFaultMessageQueryDTO) {
        try {
            Page<CharityFaultMessageSimple> result = charityFaultService.getCharityFaultMessageInfo(charityFaultMessageQueryDTO);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取动态明细评论失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }

    @PostMapping("/likeCharityFaultMessage")
    @ApiOperation(value = "爱心养生评论点赞或取消点赞", notes = "用户点赞或取消点赞爱心养生的评论")
    @ApiImplicitParams(@ApiImplicitParam(value = "动态明细评论ID", name = "messageId"))
    public ResponseBean likeCharityFaultMessage(Integer messageId) {
        try {
            charityFaultService.likeCharityFaultMessage(messageId);
            return ResponseBean.ok("操作成功");
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }
}
