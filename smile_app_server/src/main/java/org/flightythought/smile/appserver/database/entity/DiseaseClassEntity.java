package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseClassEntity.java
 * @CreateTime 2019/3/26 22:45
 * @Description: 疾病大类实体类
 */
@Entity
@Table(name = "tb_disease_class")
@Data
@EqualsAndHashCode(callSuper = false)
public class DiseaseClassEntity extends BaseEntity {
    /**
     * 疾病大类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "disease_id")
    private Integer diseaseId;

    /**
     * 编码
     */
    @Basic
    @Column(name = "number")
    private String number;

    /**
     * 疾病类目名称
     */
    @Basic
    @Column(name = "disease_name")
    private String diseaseName;

    /**
     * 疾病小类
     */
    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.LAZY)
    @JoinColumn(name = "disease_id")
    private List<DiseaseClassDetailEntity> diseaseClassDetails;
}
