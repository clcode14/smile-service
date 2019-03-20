package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.DiseaseClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/21 13:25
 */
@Repository
public interface DiseaseClassRepository extends JpaRepository<DiseaseClassEntity, Long> {
    DiseaseClassEntity findByDiseaseId(Integer id);
}
