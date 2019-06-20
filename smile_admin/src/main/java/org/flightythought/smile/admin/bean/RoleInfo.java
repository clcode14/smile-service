package org.flightythought.smile.admin.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName RoleInfo
 * @CreateTime 2019/6/20 1:54
 * @Description: TODO
 */
@Data
public class RoleInfo {
    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 角色标签名
     */
    private String role;

    /**
     * 角色名称
     */
    private String name;

    /**
     * 资源集合
     */
    private List<String> resource;
}
