package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserToDynamicDetailLikeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserToDynamicDetailLikeRepository extends JpaRepository<UserToDynamicDetailLikeEntity, Long> {
    List<UserToDynamicDetailLikeEntity> findByUserIdAndDynamicDetailIdIn(Long userId, List<Integer> dynamicDetailIds);

    UserToDynamicDetailLikeEntity findByUserIdAndDynamicDetailId(Long userId, Integer dynamicDetailId);
}
