package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.SolutionPage;
import org.flightythought.smile.appserver.bean.SolutionSimple;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.database.entity.SolutionEntity;
import org.flightythought.smile.appserver.dto.HealthOrDiseaseByIdQueryDTO;
import org.flightythought.smile.appserver.dto.SolutionQueryDTO;
import org.springframework.data.domain.Page;

public interface SolutionService {
    Page<SolutionSimple> getSolutionSimples(SolutionQueryDTO solutionQueryDTO);

    Page<SolutionEntity> getSolutions(SolutionQueryDTO solutionQueryDTO);

    SolutionPage getSolutionPage(Integer solutionId);

    Page<SolutionSimple> getSolutionSimples(HealthOrDiseaseByIdQueryDTO querySolutionDTO) throws FlightyThoughtException;

    Page<SolutionEntity> getSolutions(HealthOrDiseaseByIdQueryDTO querySolutionDTO) throws FlightyThoughtException;
}
