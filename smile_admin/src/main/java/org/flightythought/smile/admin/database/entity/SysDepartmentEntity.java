package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
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
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_sys_department")
@Data
@EqualsAndHashCode(callSuper = false)
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
    @Column(name = "parent_id")
    private Integer parentId;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id", insertable = false, updatable = false)
    private SysDepartmentEntity superiorDepartment;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "superiorDepartment")
    private Set<SysDepartmentEntity> subDepartments = new HashSet<>();

}
