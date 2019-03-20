package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.CourseImageEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseImageRepository extends JpaRepository<CourseImageEntity, Long> {
}
