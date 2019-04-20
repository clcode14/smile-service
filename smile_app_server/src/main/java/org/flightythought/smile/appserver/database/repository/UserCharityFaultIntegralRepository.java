package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserCharityFaultIntegralEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCharityFaultIntegralRepository extends JpaRepository<UserCharityFaultIntegralEntity, Long> {
    UserCharityFaultIntegralEntity findByUserId(Long userId);
}
