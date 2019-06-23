package org.flightythought.smile.admin.controller;

import io.swagger.annotations.*;
import org.flightythought.smile.admin.bean.ResponseBean;
import org.flightythought.smile.admin.bean.RoleInfo;
import org.flightythought.smile.admin.bean.SysUserInfo;
import org.flightythought.smile.admin.dto.SysUserDTO;
import org.flightythought.smile.admin.service.SysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/sysUser")
@Api(value = "账号管理", tags = "账号管理", description = "账号管理")
public class SysUserController {

    private static final Logger LOG = LoggerFactory.getLogger(SysUserController.class);
    @Autowired
    private SysUserService      sysUserService;

    @GetMapping("/list")
    @ApiOperation(value = "获取账号信息", notes = "获取账号信息", position = 1)
    @ApiImplicitParams({ @ApiImplicitParam(name = "pageNumber", value = "页数从1开始"), @ApiImplicitParam(name = "pageSize", value = "每页显示个数") })
    public ResponseBean getSysUsers(int pageNumber, int pageSize) {
        try {
            Page<SysUserInfo> page = sysUserService.getSysUsers(pageNumber, pageSize);
            return ResponseBean.ok("返回成功", page);
        } catch (Exception e) {
            LOG.error("获取疾病大类失败", e);
            return ResponseBean.error("获取疾病大类失败", e.getMessage());
        }
    }
    
    @GetMapping("/{id}")
    @ApiOperation(value = "查询账号信息", notes = "查询账号信息", position = 1)
    public ResponseBean findSysUsers(@PathVariable @ApiParam("账号id") Integer id,
                                     @ApiIgnore HttpSession session) {
        try {
            SysUserInfo sysUserInfo = sysUserService.findSysUsers(id, session);
            return ResponseBean.ok("查询成功", sysUserInfo);
        } catch (Exception e) {
            LOG.error("查询失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除账号信息", notes = "删除账号信息", position = 1)
    public ResponseBean deleteSysUsers(@PathVariable @ApiParam("账号id") Integer id,
                                     @ApiIgnore HttpSession session) {
        try {
            sysUserService.deleteSysUsers(id, session);
            return ResponseBean.ok("删除成功");
        } catch (Exception e) {
            LOG.error("删除失败", e);
            return ResponseBean.error("删除失败", e.getMessage());
        }
    }

    @PostMapping("/save")
    @ApiOperation(value = "增加账户信息", notes = "增加账户信息", position = 1)
    public ResponseBean saveSysUser(@RequestBody @ApiParam SysUserDTO sysUserDTO, @ApiIgnore HttpSession session) {
        try {
            sysUserService.saveSysUser(sysUserDTO, session);
            return ResponseBean.ok("新增成功");
        } catch (Exception e) {
            LOG.error("新增账户信息失败", e);
            return ResponseBean.error("新增失败", e.getMessage());
        }
    }

    @PostMapping("/modify")
    @ApiOperation(value = "编辑账户信息", notes = "编辑账户信息", position = 1)
    public ResponseBean modifySysUser(@RequestBody @ApiParam SysUserDTO sysUserDTO, @ApiIgnore HttpSession session) {
        try {
            sysUserService.modifySysUser(sysUserDTO, session);
            return ResponseBean.ok("编辑成功");
        } catch (Exception e) {
            LOG.error("编辑失败", e);
            return ResponseBean.error("编辑失败", e.getMessage());
        }
    }
    
    @GetMapping("/getRoles")
    @ApiOperation(value = "获取关联角色", notes = "获取关联", position = 1)
    public ResponseBean getRoles() {
        try {
            List<RoleInfo> roleInfos = sysUserService.getRoles();
            return ResponseBean.ok("查询成功",roleInfos);
        } catch (Exception e) {
            LOG.error("查询失败", e);
            return ResponseBean.error("查询失败", e.getMessage());
        }
    }
}
