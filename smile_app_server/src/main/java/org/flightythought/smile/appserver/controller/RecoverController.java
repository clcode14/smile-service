package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.database.entity.RecoverCase;
import org.flightythought.smile.appserver.dto.RecoverCaseDTO;
import org.flightythought.smile.appserver.service.RecoverService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@Api(tags = "康复案例控制层", description = "康复案例相关接口")
@RequestMapping("auth/recover")
@RestController
public class RecoverController {
    @Autowired
    private RecoverService recoverService;

    private static final Logger LOG = LoggerFactory.getLogger(RecoverController.class);

    @ApiOperation(value = "获取康复案例", notes = "分页查询，根据疾病小类ID获取康复案例")
    @PostMapping(value = "recover/list")
    public ResponseBean findAllRecoverCasePage(@RequestBody RecoverCaseDTO recoverCaseDTO) {
        Page<RecoverCase> result;
        try {
            result = recoverService.findAllRecoverCasePage(recoverCaseDTO);
            return ResponseBean.ok("返回成功", result);
        } catch (Exception e) {
            LOG.error("返回失败", e);
            return ResponseBean.error("返回失败", e.getMessage());
        }
    }
}
