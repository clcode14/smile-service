package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserFollowSolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserFollowSolutionRepository extends JpaRepository<UserFollowSolutionEntity, Long> {
    UserFollowSolutionEntity findByUserIdAndSolutionId(Long userId, Integer solutionId);
}
