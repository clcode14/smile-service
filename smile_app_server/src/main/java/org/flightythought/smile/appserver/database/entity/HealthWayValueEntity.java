package org.flightythought.smile.appserver.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthWayValueEntity
 * @CreateTime 2019/5/26 13:58
 * @Description: TODO
 */
@Entity
@Data
@Table(name = "tb_health_way_value")
public class HealthWayValueEntity extends BaseEntity {
    /**
     * 自增主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 持续时间
     */
    @Column(name = "duration")
    private Integer duration;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 养生方式ID
     */
    @Column(name = "health_way_id")
    private Integer healthWayId;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;
}
