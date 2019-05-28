package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyToCommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyToCommodityRepository
 * @CreateTime 2019/5/26 14:44
 * @Description: TODO
 */
@Repository
public interface JourneyToCommodityRepository extends JpaRepository<JourneyToCommodityEntity, Integer> {
}
