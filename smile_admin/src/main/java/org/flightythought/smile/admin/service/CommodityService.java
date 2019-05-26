package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.Commodity;
import org.flightythought.smile.admin.database.entity.CommodityEntity;
import org.flightythought.smile.admin.dto.CommodityDTO;
import org.flightythought.smile.admin.dto.CommodityQueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 15:17
 * @Description: TODO
 */
public interface CommodityService {

    CommodityEntity addCommodityEntity(CommodityDTO commodityDTO);

    CommodityEntity updateCommodityEntity(CommodityDTO commodityDTO);

    Page<Commodity> getCommodities(CommodityQueryDTO commodityQueryDTO);

    Commodity getCommodity(Integer commodityId);

    List<Commodity> getCommodities(List<CommodityEntity> commodityEntities);

    void deleteCommodity(Integer commodityId);
}
