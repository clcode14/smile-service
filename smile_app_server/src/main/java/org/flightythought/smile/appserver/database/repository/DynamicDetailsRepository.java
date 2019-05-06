package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.DynamicDetailsEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicDetailsRepository extends JpaRepository<DynamicDetailsEntity, Long> {
    List<DynamicDetailsEntity> findByDynamicIdAndHidden(Integer dynamicId, boolean hidden);

    List<DynamicDetailsEntity> findByDynamicId(Integer dynamicId);

    DynamicDetailsEntity findByDynamicDetailId(Integer dynamicDetailId);

    Page<DynamicDetailsEntity> findByDynamicIdAndHidden(Integer dynamicId, boolean hidden, Pageable pageable);

    Page<DynamicDetailsEntity> findByDynamicId(Integer dynamicId, Pageable pageable);
}
