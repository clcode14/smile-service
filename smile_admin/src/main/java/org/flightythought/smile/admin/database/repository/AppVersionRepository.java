package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.AppVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName AppVersionRepository
 * @CreateTime 2019/5/27 21:36
 * @Description: TODO
 */
@Repository
public interface AppVersionRepository extends JpaRepository<AppVersionEntity, Long> {
    List<AppVersionEntity> findByCurrent(Boolean current);
}
