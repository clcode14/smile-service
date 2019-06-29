package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.HotSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotSearchRepository extends JpaRepository<HotSearchEntity, Long> {

    HotSearchEntity findById(Integer id);
}
