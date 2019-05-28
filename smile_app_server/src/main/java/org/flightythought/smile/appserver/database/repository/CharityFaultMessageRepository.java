package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.CharityFaultMessageEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CharityFaultMessageRepository
 * @CreateTime 2019/5/26 17:53
 * @Description: TODO
 */
@Repository
public interface CharityFaultMessageRepository extends JpaRepository<CharityFaultMessageEntity, Long> {

    Page<CharityFaultMessageEntity> findByCharityFaultIdAndParentIdIsNullOrderByCreateTimeDesc(Integer charityFaultId, Pageable pageable);

    Page<CharityFaultMessageEntity> findByCharityFaultIdAndParentIdOrderByCreateTimeDesc(Integer charityFaultId,Integer parentId, Pageable pageable);

    CharityFaultMessageEntity findById(Integer id);
}
