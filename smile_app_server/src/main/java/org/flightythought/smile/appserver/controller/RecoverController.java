//package org.flightythought.smile.appserver.controller;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import org.flightythought.smile.appserver.bean.ResponseBean;
//import org.flightythought.smile.appserver.dto.AboutDiseaseDetailQueryDTO;
//import org.flightythought.smile.appserver.service.RecoverService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@Api(tags = "康复案例控制层", description = "康复案例相关接口")
//@RequestMapping("auth/recover")
//@RestController
//public class RecoverController {
//    @Autowired
//    private RecoverService recoverService;
//
//    private static final Logger LOG = LoggerFactory.getLogger(RecoverController.class);
//
//    @ApiOperation(value = "获取康复案例", notes = "康复案例查询")
//    @PostMapping(value = "recover/list")
//    public ResponseBean findAllRecoverCasePage(@RequestBody AboutDiseaseDetailQueryDTO aboutDiseaseDetailQueryDTO) {
//        return null;
//    }
//}
