package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SysResourceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SysResourceRepository
 * @CreateTime 2019/6/11 1:06
 * @Description: TODO
 */
@Repository
public interface SysResourceRepository extends JpaRepository<SysResourceEntity, Integer> {
    @Query("SELECT DISTINCT n FROM SysResourceEntity m INNER JOIN SysResourceEntity n ON m.parentId = n.id WHERE m.url IN (:urls)")
    Set<SysResourceEntity> findByList(@Param("urls") List<String> urls);

    Set<SysResourceEntity> findByUrlIn(List<String> urls);
}
