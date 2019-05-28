package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.FilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FilesRepository extends JpaRepository<FilesEntity, Long> {
}
