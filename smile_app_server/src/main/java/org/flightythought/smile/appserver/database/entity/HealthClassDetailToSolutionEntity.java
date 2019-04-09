package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthClassDetailToSolutionEntity
 * @CreateTime 2019/4/9 17:55
 * @Description: TODO
 */
@Entity
@Table(name = "tb_health_class_detail_to_solution")
@Data
@EqualsAndHashCode(callSuper = false)
public class HealthClassDetailToSolutionEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 养生小类ID
     */
    @Column(name = "health_detail_id")
    private Integer healthDetailId;

    /**
     * 解决方案ID
     */
    @Column(name = "solution_id")
    private Integer solutionId;
}
