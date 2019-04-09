package org.flightythought.smile.appserver.database.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "tb_user_charity_fault_record")
@Data
@EqualsAndHashCode(callSuper = false)
public class UserCharityFaultRecordEntity extends BaseEntity {
    /**
     * 主键ID
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY, generator = "identity")
    @GenericGenerator(name = "identity", strategy = "identity")
    @Column(name = "id")
    private Integer id;

    /**
     * 用户ID
     */
    @Column(name = "user_id")
    private Long userId;

    /**
     * 行善过失类型 0:善行1，过失
     */
    @Column(name = "type")
    private Integer type;

    /**
     * 内容记录
     */
    @Column(name = "content", columnDefinition = "text")
    private String content;

    /**
     * 行善日期
     */
    @Column(name = "charity_time")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime charityTime;

    /**
     * 行善类型ID
     */
    @Column(name = "cf_type_id")
    private Integer cfTypeId;

    /**
     * 行善类型对应的内容ID
     */
    @Column(name = "type_content_id")
    private Integer typeContentId;

    /**
     * 捐款金额
     */
    @Column(name = "donate_amount")
    private BigDecimal donateAmount;

    /**
     * 物资详情
     */
    @Column(name = "material_details")
    private String materialDetails;

    /**
     * 经度
     */
    @Column(name = "longitude")
    private Double longitude;

    /**
     * 纬度
     */
    @Column(name = "latitude")
    private Double latitude;

    /**
     * 地址
     */
    @Column(name = "address")
    private String address;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_charity_fault_record_image", joinColumns = {@JoinColumn(name = "charity_fault_record_id", nullable = false, updatable = false)},
            inverseJoinColumns = {@JoinColumn(name = "image_id", nullable = false, updatable = false)})
    private List<ImagesEntity> images;
}
