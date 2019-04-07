package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolutionRepository extends JpaRepository<SolutionEntity, Long> {
    List<SolutionEntity> findByIdIn(List<Integer> solutionIds);

    SolutionEntity findById(Integer id);
}
