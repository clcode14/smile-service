package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyNoteEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNoteRepository
 * @CreateTime 2019/4/10 22:56
 * @Description: TODO
 */
@Repository
public interface JourneyNoteRepository extends JpaRepository<JourneyNoteEntity, Long> {

    JourneyNoteEntity findById(Integer id);

    List<JourneyNoteEntity> findByJourneyId(Integer journeyId);

    Page<JourneyNoteEntity> findByJourneyId(Integer journeyId, Pageable pageable);
}
