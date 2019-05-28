package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.HealthWaySimple;
import org.flightythought.smile.appserver.database.entity.HealthWayEntity;
import org.flightythought.smile.appserver.database.entity.HealthWayMusicEntity;
import org.flightythought.smile.appserver.database.entity.HealthWayValueEntity;
import org.flightythought.smile.appserver.dto.HealthWayQueryDTO;
import org.flightythought.smile.appserver.dto.HealthWayValueDTO;
import org.flightythought.smile.appserver.dto.HealthWayValueQueryDTO;
import org.flightythought.smile.appserver.dto.PageFilterDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface HealthWayService {

    Page<HealthWayEntity> getHealthWayEntities(HealthWayQueryDTO healthWayQueryDTO);

    Page<HealthWaySimple> getHealthWays(HealthWayQueryDTO healthWayQueryDTO);

    List<HealthWayMusicEntity> getHealthWayMusics(Integer healthWayId);

    HealthWayValueEntity saveHealthWayValue(HealthWayValueDTO healthWayValueDTO);

    Page<HealthWayValueEntity> getHealthWayValue(HealthWayValueQueryDTO healthWayValueQueryDTO);
}
