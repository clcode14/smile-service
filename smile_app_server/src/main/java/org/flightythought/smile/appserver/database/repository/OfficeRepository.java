package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeRepository
 * @CreateTime 2019/5/22 0:29
 * @Description: TODO
 */
@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity, Long> {

    List<OfficeEntity> findByOfficeIdIn(List<Long> officeId);
}
