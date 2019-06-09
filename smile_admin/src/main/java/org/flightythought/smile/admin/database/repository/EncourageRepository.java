package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.EncourageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EncourageRepository extends JpaRepository<EncourageEntity, Long> {

    EncourageEntity findById(Integer id);
}
