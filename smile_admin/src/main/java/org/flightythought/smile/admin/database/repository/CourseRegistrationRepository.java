package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.CourseRegistrationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistrationEntity, Integer> {
    CourseRegistrationEntity findByCourseId(Integer courseId);

    List<CourseRegistrationEntity> findByCourseIdIn(List<Integer> courseIds);

    Page<CourseRegistrationEntity> findByCourseIdNotIn(List<Integer> courseId, Pageable pageable);
}
