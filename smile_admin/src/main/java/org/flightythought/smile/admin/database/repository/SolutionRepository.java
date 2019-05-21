package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SolutionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionRepository
 * @CreateTime 2019/3/27 22:00
 * @Description: 解决方案持久层
 */
@Repository
public interface SolutionRepository extends JpaRepository<SolutionEntity, Integer> {
    @Transactional
    @Query("update SolutionEntity s set s.recoverNumber=s.recoverNumber-1 where s.id=?1")
    @Modifying
    void updateRecoverNum(Integer solutionId);

    List<SolutionEntity> findByIdIn(List<Integer> ids);
}
