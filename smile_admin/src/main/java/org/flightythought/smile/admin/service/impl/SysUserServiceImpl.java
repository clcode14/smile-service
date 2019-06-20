package org.flightythought.smile.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.flightythought.smile.admin.bean.RoleInfo;
import org.flightythought.smile.admin.bean.SysUserInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.SysRoleEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.entity.UserRoleEntity;
import org.flightythought.smile.admin.database.repository.SysRoleRepository;
import org.flightythought.smile.admin.database.repository.SysUserRepository;
import org.flightythought.smile.admin.database.repository.UserRoleRepository;
import org.flightythought.smile.admin.dto.SysUserDTO;
import org.flightythought.smile.admin.service.SysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private SysUserRepository  sysUserRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private SysRoleRepository  sysRoleRepository;

    @Override
    public Page<SysUserInfo> getSysUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber - 1, pageSize, new Sort(Sort.Direction.DESC, "id"));
        Page<SysUserEntity> pageList = sysUserRepository.findAll(pageable);
        List<SysUserInfo> sysUserInfos = pageList.stream().map(e -> {
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setId(e.getId());
            sysUserInfo.setLoginName(e.getLoginName());
            sysUserInfo.setUserName(e.getUserName());
            sysUserInfo.setCreateTime(e.getCreateTime());
            sysUserInfo.setCreateUserName(e.getCreateUserName());
            sysUserInfo.setUpdateTime(e.getUpdateTime());
            sysUserInfo.setUpdateUserName(e.getUpdateUserName());
            return sysUserInfo;
        }).collect(Collectors.toList());
        PageImpl<SysUserInfo> result = new PageImpl<>(sysUserInfos, pageable, pageList.getTotalElements());
        return result;
    }

    @Override
    @Transactional
    public void saveSysUser(SysUserDTO sysUserDTO, HttpSession session) {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        // 新增解决方案
        SysUserEntity sysUserEntity = new SysUserEntity();
        // 创建者
        sysUserEntity.setCreateUserName(loginUser.getLoginName());
        // 账户名
        sysUserEntity.setLoginName(sysUserDTO.getLoginName());
        // 用户名
        sysUserEntity.setUserName(sysUserDTO.getUserName());
        // 密码
        sysUserEntity.setPassword(passwordEncode(sysUserDTO.getPassword()));
        sysUserEntity.setStatus(true);
        sysUserEntity.setLoginLimit(1);
        // 保存账户信息
        sysUserEntity = sysUserRepository.save(sysUserEntity);
        // 获取解决方案ID
        Integer sysUserId = sysUserEntity.getId();
        // 获取课程ID
        List<Integer> roleIds = sysUserDTO.getRoleIds();
        List<UserRoleEntity> userRoleEntities = new ArrayList<>();
        roleIds.forEach(roleId -> {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(sysUserId);
            userRoleEntity.setRoleId(roleId);
            userRoleEntities.add(userRoleEntity);
        });
        userRoleRepository.saveAll(userRoleEntities);
    }

    @Override
    @Transactional
    public void modifySysUser(SysUserDTO sysUserDTO, HttpSession session) {
        SysUserEntity loginUser = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        // 获取账户ID
        Integer sysUserId = sysUserDTO.getId();
        // 修改账户信息
        SysUserEntity sysUserEntity = sysUserRepository.findById(sysUserId).get();
        // 修改者
        sysUserEntity.setUpdateUserName(loginUser.getLoginName());
        // 删除相关角色
        userRoleRepository.deleteAllByUserId(sysUserId);
        sysUserEntity.setLoginName(sysUserDTO.getLoginName());
        sysUserEntity.setUserName(sysUserDTO.getUserName());
        sysUserRepository.save(sysUserEntity);
        // 获取课程ID
        List<Integer> roleIds = sysUserDTO.getRoleIds();
        List<UserRoleEntity> userRoleEntities = new ArrayList<>();
        roleIds.forEach(roleId -> {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(sysUserId);
            userRoleEntity.setRoleId(roleId);
            userRoleEntities.add(userRoleEntity);
        });
        userRoleRepository.saveAll(userRoleEntities);
    }

    @Override
    public SysUserInfo findSysUsers(Integer id, HttpSession session) {
        return sysUserRepository.findById(id).map(e -> {
            SysUserInfo sysUserInfo = new SysUserInfo();
            sysUserInfo.setId(e.getId());
            sysUserInfo.setLoginName(e.getLoginName());
            sysUserInfo.setUserName(e.getUserName());
            sysUserInfo.setCreateTime(e.getCreateTime());
            sysUserInfo.setCreateUserName(e.getCreateUserName());
            sysUserInfo.setUpdateTime(e.getUpdateTime());
            sysUserInfo.setUpdateUserName(e.getUpdateUserName());
            sysUserInfo.setRoleIds(e.getRoles().stream().map(SysRoleEntity::getId).collect(Collectors.toList()));
            return sysUserInfo;
        }).orElse(null);
    }

    private String passwordEncode(String password) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        // 加密
        String encodedPassword = passwordEncoder.encode(password.trim());
        return encodedPassword;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteSysUsers(Integer id, HttpSession session) {
        //删除账户
        sysUserRepository.deleteById(id);
        // 删除相关角色
        userRoleRepository.deleteAllByUserId(id);
    }

    @Override
    public List<RoleInfo> getRoles() {
        List<SysRoleEntity> entities = sysRoleRepository.findAll();
        return entities.stream().map(role -> {
            RoleInfo roleInfo = new RoleInfo();
            roleInfo.setId(role.getId());
            roleInfo.setName(role.getName());
            roleInfo.setRole(role.getRole());
            return roleInfo;
        }).collect(Collectors.toList());
    }

}
