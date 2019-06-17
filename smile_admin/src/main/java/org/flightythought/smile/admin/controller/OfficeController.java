package org.flightythought.smile.admin.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.flightythought.smile.admin.bean.OfficeInfo;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.database.entity.OfficeEntity;
import org.flightythought.smile.admin.dto.OfficeDTO;
import org.flightythought.smile.admin.service.OfficeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping("/office")
@Api(value = "其他配置", tags = "机构管理", description = "机构管理")
public class OfficeController {

    private static final Logger LOG = LoggerFactory.getLogger(OfficeController.class);

    @Autowired
    private OfficeService officeService;

    @GetMapping("/list")
    @ApiOperation(value = "机构列表", notes = "机构列表", position = 1)
    public ResponseBean findAllOffice(Map<String, String> params,
                                      @ApiIgnore HttpSession session) {
        try {
            Page<OfficeInfo> office = officeService.findAllOffice(params, session);
            return ResponseBean.ok("查询成功", office);
        } catch (Exception e) {
            LOG.error("查询机构列表失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }

    @GetMapping("{officeId}")
    @ApiOperation(value = "机构列表", notes = "机构列表", position = 1)
    public ResponseBean findOffice(@PathVariable Long officeId, @ApiIgnore HttpSession session) {
        try {
            OfficeInfo officeInfo = officeService.findOffice(officeId, session);
            return ResponseBean.ok("查询成功", officeInfo);
        } catch (Exception e) {
            LOG.error("查询机构列表失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }

    @PostMapping("/create")
    @ApiOperation(value = "新增机构", notes = "新增机构", position = 1)
    public ResponseBean createOffice(@RequestBody @ApiParam OfficeDTO officeDTO, @ApiIgnore HttpSession session) {
        try {
            OfficeEntity officeEntity = officeService.save(officeDTO, session);
            return ResponseBean.ok("新增成功", officeEntity);
        } catch (Exception e) {
            LOG.error("新增机构失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }

    }

    @PutMapping("/modify")
    @ApiOperation(value = "修改机构", notes = "修改机构", position = 1)
    public ResponseBean modifyOffice(@RequestBody @ApiParam OfficeDTO officeDTO, @ApiIgnore HttpSession session) {
        try {
            OfficeEntity officeEntity = officeService.modify(officeDTO, session);
            return ResponseBean.ok("修改成功", officeEntity);
        } catch (Exception e) {
            LOG.error("修改机构失败", e);
            return ResponseBean.error("修改失败", e.getMessage());
        }

    }

    @DeleteMapping("/delete/{officeId}")
    @ApiOperation(value = "删除机构", notes = "删除机构", position = 1)
    public ResponseBean modifyOffice(@PathVariable Long officeId, @ApiIgnore HttpSession session) {
        try {
            officeService.deleteById(officeId, session);
            return ResponseBean.ok("删除机构成功");
        } catch (Exception e) {
            LOG.error("删除机构失败", e);
            return ResponseBean.error("删除机构失败", e.getMessage());
        }

    }

}
