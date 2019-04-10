package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyNoteToImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteToImageRepository
 * @CreateTime 2019/4/10 23:16
 * @Description: TODO
 */
@Repository
public interface JourneyNoteToImageRepository extends JpaRepository<JourneyNoteToImageEntity, Long> {
}
