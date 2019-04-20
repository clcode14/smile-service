package org.flightythought.smile.appserver.database.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Objects;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName JourneyToReportEntity
 * @CreateTime 2019/4/10 2:12
 * @Description: TODO
 */
@Entity
@Table(name = "tb_journey_to_report", schema = "smile_dev", catalog = "")
@Data
@EqualsAndHashCode(callSuper = false)
public class JourneyToReportEntity {
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

    /**
     * 体检报告ID
     */
    @Column(name = "report_id")
    private Integer reportId;

    /**
     * 是否是开启旅程的体检报告
     */
    @Column(name = "start_flag")
    private Boolean startFlag;
}
