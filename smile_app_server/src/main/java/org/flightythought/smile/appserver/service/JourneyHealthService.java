package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.appserver.database.entity.JourneyEntity;
import org.flightythought.smile.appserver.dto.HealthJourneyStartDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JourneyHealthService {
    List<HealthNormTypeEntity> getHealthNormTypes();

    List<FileInfo> uploadReport(List<MultipartFile> files, Integer type) throws FlightyThoughtException;

    JourneyEntity startHealthJourney(HealthJourneyStartDTO healthJourneyStartDTO);
}
