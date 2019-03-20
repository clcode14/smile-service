package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.DiseaseClassDetailEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiseaseClassDetailRepository extends JpaRepository<DiseaseClassDetailEntity, Long> {

    Page<DiseaseClassDetailEntity> findByDiseaseId(Integer diseaseId, Pageable pageable);

    DiseaseClassDetailEntity findByDiseaseDetailId(Integer diseaseDetailId);
}
