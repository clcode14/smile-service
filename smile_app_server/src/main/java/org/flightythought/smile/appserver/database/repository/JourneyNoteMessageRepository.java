package org.flightythought.smile.appserver.database.repository;

import java.util.List;

import org.flightythought.smile.appserver.database.entity.JourneyNoteMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyNoteMessageRepository extends JpaRepository<JourneyNoteMessageEntity, Long> {
    List<JourneyNoteMessageEntity> findByJourneyNoteIdAndParentIdIsNullOrderByCreateTimeDesc(Integer journeyNoteId);

    Page<JourneyNoteMessageEntity> findByJourneyNoteIdAndParentIdIsNullOrderByCreateTimeDesc(Integer journeyNoteId, Pageable pageable);

    Page<JourneyNoteMessageEntity> findByJourneyNoteIdAndParentIdOrderByCreateTimeDesc(Integer journeyNoteId,Integer parentId, Pageable pageable);

    JourneyNoteMessageEntity findById(Integer messageId);

    List<JourneyNoteMessageEntity> findByJourneyNoteId(Integer journeyNoteId);
}
