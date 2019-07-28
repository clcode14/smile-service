package org.flightythought.smile.appserver.database.repository;

import java.util.List;

import org.flightythought.smile.appserver.database.entity.UserToJourneyNoteLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToJourneyNoteLikeRepository extends JpaRepository<UserToJourneyNoteLikeEntity, Long> {
    List<UserToJourneyNoteLikeEntity> findByUserIdAndJourneyNoteIdIn(Long userId, List<Integer> journeyNoteIds);

    UserToJourneyNoteLikeEntity findByUserIdAndJourneyNoteId(Long userId, Integer journeyNoteId);
}
