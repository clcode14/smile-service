package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyNormEntity
 * @CreateTime 2019/4/10 2:12
 * @Description: TODO
 */
@Entity
@Table(name = "tb_journey_norm")
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyNormEntity extends BaseEntity {

    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 体检指标类型ID
     */
    @Column(name = "norm_type_id")
    private Integer normTypeId;

    /**
     * 养生旅程ID
     */
    @Column(name = "journey_id")
    private Integer journeyId;

    /**
     * 开始数值1
     */
    @Column(name = "start_value1")
    private String startValue1;

    /**
     * 开始数值2
     */
    @Column(name = "start_value2")
    private String startValue2;

    /**
     * 结束数值1
     */
    @Column(name = "end_value1")
    private String endValue1;

    /**
     * 结束数值2
     */
    @Column(name = "end_value2")
    private String endValue2;
}
