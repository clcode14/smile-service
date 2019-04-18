package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.MedicalReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReportEntity, Long> {
    MedicalReportEntity findById(Integer id);
}
