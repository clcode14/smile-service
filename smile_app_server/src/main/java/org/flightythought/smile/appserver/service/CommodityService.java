package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.CommoditySimple;
import org.flightythought.smile.appserver.database.entity.CommodityEntity;
import org.flightythought.smile.appserver.dto.CommodityQueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 13:55
 * @Description: TODO
 */
public interface CommodityService {

    Page<CommoditySimple> getCommofities(CommodityQueryDTO commodityQueryDTO);

    List<CommoditySimple> getCommoditySimples(List<CommodityEntity> commodityEntities);
}
