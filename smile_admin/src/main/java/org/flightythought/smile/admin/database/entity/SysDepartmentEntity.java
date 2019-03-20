package org.flightythought.smile.admin.database.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/15 14:33
 */
@Entity
@Table(name = "tb_sys_department", schema = "smile", catalog = "")
public class SysDepartmentEntity extends BaseEntity {

    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private int id;

    /**
     * 部门名称
     */
    @Basic
    @Column(name = "department_name")
    private String departmentName;

    /**
     * 地址
     */
    @Basic
    @Column(name = "address")
    private String address;

    /**
     * 地址
     */
    @Basic
    @Column(name = "icon")
    private String icon;

    /**
     * 父级ID
     */
    @Basic
    @Column(name = "parent_id", insertable = false, updatable = false)
    private Integer parentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private SysDepartmentEntity superiorDepartment;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "superiorDepartment")
    private Set<SysDepartmentEntity> subDepartments = new HashSet<>();


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }


    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public SysDepartmentEntity getSuperiorDepartment() {
        return superiorDepartment;
    }

    public void setSuperiorDepartment(SysDepartmentEntity superiorDepartment) {
        this.superiorDepartment = superiorDepartment;
    }

    public Set<SysDepartmentEntity> getSubDepartments() {
        return subDepartments;
    }

    public void setSubDepartments(Set<SysDepartmentEntity> subDepartments) {
        this.subDepartments = subDepartments;
    }
}
