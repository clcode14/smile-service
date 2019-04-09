package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.DiseaseReasonEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseReasonRepository extends JpaRepository<DiseaseReasonEntity, Long> {

    Page<DiseaseReasonEntity> findByDiseaseDetailIdAndType(Integer diseaseDetailId, Integer type, Pageable pageable);

    Page<DiseaseReasonEntity> findByDiseaseDetailId(Integer diseaseDetailId, Pageable pageable);

    List<DiseaseReasonEntity> findByDiseaseDetailId(Integer diseaseDetailId);

    List<DiseaseReasonEntity> findByDiseaseDetailIdAndType(Integer diseaseDetailId, Integer type);

    DiseaseReasonEntity findById(Integer reasonId);
}
