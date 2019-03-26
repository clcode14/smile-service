package org.flightythought.smile.admin.database.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Copyright 2017 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseClassEntity.java
 * @CreateTime 2019/3/26 22:45
 * @Description: 疾病大类实体类
 */
@Entity
@Table(name = "tb_disease_class", schema = "smile", catalog = "")
public class DiseaseClassEntity extends BaseEntity {
    /**
     * 疾病大类ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "disease_id")
    private int diseaseId;

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

    public int getDiseaseId() {
        return diseaseId;
    }

    public void setDiseaseId(int diseaseId) {
        this.diseaseId = diseaseId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public List<DiseaseClassDetailEntity> getDiseaseClassDetails() {
        return diseaseClassDetails;
    }

    public void setDiseaseClassDetails(List<DiseaseClassDetailEntity> diseaseClassDetails) {
        this.diseaseClassDetails = diseaseClassDetails;
    }
}
