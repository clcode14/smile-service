package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.HealthNormTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthNormTypeRepository extends JpaRepository<HealthNormTypeEntity, Long> {
    HealthNormTypeEntity findByNormTypeId(Integer normTypeId);
}
