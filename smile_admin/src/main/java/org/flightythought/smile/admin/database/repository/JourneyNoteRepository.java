package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.JourneyNoteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyRepository
 * @CreateTime 2019/4/10 2:43
 * @Description: TODO
 */
@Repository
public interface JourneyNoteRepository extends JpaRepository<JourneyNoteEntity, Long>,JpaSpecificationExecutor<JourneyNoteEntity>  {
}
