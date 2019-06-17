package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.HealthIntroductionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthIntroductionRepository extends JpaRepository<HealthIntroductionEntity, Long> {

}
