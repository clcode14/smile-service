package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthWayMusicEntity
 * @CreateTime 2019/5/26 13:31
 * @Description: TODO
 */
@Data
@Entity
@Table(name = "tb_health_way_music")
public class HealthWayMusicEntity extends BaseEntity {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 音乐URL链接
     */
    @Column(name = "url")
    private String url;

    /**
     * 名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 文件ID
     */
    @Column(name = "file_id")
    private Integer fileId;

    /**
     * 养生方式ID
     */
    @Column(name = "health_way_id")
    private Integer healthWayId;
}
