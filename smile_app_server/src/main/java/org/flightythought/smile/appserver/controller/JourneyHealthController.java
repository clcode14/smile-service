package org.flightythought.smile.appserver.controller;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.flightythought.smile.appserver.bean.DiseaseClassDetailSimple;
import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.bean.HealthJourney;
import org.flightythought.smile.appserver.bean.HealthJourneySimple;
import org.flightythought.smile.appserver.bean.JourneyNote;
import org.flightythought.smile.appserver.bean.JourneyNoteMessageSimple;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.appserver.database.entity.HealthResultEntity;
import org.flightythought.smile.appserver.database.entity.JourneyEntity;
import org.flightythought.smile.appserver.database.entity.JourneyNoteEntity;
import org.flightythought.smile.appserver.database.entity.JourneyNoteMessageEntity;
import org.flightythought.smile.appserver.dto.HealthJourneyEndDTO;
import org.flightythought.smile.appserver.dto.HealthJourneyQueryDTO;
import org.flightythought.smile.appserver.dto.HealthJourneyStartDTO;
import org.flightythought.smile.appserver.dto.JourneyNoteDTO;
import org.flightythought.smile.appserver.dto.JourneyNoteMessageDTO;
import org.flightythought.smile.appserver.dto.JourneyNoteMessageQueryDTO;
import org.flightythought.smile.appserver.dto.JourneyNoteQueryDTO;
import org.flightythought.smile.appserver.dto.JourneyQueryDTO;
import org.flightythought.smile.appserver.dto.MyJourneyQueryDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.flightythought.smile.appserver.service.JourneyHealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@RequestMapping("/auth/journey")
@Api(tags = "养生旅程控制层", description = "养生旅程等相关接口")
public class JourneyHealthController {
    @Autowired
    private JourneyHealthService journeyHealthService;

    private static final Logger LOG = LoggerFactory.getLogger(JourneyHealthController.class);

