package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.DiseaseReasonInfo;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.database.entity.DiseaseReasonEntity;
import org.flightythought.smile.admin.dto.DiseaseReasonDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseReasonService
 * @CreateTime 2019/4/2 0:35
 * @Description: TODO
 */
public interface DiseaseReasonService {

    List<SelectItemOption> getDiseaseReasonType();

    DiseaseReasonEntity saveDiseaseReason(DiseaseReasonDTO diseaseReasonDTO, HttpSession session) throws FlightyThoughtException;

    DiseaseReasonEntity modifyDiseaseReason(DiseaseReasonDTO diseaseReasonDTO, HttpSession session) throws FlightyThoughtException;

    Page<DiseaseReasonInfo> findAllDiseaseReason(Map<String,String> params, HttpSession session);

    DiseaseReasonInfo findDiseaseReasonById(Integer id, HttpSession session);

    void deleteDiseaseReason(Integer id, HttpSession session);
}
