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
 * @ClassName JourneyHealthEntity
 * @CreateTime 2019/4/10 2:12
 * @Description: TODO
 */
@Entity
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
@Table(name = "tb_journey_health")
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyHealthEntity {
    /**
     * 自增主键
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

    @Column(name = "health_id")
    private Integer healthId;

    @Column(name = "health_result_id")
    private Integer healthResultId;
}
