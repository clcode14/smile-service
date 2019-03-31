package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SolutionImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionImageRepository
 * @CreateTime 2019/3/27 22:06
 * @Description: 解决方案图片持久层
 */
@Repository
public interface SolutionImageRepository extends JpaRepository<SolutionImage, Long> {
}
