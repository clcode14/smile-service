package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.DiseaseClassDetailEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseClassDetailRepository extends JpaRepository<DiseaseClassDetailEntity, Long> {
    List<DiseaseClassDetailEntity> findByType(String type);

    List<DiseaseClassDetailEntity> findByDiseaseDetailIdIn(List<Integer> diseaseDetailIds);

    DiseaseClassDetailEntity findByDiseaseDetailId(Integer diseaseDetailId);
}
