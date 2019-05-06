package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseReasonTypeEntity
 * @CreateTime 2019/5/4 20:30
 * @Description: TODO
 */
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_disease_reason_type")
@EqualsAndHashCode(callSuper = false)
@Data
public class DiseaseReasonTypeEntity extends BaseEntity {

    /**
     * 类型ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "type_id")
    private Integer typeId;

    /**
     * 类型名称
     */
    @Column(name = "type_name")
    private String typeName;
}
