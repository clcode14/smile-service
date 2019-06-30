package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.HotSearchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HotSearchRepository extends JpaRepository<HotSearchEntity, Long> {

    HotSearchEntity findById(Integer id);
}
