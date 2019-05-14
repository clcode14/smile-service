package org.flightythought.smile.admin.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyToCourseEntity
 * @CreateTime 2019/4/14 0:57
 * @Description: TODO
 */
@Entity
@Table(name = "tb_journey_to_course")
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyToCourseEntity {
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
     * 课程ID
     */
    @Column(name = "course_id")
    private Integer courseId;
}
