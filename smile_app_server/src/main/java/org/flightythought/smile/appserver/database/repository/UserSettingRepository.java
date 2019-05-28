package org.flightythought.smile.appserver.database.repository;

import org.flightythought.smile.appserver.database.entity.UserSettingEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserSettingRepository
 * @CreateTime 2019/5/27 2:09
 * @Description: TODO
 */
@Repository
public interface UserSettingRepository extends JpaRepository<UserSettingEntity, Long> {
    UserSettingEntity findByUserIdAndCode(Long userId, String code);
}
