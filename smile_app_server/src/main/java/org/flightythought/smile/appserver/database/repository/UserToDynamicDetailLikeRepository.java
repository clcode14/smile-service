package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserToDynamicDetailLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserToDynamicDetailLikeRepository extends JpaRepository<UserToDynamicDetailLikeEntity, Long> {
}
