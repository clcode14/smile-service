package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, Long> {
}