    @GetMapping("/healthNormType")
    @ApiOperation(value = "获取体检指标类型", notes = "获取体检指标类型")
    public ResponseBean getHealthNormType() {
        try {
            List<HealthNormTypeEntity> result = journeyHealthService.getHealthNormTypes();
            return ResponseBean.ok("获取成功", result);
        } catch (Exception e) {
            LOG.error("获取体检指标类型失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "上传多份体检报告", notes = "上传多份体检报告")
    @PostMapping("/healthReports")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "上传类型：0：开启养生旅程上传体检报告，1：结束养生旅程上传的体检报告")
    })
    public ResponseBean uploadReports(@ApiParam(value = "上传的体检报告") List<MultipartFile> reports, Integer type) {
        try {
            List<FileInfo> result = journeyHealthService.uploadReport(reports, type);
            return ResponseBean.ok("上传成功", result);
        } catch (Exception e) {
            LOG.error("上传体检报告失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "上传一份体检报告", notes = "上传一份体检报告")
    @PostMapping("/healthReport")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "type", value = "上传类型：0：开启养生旅程上传体检报告，1：结束养生旅程上传的体检报告")
    })
    public ResponseBean uploadReports(MultipartFile report, Integer type) {
        List<MultipartFile> reports = new ArrayList<>();
        try {
            reports.add(report);
            List<FileInfo> results = journeyHealthService.uploadReport(reports, type);
            return ResponseBean.ok("上传体检报告成功", results);
        } catch (Exception e) {
            LOG.error("上传体检报告失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "开启养生旅程", notes = "开启养生旅程")
    @PostMapping("/startJourney")
    public ResponseBean startHealthJourney(@RequestBody HealthJourneyStartDTO healthJourneyStartDTO) {
        try {
            JourneyEntity result = journeyHealthService.startHealthJourney(healthJourneyStartDTO);
            return ResponseBean.ok("开启养生旅程成功", result);
        } catch (Exception e) {
            LOG.error("开启养生旅程失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "修改当前登录用户的养生旅程", notes = "修改当前登录用户的养生旅程信息，" +
            "该接口不会修改创建旅程时的体检指标、体检报告、和现有症状（疾病小类）只会修改旅程名称和概述")
    @PostMapping("/updateJourney")
    public ResponseBean updateHealthJourney(@RequestBody HealthJourneyStartDTO healthJourneyStartDTO) {
        try {
            HealthJourney result = journeyHealthService.updateHealthJourney(healthJourneyStartDTO);
            return ResponseBean.ok("修改成功", result);
        } catch (Exception e) {
            LOG.error("修改当前登录用户的养生旅程失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "获取当前用户的养生旅程", notes = "获取当前用户的养生旅程")
    @PostMapping("/healthJourney")
    public ResponseBean getHealthJourney(@RequestBody MyJourneyQueryDTO myJourneyQueryDTO) {
        try {
            PageFilterDTO pageFilterDTO = new PageFilterDTO();
            pageFilterDTO.setPageNumber(myJourneyQueryDTO.getPageNumber());
            pageFilterDTO.setPageSize(myJourneyQueryDTO.getPageSize());
            Page<HealthJourneySimple> result = journeyHealthService.getHealthJourney(null, pageFilterDTO, myJourneyQueryDTO.getFinished());
            if (result.getContent().size() > 0) {
                List<HealthJourneySimple> sort = result.stream().sorted(Comparator.comparing(HealthJourneySimple::getStartTime).reversed()).collect(Collectors.toList());
                result = new PageImpl<>(sort, result.getPageable(), result.getTotalElements());
            }
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取当前用户养生旅程失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "根据用户ID获取养生旅程", notes = "根据用户ID获取养生旅程")
    @PostMapping("/healthJourneyByUser")
    public ResponseBean getHealthJourneyByUserId(@RequestBody HealthJourneyQueryDTO healthJourneyQueryDTO) {
        try {
            Long userId = healthJourneyQueryDTO.getUserId();
            PageFilterDTO pageFilterDTO = new PageFilterDTO();
            pageFilterDTO.setPageNumber(healthJourneyQueryDTO.getPageNumber());
            pageFilterDTO.setPageSize(healthJourneyQueryDTO.getPageSize());
            Page<HealthJourneySimple> result = journeyHealthService.getHealthJourney(userId, pageFilterDTO, true);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "根据养生旅程ID获取养生旅程信息", notes = "根据养生旅程ID获取养生旅程信息，访问非自己的养生旅程会增加一次访问量")
    @PostMapping("/healthJourneyInfo")
    public ResponseBean getJourneyByJourneyId(@RequestBody JourneyQueryDTO journeyQueryDTO) {
        try {
            Integer journeyId = journeyQueryDTO.getJourneyId();
            Integer recoverId = journeyQueryDTO.getRecoverId();
            HealthJourney result = journeyHealthService.getHealthJourney(journeyId, recoverId);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生旅程信息失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "新增养生日记", notes = "用户新增养生日记")
    @PostMapping("/addNote")
    public ResponseBean saveJourneyHealthNote(@RequestBody JourneyNoteDTO journeyNoteDTO) {
        try {
            JourneyNoteEntity journeyNoteEntity = journeyHealthService.addJourneyNoteEntity(journeyNoteDTO);
            return ResponseBean.ok("新增养生日记成功", journeyNoteEntity);
        } catch (Exception e) {
            LOG.error("新增养生日记失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "根据养生旅程ID获取养生日记", notes = "根据养生旅程ID获取养生日记")
    @PostMapping("/notes")
    public ResponseBean getJourneyHealthNote(@RequestBody JourneyNoteQueryDTO journeyNoteQueryDTO) {
        try {
            Page<JourneyNote> result = journeyHealthService.getJourneyHealthNote(journeyNoteQueryDTO);
            return ResponseBean.ok("获取养生日记成功", result);
        } catch (Exception e) {
            LOG.error("获取养生日记失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "根据日记ID获取日记详情", notes = "根据日记ID获取日记详情")
    @GetMapping("/note/{noteId}")
    public ResponseBean getJourneyNote(@PathVariable Integer noteId) {
        try {
            JourneyNote journeyNote = journeyHealthService.getJourneyNote(noteId);
            return ResponseBean.ok("获取日记详情成功", journeyNote);
        } catch (Exception e) {
            LOG.error("获取日记详情失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "获取养生成果", notes = "获取养生成果选项用于结束养生旅程所用")
    @PostMapping("/healthResultStatus")
    public ResponseBean getJourneyHealthResultStatus(@RequestBody PageFilterDTO pageFilterDTO) {
        try {
            Page<HealthResultEntity> result = journeyHealthService.getHealthResultList(pageFilterDTO);
            return ResponseBean.ok("获取养生成果成果", result);
        } catch (Exception e) {
            LOG.error("获取养生成果失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "结束养生旅程", notes = "结束养生旅程")
    @PostMapping("/finished")
    public ResponseBean finishedHealthJourney(@RequestBody HealthJourneyEndDTO healthJourneyEndDTO) {
        try {
            HealthJourney result = journeyHealthService.endHealthJourney(healthJourneyEndDTO);
            return ResponseBean.ok("操作成功", result);
        } catch (Exception e) {
            LOG.error("结束养生旅程失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @GetMapping("/journeyDisease/{journeyId}")
    @ApiOperation(value = "根据旅程ID获取该旅程关联的疾病列表")
    public ResponseBean getDiseaseByJourneyId(@PathVariable Integer journeyId) {
        try {
            List<DiseaseClassDetailSimple> result = journeyHealthService.getDiseaseByJourneyId(journeyId);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("返回失败", e.getMessage());
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "删除养生旅程", notes = "删除养生旅程，会删除该旅程关联的全部信息，同时会删除服务器上的上传文件")
    @DeleteMapping("/deleteJourney/{journeyId}")
    public ResponseBean deleteHealthJourney(@PathVariable(value = "journeyId") Integer journeyId) {
        try {
            journeyHealthService.deleteJourneyById(journeyId);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
    
    @ApiOperation(value = "删除养生旅程日记", notes = "删除养生旅程日记")
    @DeleteMapping("/deleteJourneyNote/{noteId}")
    public ResponseBean deleteJourneyNote(@PathVariable(value = "noteId") Integer noteId) {
        try {
            journeyHealthService.deleteJourneyNoteById(noteId);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
    
    @PostMapping("/addNoteMessage")
    @ApiOperation(value = "评论养生日记", notes = "评论养生日记和回复评论，如果是回复评论则需要传递被回复的评论主键ID，即parentId")
    public ResponseBean addJourneyNoteMessage(@RequestBody JourneyNoteMessageDTO journeyNoteMessageDTO) {
        try {
            JourneyNoteMessageEntity journeyNoteMessageEntity = journeyHealthService.addJourneyNoteMessage(journeyNoteMessageDTO);
            return ResponseBean.ok("评论成功", journeyNoteMessageEntity);
        } catch (Exception e) {
            LOG.error("评论失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }
    
    @PostMapping("/messageDetail")
    @ApiOperation(value = "根据养生日记ID，获取详情评论信息", notes = "根据养生日记ID，获取详情评论信息")
    public ResponseBean getJourneyNoteMessageInfo(@RequestBody JourneyNoteMessageQueryDTO journeyNoteMessageQueryDTO) {
        try {
            Page<JourneyNoteMessageSimple> result = journeyHealthService.getJourneyNoteMessageInfo(journeyNoteMessageQueryDTO);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("获取养生日记评论失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @PostMapping("/likeJourneyNote")
    @ApiOperation(value = "养生日记点赞或取消点赞", notes = "用户点赞或取消点赞养生日记")
    @ApiImplicitParams(@ApiImplicitParam(value = "动态明细ID", name = "dynamicDetailId"))
    public ResponseBean likeJourneyNote(Integer journeyNoteId) {
        try {
            journeyHealthService.likeJourneyNote(journeyNoteId);
            return ResponseBean.ok("操作成功");
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }
    
    @DeleteMapping("/noteMessage/{messageId}")
    @ApiOperation(value = "删除养生日记评论", notes = "删除养生日记评论")
    public ResponseBean deleteJourneyNoteMessage(@PathVariable Integer messageId) {
        try {
            journeyHealthService.deleteJourneyNoteMessage(messageId);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }
}
