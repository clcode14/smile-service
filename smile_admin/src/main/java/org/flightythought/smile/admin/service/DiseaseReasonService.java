package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.DiseaseReasonEntity;
import org.flightythought.smile.admin.dto.DiseaseReasonDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;

import javax.servlet.http.HttpSession;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseReasonService
 * @CreateTime 2019/4/2 0:35
 * @Description: TODO
 */
public interface DiseaseReasonService {
    DiseaseReasonEntity saveDiseaseReason(DiseaseReasonDTO diseaseReasonDTO, HttpSession session) throws FlightyThoughtException;
}
