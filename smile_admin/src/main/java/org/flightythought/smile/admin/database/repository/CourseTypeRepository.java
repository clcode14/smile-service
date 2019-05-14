package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.CourseTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseTypeRepository extends JpaRepository<CourseTypeEntity, Long> {
    CourseTypeEntity findById(Integer id);
}
