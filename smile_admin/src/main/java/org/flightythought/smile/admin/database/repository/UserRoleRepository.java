package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.UserRoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * 账户角色表
 * 
 * @author cl47872
 * @version $Id: UserRoleRepository.java, v 0.1 Jun 20, 2019 1:34:14 AM cl47872 Exp $
 */
@Repository
public interface UserRoleRepository extends JpaRepository<UserRoleEntity, Integer> {
    void deleteAllByUserId(Integer userId);
}
