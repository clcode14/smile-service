package org.flightythought.smile.appserver.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.flightythought.smile.appserver.bean.HealthClassDetail;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

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
     * 养生旅程概述
     */
    @Column(name = "summarize")
    private String summarize;

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

    /**
     * 访问量
     */
    @Column(name = "read_num")
    private Integer readNum;

    /**
     * 旅程体检指标
     */
    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "journey_id", insertable = false, updatable = false)
    private List<JourneyNormEntity> journeyNorms;

    /**
     * 旅程关联的疾病小类
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_journey_disease", joinColumns = {@JoinColumn(name = "journey_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "disease_detail_id", nullable = false, updatable = false)})
    private List<DiseaseClassDetailEntity> diseaseClassDetails;

    /**
     * 旅程关联的养生方式
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_journey_health", joinColumns = {@JoinColumn(name = "journey_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "health_detail_id", nullable = false, updatable = false)})
    private List<HealthClassDetailEntity> healthClassDetails;

    /**
     * 体检报告
     */
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_journey_to_report", joinColumns = {@JoinColumn(name = "journey_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "report_id", nullable = false, updatable = false)})
    private List<MedicalReportEntity> medicalReports;


}
