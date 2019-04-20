package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.HomeBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HomeBannerRepository
 * @CreateTime 2019/4/14 19:28
 * @Description: TODO
 */
@Repository
public interface HomeBannerRepository extends JpaRepository<HomeBannerEntity, Long> {
}
