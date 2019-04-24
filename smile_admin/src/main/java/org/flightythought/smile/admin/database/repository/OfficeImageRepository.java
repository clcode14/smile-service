package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.OfficeImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OfficeImageRepository extends JpaRepository<OfficeImageEntity, Long> {

    List<OfficeImageEntity> findByOfficeId(Long officeId);
}
