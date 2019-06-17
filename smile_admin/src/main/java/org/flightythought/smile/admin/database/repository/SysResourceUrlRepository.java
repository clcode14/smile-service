package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SysResourceUrlEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SysResourceUrlRepository
 * @CreateTime 2019/6/11 1:04
 * @Description: TODO
 */
@Repository
public interface SysResourceUrlRepository extends JpaRepository<SysResourceUrlEntity, Integer> {
}
