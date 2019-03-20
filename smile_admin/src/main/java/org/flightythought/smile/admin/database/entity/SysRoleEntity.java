package org.flightythought.smile.admin.database.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/15 11:10
 */
@Entity
@Table(name = "tb_sys_role", schema = "smile", catalog = "")
public class SysRoleEntity extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private int id;

    /**
     * 角色权限
     */
    @Basic
    @Column(name = "role", nullable = false)
    private String role;

    /**
     * 角色名称
     */
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * 是否是管理员
     */
    @Basic
    @Column(name = "is_admin")
    private boolean isAdmin;

    /**
     * 角色权限下的可访问的资源（根据映射表r_role_resource来映射tb_sys_resource）
     */
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "r_role_resource", joinColumns = {@JoinColumn(name = "role_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "resource_id", nullable = false, updatable = false)})
    @OrderBy("id ASC")
    private Set<SysResourceEntity> resources = new HashSet<>(0);

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean getIsAdmin() {
        return isAdmin;
    }

    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    public Set<SysResourceEntity> getResources() {
        return resources;
    }

    public void setResources(Set<SysResourceEntity> resources) {
        this.resources = resources;
    }
}
