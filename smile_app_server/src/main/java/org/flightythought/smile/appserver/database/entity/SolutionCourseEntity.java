package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionCourseEntity
 * @CreateTime 2019/4/1 21:42
 * @Description: TODO
 */
@Table(name = "tb_solution_course")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class SolutionCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    @Column(name = "solution_id")
    private Integer solutionId;

    @Column(name = "course_id")
    private Integer courseId;
}
