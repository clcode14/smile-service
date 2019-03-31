package org.flightythought.smile.appserver.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "tb_recover_case")
@Entity
@Data
@EqualsAndHashCode(callSuper = false)
public class RecoverCase extends BaseEntity {


    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 解决方案ID
     */
    @Column(name = "solution_id")
    private Integer solutionId;

    /**
     * 疾病小类ID
     */
    @Column(name = "disease_detail_id")
    private Integer diseaseDetailId;

    /**
     * 标题
     */
    @Column(name = "title")
    private String title;

    /**
     * 案例开始时间
     */
    @Column(name = "case_start_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime caseStartTime;

    /**
     * 案例结束时间
     */
    @Column(name = "case_end_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime caseEndTime;

    @Column(name = "user_id")
    private Long userId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private UserEntity user;

    @Column(name = "content", columnDefinition = "text")
    private String content;

    @Column(name = "images")
    private Integer imageId;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "images", insertable = false, updatable = false)
    private Images images;

    @Column(name = "read_num")
    private Long readNum;
}
