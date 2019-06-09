package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName AppVersionEntity
 * @CreateTime 2019/5/27 20:52
 * @Description: TODO
 */
@Entity
@Table(name = "tb_app_version")
@Data
@EqualsAndHashCode(callSuper = false)
public class AppVersionEntity extends BaseEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 版本序号
     */
    @Column(name = "version_id")
    private Integer versionId;

    /**
     * 版本号
     */
    @Column(name = "version")
    private String version;

    /**
     * 是否强制升级
     */
    @Column(name = "force_update")
    private Boolean forceUpdate;

    /**
     * 服务器路径
     */
    @Column(name = "path")
    private String path;

    /**
     * OSS KEY
     */
    @Column(name = "oss_key")
    private String key;

    /**
     * OSS URL
     */
    @Column(name = "oss_url")
    private String url;

    /**
     * 版本说明
     */
    @Column(name = "description", columnDefinition = "text")
    private String description;

    /**
     * 当前版本
     */
    @Column(name = "current")
    private Boolean current;
}
