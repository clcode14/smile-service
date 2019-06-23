package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.JourneyNoteToImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteToImageRepository
 * @CreateTime 2019/6/23 0:17
 * @Description: TODO
 */
@Repository
public interface JourneyNoteToImageRepository extends JpaRepository<JourneyNoteToImageEntity, Integer> {
}
