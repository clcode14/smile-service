package org.flightythought.smile.admin.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.flightythought.smile.admin.bean.RoleInfo;
import org.flightythought.smile.admin.bean.SysUserInfo;
import org.flightythought.smile.admin.dto.SysUserDTO;
import org.springframework.data.domain.Page;

public interface SysUserService {

    Page<SysUserInfo> getSysUsers(int pageNumber, int pageSize);

    void saveSysUser(SysUserDTO sysUserDTO, HttpSession session);

    void modifySysUser(SysUserDTO sysUserDTO, HttpSession session);

    SysUserInfo findSysUsers(Integer id, HttpSession session);

    void deleteSysUsers(Integer id, HttpSession session);

    List<RoleInfo> getRoles();

}
