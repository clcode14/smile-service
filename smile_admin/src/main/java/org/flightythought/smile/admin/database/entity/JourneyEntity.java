package org.flightythought.smile.admin.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyEntity
 * @CreateTime 2019/4/10 2:12
 * @Description: TODO
 */
@Entity
@Table(name = "tb_journey")
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyEntity extends BaseEntity {
    /**
     * 自增主键，养生旅程ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "journey_id")
    private Integer journeyId;

    /**
     * 养生旅程名称
     */
    @Column(name = "journey_name")
    private String journeyName;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 开始时间
     */
    @Column(name = "start_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @Column(name = "end_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 是否完成
     */
    @Column(name = "finished")
    private Boolean finished;
}
