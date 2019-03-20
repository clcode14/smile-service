package org.flightythought.smile.admin.database.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/15 14:03
 */
@Entity
@Table(name = "tb_sys_resource", schema = "smile", catalog = "")
public class SysResourceEntity extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private int id;

    /**
     * 资源名称
     */
    @Basic
    @Column(name = "name", nullable = false)
    private String name;

    /**
     * URL
     */
    @Basic
    @Column(name = "url")
    private String url;

    /**
     * 描述
     */
    @Basic
    @Column(name = "description")
    private String description;

    /**
     * 图标
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

    /**
     * 排序
     */
    @Basic
    @Column(name = "seq")
    private int seq;

    /**
     * 状态
     */
    @Basic
    @Column(name = "state")
    private int state;

    /**
     * 资源类型
     */
    @Basic
    @Column(name = "resource_type")
    private int resourceType;

    /**
     * 父级
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parent_id")
    private SysResourceEntity resource;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public int getSeq() {
        return seq;
    }

    public void setSeq(int seq) {
        this.seq = seq;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getResourceType() {
        return resourceType;
    }

    public void setResourceType(int resourceType) {
        this.resourceType = resourceType;
    }

    public SysResourceEntity getResource() {
        return resource;
    }

    public void setResource(SysResourceEntity resource) {
        this.resource = resource;
    }

}
