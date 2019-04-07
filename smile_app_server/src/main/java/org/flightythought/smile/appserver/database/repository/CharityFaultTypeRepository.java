package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.CharityFaultTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharityFaultTypeRepository extends JpaRepository<CharityFaultTypeEntity, Long> {

    List<CharityFaultTypeEntity> findByCfTypeId(Integer cfTypeId);
}
