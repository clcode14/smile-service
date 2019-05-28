package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.RecoverCaseSimple;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.bean.UserInfo;
import org.flightythought.smile.appserver.dto.HealthOrDiseaseByIdQueryDTO;
import org.flightythought.smile.appserver.service.RecoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "康复案例控制层", description = "康复案例相关接口")
@RequestMapping("auth/recover")
@RestController
public class RecoverController {
    @Autowired
    private RecoverService recoverService;

    private static final Logger LOG = LoggerFactory.getLogger(RecoverController.class);

    @ApiOperation(value = "获取康复案例", notes = "康复案例查询，可以根据疾病小类ID或养生ID分页查询获取康复案例，参数不传会返回全部案例，返回的案例是不重复的")
    @PostMapping(value = "/list")
    public ResponseBean getRecoverCase(@RequestBody HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO) {
        try {
            Page<RecoverCaseSimple> result = recoverService.getRecoverCase(healthOrDiseaseByIdQueryDTO);
            return ResponseBean.ok("获取康复案例成功", result);
        } catch (Exception e) {
            LOG.error("获取康复案例失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }

    @ApiOperation(value = "获取康复人列表", notes = "康复人列表查询，根据疾病小类ID或养生ID返回对应疾病下所有的康复人")
    @PostMapping(value = "/persons")
    public ResponseBean getRecoverCasePeron(@RequestBody HealthOrDiseaseByIdQueryDTO healthOrDiseaseByIdQueryDTO) {
        try {
            Page<UserInfo> result = recoverService.getRecoverCasePerson(healthOrDiseaseByIdQueryDTO);
            return ResponseBean.ok("获取康复人员列表成功", result);
        } catch (Exception e) {
            LOG.error("获取康复人员列表失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }
}
