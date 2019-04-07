package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserFollowDiseaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserFollowDiseaseRepository extends JpaRepository<UserFollowDiseaseEntity, Long> {
    List<UserFollowDiseaseEntity> findByUserId(Long userId);

    UserFollowDiseaseEntity findByUserIdAndDiseaseDetailId(Long userId, Integer diseaseDetailId);
}
