package org.flightythought.smile.admin.service.impl;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.flightythought.smile.admin.bean.ResourceInfo;
import org.flightythought.smile.admin.bean.RoleInfo;
import org.flightythought.smile.admin.database.entity.SysResourceEntity;
import org.flightythought.smile.admin.database.entity.SysRoleEntity;
import org.flightythought.smile.admin.database.repository.SysResourceRepository;
import org.flightythought.smile.admin.database.repository.SysRoleRepository;
import org.flightythought.smile.admin.dto.RoleDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.RoleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName RoleServiceImpl
 * @CreateTime 2019/6/20 1:38
 * @Description: TODO
 */
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private SysRoleRepository     sysRoleRepository;
    @Autowired
    private SysResourceRepository sysResourceRepository;

    @Override
    public RoleInfo addRole(RoleDTO roleDTO) {
        SysRoleEntity sysRoleEntity = new SysRoleEntity();
        sysRoleEntity.setName(roleDTO.getName());
        sysRoleEntity.setRole(roleDTO.getRole());
        Set<SysResourceEntity> parent = sysResourceRepository.findByList(roleDTO.getResource());
        Set<SysResourceEntity> child = sysResourceRepository.findByUrlIn(roleDTO.getResource());
        parent.addAll(child);
        sysRoleEntity.setResources(parent);
        sysRoleEntity = sysRoleRepository.save(sysRoleEntity);
        RoleInfo result = new RoleInfo();
        BeanUtils.copyProperties(roleDTO, result);
        result.setId(sysRoleEntity.getId());
        return result;
    }

    @Override
    @Transactional
    public RoleInfo updateRole(RoleDTO roleDTO) {
        SysRoleEntity sysRoleEntity = sysRoleRepository.getOne(roleDTO.getId());
        sysRoleEntity.setName(roleDTO.getName());
        sysRoleEntity.setRole(roleDTO.getRole());
        Set<SysResourceEntity> parent = sysResourceRepository.findByList(roleDTO.getResource());
        Set<SysResourceEntity> child = sysResourceRepository.findByUrlIn(roleDTO.getResource());
        parent.addAll(child);
        sysRoleEntity.setResources(parent);
        sysRoleEntity = sysRoleRepository.save(sysRoleEntity);
        RoleInfo result = new RoleInfo();
        BeanUtils.copyProperties(roleDTO, result);
        result.setId(sysRoleEntity.getId());
        return result;
    }

    @Override
    @Transactional
    public Page<RoleInfo> getRoles(int pageNumber, int pageSize) {
        PageRequest pageRequest = PageRequest.of(pageNumber - 1, pageSize);
        Page<SysRoleEntity> sysRoleEntities = sysRoleRepository.findAll(pageRequest);
        List<RoleInfo> roleInfos = sysRoleEntities.stream().map(this::getRoleInfo).collect(Collectors.toList());
        return new PageImpl<>(roleInfos, pageRequest, sysRoleEntities.getTotalElements());
    }

    @Override
    public void deleteRole(int roleId) {
        SysRoleEntity sysRoleEntity = sysRoleRepository.getOne(roleId);
        if (sysRoleEntity.isAdmin()) {
            throw new FlightyThoughtException("Admin 权限不能删除");
        }
        sysRoleRepository.delete(sysRoleEntity);
    }

    public RoleInfo getRoleInfo(SysRoleEntity sysRoleEntity) {
        List<String> resource = sysRoleEntity.getResources().stream().filter(sysResourceEntity -> sysResourceEntity.getResourceType() == 0).map(SysResourceEntity::getUrl)
            .collect(Collectors.toList());
        RoleInfo roleInfo = new RoleInfo();
        roleInfo.setResource(resource);
        roleInfo.setId(sysRoleEntity.getId());
        roleInfo.setName(sysRoleEntity.getName());
        roleInfo.setRole(sysRoleEntity.getRole());
        return roleInfo;
    }

    @Override
    public List<ResourceInfo> getResource() {
        List<SysResourceEntity> entities = sysResourceRepository.findAll();
        List<ResourceInfo> resourceInfos = entities.stream().filter(resource -> resource.getParentId() == null).map(resource -> {
            ResourceInfo resourceInfo = new ResourceInfo();
            resourceInfo.setId(resource.getUrl());
            resourceInfo.setLable(resource.getName());
            List<SysResourceEntity> childEntities = entities.stream().filter(r -> r.getParentId()!=null).filter(r -> r.getParentId().equals(resource.getId())).collect(Collectors.toList());
            if (childEntities != null && childEntities.size() > 0) {
                List<ResourceInfo> childResourceInfos = childEntities.stream().map(cr -> {
                    ResourceInfo childResourceInfo = new ResourceInfo();
                    childResourceInfo.setId(cr.getUrl());
                    childResourceInfo.setLable(cr.getName());
                    return childResourceInfo;
                }).collect(Collectors.toList());
                resourceInfo.setChildren(childResourceInfos);
            }
            return resourceInfo;
        }).collect(Collectors.toList());
        return resourceInfos;
    }
}
