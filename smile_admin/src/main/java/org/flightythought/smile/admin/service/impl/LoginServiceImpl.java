package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.SysResourceEntity;
import org.flightythought.smile.admin.database.entity.SysRoleEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.SysUserRepository;
import org.flightythought.smile.admin.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.Comparator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName LoginServiceImpl
 * @CreateTime 2019/6/20 13:51
 * @Description: TODO
 */
@Service
public class LoginServiceImpl implements LoginService {
    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    @Transactional
    public List<String> getRoles(HttpSession session) {
        // 获取当前登陆用户
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        // 获取当前用户登陆权限
        SysUserEntity userEntity = sysUserRepository.getOne(sysUserEntity.getId());
        Set<SysRoleEntity> sysRoleEntities = userEntity.getRoles();
        // 获取资源
        Set<SysResourceEntity> sysResourceEntities = sysRoleEntities.stream()
                .flatMap(sysRoleEntity -> sysRoleEntity.getResources().stream())
                .sorted(Comparator.comparing(SysResourceEntity::getSeq))
                .collect(Collectors.toCollection(LinkedHashSet::new));
        List<String> roles = sysResourceEntities.stream().map(SysResourceEntity::getUrl).collect(Collectors.toList());
        return roles;
    }
}
