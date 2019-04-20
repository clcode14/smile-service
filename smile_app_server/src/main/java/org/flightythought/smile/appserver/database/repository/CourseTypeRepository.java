package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.CourseTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTypeRepository extends JpaRepository<CourseTypeEntity, Long> {
}
