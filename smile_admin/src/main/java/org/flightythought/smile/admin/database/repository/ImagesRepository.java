package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ImagesRepository extends JpaRepository<ImagesEntity, Long> {
}
