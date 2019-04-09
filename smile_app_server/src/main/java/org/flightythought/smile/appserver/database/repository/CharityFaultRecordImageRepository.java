package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.CharityFaultRecordImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharityFaultRecordImageRepository extends JpaRepository<CharityFaultRecordImageEntity, Long> {
    List<CharityFaultRecordImageEntity> findByCharityFaultRecordId(Integer charityFaultRecordId);
}
