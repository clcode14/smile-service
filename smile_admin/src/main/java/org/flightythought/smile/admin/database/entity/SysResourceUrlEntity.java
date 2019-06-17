package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SysResourceUrlEntity
 * @CreateTime 2019/6/11 0:57
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "tb_sys_resource_url")
public class SysResourceUrlEntity extends BaseEntity {
    /**
     * 自增主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 资源ID
     */
    @Column(name = "resource_id")
    private Integer resourceId;

    /**
     * Class URL
     */
    @Column(name = "class_url")
    private String classUrl;

    /**
     * Method URL
     */
    @Column(name = "method_url")
    private String methodUrl;

    /**
     * 请求方式
     */
    @Column(name = "request_method")
    private String requestMethod;

    /**
     * URL
     */
    @Column(name = "url")
    private String url;

    /**
     * Class DESC
     */
    @Column(name = "class_desc")
    private String classDesc;

    /**
     * Method DESC
     */
    @Column(name = "method_desc")
    private String methodDesc;

    /**
     * 备注信息
     */
    @Column(name = "memo")
    private String memo;
}
