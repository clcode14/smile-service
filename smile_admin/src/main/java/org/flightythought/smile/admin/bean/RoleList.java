package org.flightythought.smile.admin.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName RoleList
 * @CreateTime 2019/6/10 21:06
 * @Description: TODO
 */
@Data
public class RoleList {
    /**
     * 资源唯一性标识
     */
    private String id;

    /**
     * Label
     */
    private String label;

    /**
     * 子资源
     */
    private List<RoleList> children;
}
