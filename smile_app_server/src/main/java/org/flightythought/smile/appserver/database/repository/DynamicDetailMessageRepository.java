package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.DynamicDetailMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicDetailMessageRepository extends JpaRepository<DynamicDetailMessageEntity, Long> {
    List<DynamicDetailMessageEntity> findByDynamicDetailIdAndParentIdIsNullOrderByCreateTimeDesc(Integer dynamicDetailId);

    Page<DynamicDetailMessageEntity> findByDynamicDetailIdAndParentIdIsNullOrderByCreateTimeDesc(Integer dynamicDetailId, Pageable pageable);

    Page<DynamicDetailMessageEntity> findByDynamicDetailIdAndParentIdOrderByCreateTimeDesc(Integer dynamicDetailId,Integer parentId, Pageable pageable);

    DynamicDetailMessageEntity findById(Integer messageId);
}
