package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.CourseBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseBannerRepository extends JpaRepository<CourseBannerEntity, Long> {

    List<CourseBannerEntity> findByStatus(Boolean status);
}
