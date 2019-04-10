package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyNoteNormEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteNormRepository
 * @CreateTime 2019/4/10 23:37
 * @Description: TODO
 */
@Repository
public interface JourneyNoteNormRepository extends JpaRepository<JourneyNoteNormEntity, Long> {
}
