package org.flightythought.smile.admin.controller;

import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.DiseaseReasonEntity;
import org.flightythought.smile.admin.dto.DiseaseReasonDTO;
import org.flightythought.smile.admin.service.DiseaseReasonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/diseaseReason")
@Api(value = "疾病配置", tags = "疾病原因")
public class DiseaseReasonController {

    @Autowired
    private DiseaseReasonService diseaseReasonService;

    private static final Logger LOG = LoggerFactory.getLogger(DiseaseReasonController.class);

    @GetMapping("/list")
    @ApiOperation(value = "疾病原因列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageNumber", value = "页数从1开始"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示个数"),
            @ApiImplicitParam(name = "diseaseId", value = "疾病大类id"),
            @ApiImplicitParam(name = "diseaseDetailId", value = "疾病小类id")

    })
    public ResponseBean findAllDiseaseReason(@ApiParam Map<String, String> params, @ApiIgnore HttpSession session) {
        try {
            Page<DiseaseReasonEntity> diseaseReasonEntity = diseaseReasonService.findAllDiseaseReason(params, session);
            return ResponseBean.ok("操作成功", diseaseReasonEntity);
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }

    @GetMapping("{id}")
    @ApiOperation(value = "查询单个疾病原因")
    public ResponseBean findDiseaseReason(@ApiParam(name = "id") @PathVariable Integer id, @ApiIgnore HttpSession session) {
        try {
            DiseaseReasonEntity diseaseReasonEntity = diseaseReasonService.findDiseaseReasonById(id, session);
            return ResponseBean.ok("操作成功", diseaseReasonEntity);
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增疾病原因")
    public ResponseBean saveDiseaseReason(@RequestBody @ApiParam(name = "疾病原因实体") DiseaseReasonDTO diseaseReasonDTO, @ApiIgnore HttpSession session) {
        try {
            DiseaseReasonEntity diseaseReasonEntity = diseaseReasonService.saveDiseaseReason(diseaseReasonDTO, session);
            return ResponseBean.ok("操作成功", diseaseReasonEntity);
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改疾病原因")
    public ResponseBean modifyDiseaseReason(@RequestBody DiseaseReasonDTO diseaseReasonDTO, @ApiIgnore HttpSession session) {
        try {
            DiseaseReasonEntity diseaseReasonEntity = diseaseReasonService.modifyDiseaseReason(diseaseReasonDTO, session);
            return ResponseBean.ok("操作成功", diseaseReasonEntity);
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }

    @DeleteMapping("{id}")
    @ApiOperation(value = "删除单个疾病原因")
    public ResponseBean deleteDiseaseReason(@ApiParam(name = "id") @PathVariable Long id, @ApiIgnore HttpSession session) {
        try {
            diseaseReasonService.deleteDiseaseReason(id, session);
            return ResponseBean.ok("操作成功");
        } catch (Exception e) {
            LOG.error("操作失败", e);
            return ResponseBean.error("操作失败", e.getMessage());
        }
    }

}
