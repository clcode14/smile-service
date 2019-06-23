package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.RecoverCaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecoverCaseRepository extends JpaRepository<RecoverCaseEntity, Long> {
    List<RecoverCaseEntity> findByIdIn(List<Integer> recoverIds);

    RecoverCaseEntity findByJourneyIdAndId(Integer journeyId, Integer recoverId);

    List<RecoverCaseEntity> findByJourneyId(Integer journeyId);

    @Query("select distinct rc from  RecoverCaseEntity rc " +
            "left join JourneyEntity j " +
            "on rc.journeyId = j.journeyId " +
            "left join SolutionEntity  s " +
            "on rc.solutionId = s.id " +
            "where rc.title like %:name% " +
            "or j.journeyName like %:name% " +
            "or s.title like %:name%")
    Page<RecoverCaseEntity> searchRecoverCase(@Param("name") String name, Pageable pageable);
}
