package org.flightythought.smile.admin.database.entity;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/25 11:48
 */
@Entity
@Table(name = "tb_disease_class_detail", schema = "smile", catalog = "")
public class DiseaseClassDetailEntity extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "disease_detail_id")
    private int diseaseDetailId;

    @Basic
    @Column(name = "disease_id")
    private int diseaseId;

    @Basic
    @Column(name = "number")
    private String number;

    @Basic
    @Column(name = "disease_detail_name")
    private String diseaseDetailName;

    public int getDiseaseDetailId() {
        return diseaseDetailId;
    }

    public void setDiseaseDetailId(int diseaseDetailId) {
        this.diseaseDetailId = diseaseDetailId;
    }

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

    public String getDiseaseDetailName() {
        return diseaseDetailName;
    }

    public void setDiseaseDetailName(String diseaseDetailName) {
        this.diseaseDetailName = diseaseDetailName;
    }
}
