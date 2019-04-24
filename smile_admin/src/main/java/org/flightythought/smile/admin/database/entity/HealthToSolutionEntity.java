package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.flightythought.smile.admin.database.entity.BaseEntity;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.SolutionEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthToSolutionEntity
 * @CreateTime 2019/4/9 17:45
 * @Description: TODO
 */
@Entity
@Table(name = "tb_health_to_solution")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthToSolutionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 所属养生ID
     */
    @Column(name = "health_id")
    private Integer healthId;

    @Column(name = "solution_id")
    private Integer solutionId;
}
