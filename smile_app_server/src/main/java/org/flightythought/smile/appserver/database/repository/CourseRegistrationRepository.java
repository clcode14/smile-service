package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.CourseRegistrationEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistrationEntity, Long> {

    List<CourseRegistrationEntity> findByCourseIdIn(List<Integer> courseIds);

    CourseRegistrationEntity findByCourseId(Integer courseId);


}
