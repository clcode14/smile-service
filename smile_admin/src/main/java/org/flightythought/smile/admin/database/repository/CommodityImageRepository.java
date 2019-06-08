package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.CommodityImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 15:22
 * @Description: TODO
 */
@Repository
public interface CommodityImageRepository extends JpaRepository<CommodityImageEntity, Long> {
    List<CommodityImageEntity> findByCommodityId(Integer commodityId);
}