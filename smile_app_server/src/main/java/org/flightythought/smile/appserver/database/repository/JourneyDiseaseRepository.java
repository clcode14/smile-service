package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.JourneyDiseaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JourneyDiseaseRepository extends JpaRepository<JourneyDiseaseEntity, Long> {
}
