package org.flightythought.smile.admin.database.repository;

import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author lilei
 */
@Repository
public interface SysUserRepository extends JpaRepository<SysUserEntity, Long> {
    SysUserEntity findByLoginName(String loginName);
}
