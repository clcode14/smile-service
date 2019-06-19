package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SysResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SysResourceRepository
 * @CreateTime 2019/6/11 1:06
 * @Description: TODO
 */
@Repository
public interface SysResourceRepository extends JpaRepository<SysResourceEntity, Integer> {
}