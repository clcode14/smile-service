package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.SolutionPage;
import org.flightythought.smile.appserver.bean.SolutionSimple;
import org.flightythought.smile.appserver.database.entity.SolutionEntity;
import org.flightythought.smile.appserver.dto.HealthOrDiseaseQuerySolutionDTO;
import org.flightythought.smile.appserver.dto.SolutionQueryDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SolutionService {
    Page<SolutionSimple> getSolutionSimples(SolutionQueryDTO solutionQueryDTO);

    Page<SolutionEntity> getSolutions(SolutionQueryDTO solutionQueryDTO);

    SolutionPage getSolutionPage(Integer solutionId);

    Page<SolutionSimple> getSolutionSimples(HealthOrDiseaseQuerySolutionDTO querySolutionDTO);

    Page<SolutionEntity> getSolutions(HealthOrDiseaseQuerySolutionDTO querySolutionDTO);
}
