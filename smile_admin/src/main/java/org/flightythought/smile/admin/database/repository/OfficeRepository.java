package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.OfficeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OfficeRepository extends JpaRepository<OfficeEntity,Long> {
}
