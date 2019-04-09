package org.flightythought.smile.appserver.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;
import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class UserCharityFaultRecord {
    /**
     * 自增主键ID
     */
    private Integer id;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 行善 忏悔 类型
     */
    private Integer type;

    /**
     * 内容记录
     */
    private String content;

    /**
     * 行善日期
     */
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime charityTime;

    /**
     * 行善类型ID
     */
    private Integer cfTypeId;

    /**
     * 类型对应的内容ID
     */
    private Integer typeContentId;

    /**
     * 捐款金额
     */
    private BigDecimal donateAmount;

    /**
     * 物资详情
     */
    private String materialDetails;

    /**
     * 经度
     */
    private Double longitude;

    /**
     * 维度
     */
    private Double latitude;

    /**
     * 地址
     */
    private String address;

    /**
     * 图片
     */
    private List<ImageInfo> images;

    public UserCharityFaultRecord() {
    }

    public UserCharityFaultRecord(UserCharityFaultRecordEntity userCharityFaultRecordEntity, List<ImageInfo> images) {
        this.id = userCharityFaultRecordEntity.getId();
        this.userId = userCharityFaultRecordEntity.getUserId();
        this.type = userCharityFaultRecordEntity.getType();
        this.content = userCharityFaultRecordEntity.getContent();
        this.charityTime = userCharityFaultRecordEntity.getCharityTime();
        this.cfTypeId = userCharityFaultRecordEntity.getCfTypeId();
        this.typeContentId = userCharityFaultRecordEntity.getTypeContentId();
        this.donateAmount = userCharityFaultRecordEntity.getDonateAmount();
        this.materialDetails = userCharityFaultRecordEntity.getMaterialDetails();
        this.longitude = userCharityFaultRecordEntity.getLongitude();
        this.latitude = userCharityFaultRecordEntity.getLatitude();
        this.address = userCharityFaultRecordEntity.getAddress();
        this.images = images;
    }
}
