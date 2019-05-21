package org.flightythought.smile.admin.database.repository;//package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.admin.database.entity.RecoverCaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecoverCaseRepository extends JpaRepository<RecoverCaseEntity, Integer> {

    List<RecoverCaseEntity> findBySolutionIdAndUserId(Integer solutionId, Long userId);
}
