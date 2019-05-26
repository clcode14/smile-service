package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.CommodityEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 15:21
 * @Description: TODO
 */
@Repository
public interface CommodityRepository extends JpaRepository<CommodityEntity, Long> {
    List<CommodityEntity> findByCommodityIdIn(List<Integer> commodityIds);

    CommodityEntity findByCommodityId(Integer commodityId);
}
