package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.AppUserInfo;
import org.flightythought.smile.admin.dto.AppUserQueryDTO;
import org.springframework.data.domain.Page;

/**
 * APP用户管理服务
 * 
 * @author cl47872
 * @version $Id: AppUserService.java, v 0.1 Jun 4, 2019 9:47:23 PM cl47872 Exp $
 */
public interface AppUserService{
    
    /**
     * 分页查询APP用户信息列表
     * 
     * @param appUserQueryDTO
     * @return
     */
    Page<AppUserInfo> findUsers(AppUserQueryDTO appUserQueryDTO);

}
