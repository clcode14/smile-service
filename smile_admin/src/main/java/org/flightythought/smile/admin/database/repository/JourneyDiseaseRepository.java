package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.JourneyDiseaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyDiseaseRepository
 * @CreateTime 2019/6/23 0:08
 * @Description: TODO
 */
@Repository
public interface JourneyDiseaseRepository extends JpaRepository<JourneyDiseaseEntity, Integer> {
}
