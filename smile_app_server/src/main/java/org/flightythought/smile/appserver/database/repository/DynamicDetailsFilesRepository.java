package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.DynamicDetailsEntity;
import org.flightythought.smile.appserver.database.entity.DynamicDetailsFilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DynamicDetailsFilesRepository extends JpaRepository<DynamicDetailsFilesEntity, Long> {
}
