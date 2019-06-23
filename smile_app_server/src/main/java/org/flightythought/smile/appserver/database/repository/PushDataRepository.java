package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.PushDataEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName PushDataRepository
 * @CreateTime 2019/5/27 18:18
 * @Description: TODO
 */
@Repository
public interface PushDataRepository extends JpaRepository<PushDataEntity, Long> {
    Page<PushDataEntity> findByUserIdAndReadAndTypeOrderByCreateTimeDesc(Long userId, Boolean read, Integer type, Pageable pageable);

    Page<PushDataEntity> findByUserIdAndTypeOrderByCreateTimeDesc(Long userId, Integer type, Pageable pageable);

    List<PushDataEntity> findByUserIdAndReadAndType(Long userId, Boolean read, Integer type);

    List<PushDataEntity> findByUserId(Long userId);

    PushDataEntity findById(Integer pushDataId);

    int countByTypeAndUserIdAndRead(Integer type, Long userId, Boolean read);
}
