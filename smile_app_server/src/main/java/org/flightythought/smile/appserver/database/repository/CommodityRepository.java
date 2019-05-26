package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 13:22
 * @Description: TODO
 */
@Repository
public interface CommodityRepository extends JpaRepository<CommodityEntity, Integer> {
    List<CommodityEntity> findByCommodityIdIn(List<Integer> commodityIds);
}
