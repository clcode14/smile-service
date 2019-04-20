package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyDiseaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface JourneyDiseaseRepository extends JpaRepository<JourneyDiseaseEntity, Long> {
    void deleteAllByJourneyId(Integer journeyId);

    List<JourneyDiseaseEntity> findByJourneyId(Integer journeyId);
}
