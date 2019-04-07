package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.DiseaseReason;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.database.entity.DiseaseReasonEntity;
import org.flightythought.smile.appserver.dto.AboutDiseaseDetailQueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface DiseaseReasonService {
    Page<DiseaseReasonEntity> getDiseaseReason(AboutDiseaseDetailQueryDTO diseaseDetailQueryDTO) throws FlightyThoughtException;

    List<Map<String, String>> getDiseaseTypes();

    DiseaseReason getDiseaseReasonInfo(String reasonId) throws FlightyThoughtException;
}
