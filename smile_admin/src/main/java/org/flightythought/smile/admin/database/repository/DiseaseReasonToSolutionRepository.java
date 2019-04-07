package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.DiseaseReasonToSolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseReasonToSolutionRepository
 * @CreateTime 2019/4/2 1:04
 * @Description: TODO
 */
@Repository
public interface DiseaseReasonToSolutionRepository extends JpaRepository<DiseaseReasonToSolutionEntity, Long> {
    void deleteAllByDiseaseReasonId(Integer diseaseReasonId);
}
