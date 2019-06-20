package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.RoleInfo;
import org.flightythought.smile.admin.dto.RoleDTO;
import org.springframework.data.domain.Page;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName RoleService
 * @CreateTime 2019/6/20 1:37
 * @Description: TODO
 */
public interface RoleService {
    RoleInfo addRole(RoleDTO roleDTO);

    RoleInfo updateRole(RoleDTO roleDTO);

    Page<RoleInfo> getRoles(int pageNumber, int pageSize);

    void deleteRole(int roleId);
}
