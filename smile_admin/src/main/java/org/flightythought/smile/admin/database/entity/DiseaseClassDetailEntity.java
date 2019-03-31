package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseClassDetailEntity.java
 * @CreateTime 2019/3/27 17:14
 * @Description: 疾病小类实体类
 */
@Entity
@Table(name = "tb_disease_class_detail")
@Data
@EqualsAndHashCode(callSuper = false)
public class DiseaseClassDetailEntity extends BaseEntity {
    /**
     * 疾病小类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "disease_detail_id")
    private int diseaseDetailId;

    /**
     * 疾病大类ID
     */
    @Basic
    @Column(name = "disease_id")
    private int diseaseId;

    /**
     * 编码
     */
    @Basic
    @Column(name = "number")
    private String number;

    /**
     * 疾病小类类型 0：普通疾病 1：常见疾病
     */
    @Basic
    @Column(name = "type")
    private String type;

    /**
     * 疾病小类名称
     */
    @Basic
    @Column(name = "disease_detail_name")
    private String diseaseDetailName;

}
