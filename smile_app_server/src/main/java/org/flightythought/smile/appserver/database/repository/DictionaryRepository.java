package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.DictionaryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DictionaryRepository extends JpaRepository<DictionaryEntity, Long> {
}
