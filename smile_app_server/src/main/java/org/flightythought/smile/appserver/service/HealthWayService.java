package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.HealthWaySimple;
import org.flightythought.smile.appserver.database.entity.HealthWayEntity;
import org.flightythought.smile.appserver.dto.HealthWayQueryDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.springframework.data.domain.Page;

public interface HealthWayService {

    Page<HealthWayEntity> getHealthWayEntities(HealthWayQueryDTO healthWayQueryDTO);

    Page<HealthWaySimple> getHealthWays(HealthWayQueryDTO healthWayQueryDTO);
}
