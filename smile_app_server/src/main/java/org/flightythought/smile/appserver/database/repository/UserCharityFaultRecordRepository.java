package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCharityFaultRecordRepository extends JpaRepository<UserCharityFaultRecordEntity, Long> {
    UserCharityFaultRecordEntity findById(Integer id);

    Page<UserCharityFaultRecordEntity> findByCfTypeIdAndHiddenOrderByCreateTimeDesc(Integer cfTypeId, Boolean hidden, Pageable pageable);

    Page<UserCharityFaultRecordEntity> findByUserIdAndCfTypeIdAndHiddenOrderByCreateTimeDesc(Long userId, Integer cfTypeId, Boolean hidden, Pageable pageable);

    Page<UserCharityFaultRecordEntity> findByHiddenOrderByCreateTimeDesc(Boolean hidden, Pageable pageable);

    Page<UserCharityFaultRecordEntity> findByUserIdAndHiddenOrderByCreateTimeDesc(Long userId, Boolean hidden, Pageable pageable);

    Page<UserCharityFaultRecordEntity> findByUserIdOrderByCreateTimeDesc(Long userId, Pageable pageable);
}
