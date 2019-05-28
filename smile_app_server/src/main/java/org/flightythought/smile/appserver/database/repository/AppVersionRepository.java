package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.AppVersionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName AppVersionRepository
 * @CreateTime 2019/5/27 22:35
 * @Description: TODO
 */
@Repository
public interface AppVersionRepository extends JpaRepository<AppVersionEntity, Long> {
    AppVersionEntity findByCurrent(Boolean current);
}
