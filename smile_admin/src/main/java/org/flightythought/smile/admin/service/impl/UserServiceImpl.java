package org.flightythought.smile.admin.service.impl;

import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.SysUserRepository;
import org.flightythought.smile.admin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lilei
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserRepository sysUserRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        SysUserEntity sysUserEntity = sysUserRepository.findByLoginName(s);
        if (sysUserEntity == null) {
            throw new UsernameNotFoundException("用户名输入错误");
        }
        return sysUserEntity;
    }
}
