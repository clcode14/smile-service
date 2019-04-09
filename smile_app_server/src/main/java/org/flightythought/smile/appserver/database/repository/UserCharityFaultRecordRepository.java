package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCharityFaultRecordRepository extends JpaRepository<UserCharityFaultRecordEntity, Long> {
    UserCharityFaultRecordEntity findById(Integer id);
}
