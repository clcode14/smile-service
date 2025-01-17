package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName UserRepository
 * @CreateTime 2019/3/17 22:09
 * @Description: TODO
 */
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long>,JpaSpecificationExecutor<UserEntity> {

    UserEntity findByMobile(String mobile);

    UserEntity findByUsername(String username);
    
}
