package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.HealthWayMusicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthWayMusicRepository
 * @CreateTime 2019/5/26 13:48
 * @Description: TODO
 */
@Repository
public interface HealthWayMusicRepository extends JpaRepository<HealthWayMusicEntity, Integer> {

    List<HealthWayMusicEntity> findByHealthWayId(Integer healthWayId);

    void deleteAllByHealthWayId(Integer healthWayId);
}
