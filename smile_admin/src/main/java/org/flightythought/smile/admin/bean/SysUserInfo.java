package org.flightythought.smile.admin.bean;

import java.time.LocalDateTime;
import java.util.List;

import org.flightythought.smile.admin.framework.serializer.JsonLocalDateTimeSerializer;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import lombok.Data;

/**
 * 系统账号信息
 * 
 * @author cl47872
 * @version $Id: SysUserInfo.java, v 0.1 Jun 20, 2019 12:15:37 AM cl47872 Exp $
 */
@Data
public class SysUserInfo {
    /**
     * 账号ID
     */
    private Integer       id;
    /**
     * 账号
     */
    private String        loginName;

    /**
     * 账号创建人
     */
    private String        createUserName;

    /**
     * 创建时间
     */
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    private LocalDateTime createTime;

    /**
     * 修改人
     */
    private String        updateUserName;

    /**
     * 修改时间
     */
    @JsonSerialize(using = JsonLocalDateTimeSerializer.class)
    private LocalDateTime updateTime;

    /**
     * 角色ID
     */
    private List<Integer> roleIds;

}
