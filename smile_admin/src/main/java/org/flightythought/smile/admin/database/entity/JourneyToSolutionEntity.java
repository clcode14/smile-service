package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyToSolutionEntity
 * @CreateTime 2019/4/14 0:48
 * @Description: TODO
 */
@Entity
@Table(name = "tb_journey_to_solution")
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyToSolutionEntity {
    /**
     * 自增主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 养生旅程ID
     */
    @Column(name = "journey_id")
    private Integer journeyId;

    /**
     * 解决方案ID
     */
    @Column(name = "solution_id")
    private Integer solutionId;
}
