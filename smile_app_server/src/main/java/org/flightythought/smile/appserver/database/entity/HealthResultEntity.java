package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthResultEntity
 * @CreateTime 2019/4/14 0:46
 * @Description: TODO
 */
@Entity
@Table(name = "tb_health_result")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthResultEntity extends BaseEntity {

    /**
     * 自增主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 养生成果名称
     */
    @Column(name = "name")
    private String name;

    /**
     * 养生成果编码
     */
    @Column(name = "number")
    private String number;
}
