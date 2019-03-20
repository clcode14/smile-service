package org.flightythought.smile.admin.database.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 * 疾病大类分类
 *
 * @author LiLei
 * @date 2019/1/21 13:14
 */
@Entity
@Table(name = "tb_disease_class", schema = "smile", catalog = "")
public class DiseaseClassEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "disease_id")
    private int diseaseId;

    @Basic
    @Column(name = "number")
    private String number;

    @Basic
    @Column(name = "disease_name")
    private String diseaseName;

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
