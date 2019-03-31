package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.database.entity.DiseaseClassDetailEntity;
import org.flightythought.smile.appserver.database.entity.DiseaseClassEntity;
import org.flightythought.smile.appserver.dto.UserFollowDiseaseDTO;
import org.flightythought.smile.appserver.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RestController
@RequestMapping("/auth/disease")
@Api(tags = "千病万方控制层", description = "千病万方相关接口")
public class DiseaseController {
    @Autowired
    private DiseaseService diseaseService;

    @ApiOperation(value = "常见疾病列表", notes = "用户第一次登陆时，选择关注常见疾病（获取疾病小类列表）")
    @GetMapping("common/list")
    public ResponseBean getCommonDiseases() {
        return ResponseBean.ok("返回成功", diseaseService.getCommonDiseases());
    }

    @ApiOperation(value = "常见疾病选中", notes = "用户选择关注常见疾病")
    @PostMapping(value = "common/save")
    public ResponseBean commonDiseaseDetails(@ApiParam @RequestBody List<UserFollowDiseaseDTO> userFollowDiseases) {
        diseaseService.saveUserFollowDiseases(userFollowDiseases);
        return ResponseBean.ok("操作成功");
    }

    @ApiOperation(value = "千病万方疾病大类列表查询", notes = "点击千病万方后获取所有疾病大类以及对应的疾病小类，用户关注的会排在前面")
    @GetMapping(value = "thousand/list")
    public ResponseBean getAllThousandDisease() {
        List<DiseaseClassEntity> diseaseClassEntities = diseaseService.getAllThousandDisease();
        return ResponseBean.ok("操作成功", diseaseClassEntities);
    }

    @ApiOperation(value = "疾病详情", notes = "用户点击疾病，获取疾病小类有关属性，拿到返回数据可调用其它接口获取康复案例以及疾病原因")
    @GetMapping(value = "thousand/detail/{id}")
    public ResponseBean getThousandDiseaseDetail(@ApiParam("疾病小类ID") @PathVariable Integer id) {
        DiseaseClassDetailEntity diseaseClassDetailEntity = diseaseService.getThousandDiseaseDetail(id);
        return ResponseBean.ok("操作成功", diseaseClassDetailEntity);
    }
}
