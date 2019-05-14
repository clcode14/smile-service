package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.HealthResultEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 15:28
 * @Description: TODO
 */
@Repository
public interface HealthResultRepository extends JpaRepository<HealthResultEntity, Integer> {
}
