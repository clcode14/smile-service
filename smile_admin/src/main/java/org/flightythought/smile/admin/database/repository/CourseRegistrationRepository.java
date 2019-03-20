package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.CourseRegistrationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistrationEntity, Long> {
}
