package org.flightythought.smile.admin.database.repository;


import org.flightythought.smile.admin.database.entity.MedicalReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicalReportRepository extends JpaRepository<MedicalReportEntity, Long> {

}
