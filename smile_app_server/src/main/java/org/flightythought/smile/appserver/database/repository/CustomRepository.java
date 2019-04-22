package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.CustomEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CustomRepository
 * @CreateTime 2019/4/21 21:29
 * @Description: TODO
 */
@Repository
public interface CustomRepository extends JpaRepository<CustomEntity, Long> {
}
