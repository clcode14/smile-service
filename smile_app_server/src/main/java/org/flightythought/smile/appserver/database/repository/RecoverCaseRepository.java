package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.RecoverCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecoverCaseRepository extends JpaRepository<RecoverCaseEntity, Long> {
    List<RecoverCaseEntity> findByIdIn(List<Integer> recoverIds);

    RecoverCaseEntity findByJourneyIdAndId(Integer journeyId, Integer recoverId);
}
