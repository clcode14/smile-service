package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.DiseaseReasonEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseReasonRepository
 * @CreateTime 2019/4/2 0:43
 * @Description: TODO
 */
@Repository
public interface DiseaseReasonRepository extends JpaRepository<DiseaseReasonEntity, Long> {
    DiseaseReasonEntity findById(Integer id);
}
