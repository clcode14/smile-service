package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SysRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SysRoleRepository
 * @CreateTime 2019/6/20 1:38
 * @Description: TODO
 */
@Repository
public interface SysRoleRepository extends JpaRepository<SysRoleEntity, Integer> {
}
