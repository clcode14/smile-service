package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;

public interface JourneyHealthService {
    Page<HealthNormTypeEntity> getHealthNormType(Integer pageSize, Integer pageNumber);

    HealthNormTypeEntity saveOrUpdateHealthNormType(HealthNormTypeEntity healthNormTypeEntity, HttpSession session) throws FlightyThoughtException;

    void deleteHealthNormType(Integer normTypeId);
}
