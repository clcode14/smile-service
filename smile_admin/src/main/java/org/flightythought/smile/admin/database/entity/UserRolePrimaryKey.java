package org.flightythought.smile.admin.database.entity;

import java.io.Serializable;

import lombok.Data;

@Data
public class UserRolePrimaryKey implements Serializable{

    /**  */
    private static final long serialVersionUID = -8156209786181114849L;

    /**
     * 用户ID
     */
    private Integer userId;

    /**
     * 权限ID
     */
    private Integer roleId;
}
