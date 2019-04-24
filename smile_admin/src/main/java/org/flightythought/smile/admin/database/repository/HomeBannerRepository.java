package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.HomeBannerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface HomeBannerRepository extends JpaRepository<HomeBannerEntity, Integer> {
}
