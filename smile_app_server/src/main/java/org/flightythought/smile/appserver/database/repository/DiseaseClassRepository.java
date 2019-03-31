package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.DiseaseClassEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiseaseClassRepository extends JpaRepository<DiseaseClassEntity, Long> {
    List<DiseaseClassEntity> findByDiseaseIdIn(List<Integer> diseaseIds);

    List<DiseaseClassEntity> findByDiseaseIdNotIn(List<Integer> diseaseIds);
}
