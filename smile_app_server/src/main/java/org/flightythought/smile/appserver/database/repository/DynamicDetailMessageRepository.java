package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.DynamicDetailMessageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicDetailMessageRepository extends JpaRepository<DynamicDetailMessageEntity, Long> {
    List<DynamicDetailMessageEntity> findByDynamicDetailIdAndParentIdIsNull(Integer dynamicDetailId);
}
