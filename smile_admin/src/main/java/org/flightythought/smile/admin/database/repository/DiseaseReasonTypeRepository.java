package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.DiseaseReasonTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseReasonTypeRepository
 * @CreateTime 2019/5/4 20:32
 * @Description: TODO
 */
@Repository
public interface DiseaseReasonTypeRepository extends JpaRepository<DiseaseReasonTypeEntity, Long> {
}
