package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.DynamicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DynamicRepository extends JpaRepository<DynamicEntity, Long> {
    DynamicEntity findByDynamicId(Integer dynamicId);

    List<DynamicEntity> findByDynamicIdIn(List<Integer> dynamicIds);
}
