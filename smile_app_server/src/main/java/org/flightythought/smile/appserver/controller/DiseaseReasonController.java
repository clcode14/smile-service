package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.Api;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth/disease")
@Api(tags = "疾病原因控制层", description = "疾病原因以及对应的解决方案获取")
public class DiseaseReasonController {

    private static final Logger LOG = LoggerFactory.getLogger(DiseaseReasonController.class);

//    public ResponseBean
}
