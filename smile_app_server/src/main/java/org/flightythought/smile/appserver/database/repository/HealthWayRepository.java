package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.HealthWayEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthWayRepository extends JpaRepository<HealthWayEntity, Long> {

    Page<HealthWayEntity> findByHealthWayIdIn(List<Integer> healthWayIds, Pageable pageable);

    List<HealthWayEntity> findByHealthWayIdIn(List<Integer> healthWayIds);
}
