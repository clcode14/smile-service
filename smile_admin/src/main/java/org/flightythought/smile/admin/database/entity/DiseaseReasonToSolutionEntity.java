package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DiseaseReasonToSolutionEntity
 * @CreateTime 2019/4/2 0:59
 * @Description: TODO
 */
@Entity
@Table(name = "tb_disease_reason_to_solution")
@Data
@EqualsAndHashCode(callSuper = false)
public class DiseaseReasonToSolutionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "disease_reason_id")
    private Integer diseaseReasonId;

    @Column(name = "solution_id")
    private Integer solutionId;
}
