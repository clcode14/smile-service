package org.flightythought.smile.appserver.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.appserver.bean.CharityAndFault;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;
import org.flightythought.smile.appserver.dto.CharityFaultRecordDTO;
import org.flightythought.smile.appserver.service.CharityFaultService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth/charityFault")
@Api(tags = "行善过失控制层", description = "行善、过失相关的接口")
public class CharityFaultController {

    @Autowired
    private CharityFaultService charityFaultService;

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
            return ResponseBean.error("获取失败", e.getMessage());
        }
    }

    @PostMapping("/add")
    @ApiOperation(value = "新增行善或过失记录", notes = "新增行善或过失通用接口，根据不同行善类型和过失类型，传递不同的参数")
    public ResponseBean addCharityFaultRecord(@RequestBody CharityFaultRecordDTO charityFaultRecordDTO) {
        try {
            UserCharityFaultRecordEntity result = charityFaultService.addCharityFaultRecord(charityFaultRecordDTO);
            return ResponseBean.ok("新增成功", result);
        } catch (Exception e) {
            LOG.error("新增行善或过失记录失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }
    }
}
