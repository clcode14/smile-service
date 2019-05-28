package org.flightythought.smile.appserver.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@ApiModel(value = "行善过失 DTO", description = "行善过失 DTO，新增行善和过失，需要传递哪个值就传递哪个值即可，关于行善类型如下表定义<table><thead><tr><th>cf_type_id(行善过失ID)</th><th>type name(类型名称)</th></tr></thead><tbody><tr><td>1</td><td>祝福</td></tr><tr><td>2</td><td>道歉</td></tr><tr><td>3</td><td>微笑</td></tr><tr><td>4</td><td>鼓励他人</td></tr><tr><td>5</td><td>捐款</td></tr><tr><td>6</td><td>捐物</td></tr><tr><td>7</td><td>做义工</td></tr></tbody></table>")
public class CharityFaultRecordDTO {
    @ApiModelProperty(value = "行善、过失类型，0：行善；1：过失")
    private Integer type;

    @ApiModelProperty(value = "内容记录")
    private String content;

    @ApiModelProperty(value = "行善日期 格式：yyyy-MM-dd HH:mm:ss", example = "2019-01-01 00:00:00")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime charityTimeStart;

    @ApiModelProperty(value = "行善日期 格式：yyyy-MM-dd HH:mm:ss", example = "2019-01-01 00:00:00")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime charityTimeEnd;

    @ApiModelProperty(value = "行善过失类型ID，详情参照上表或查看：获取行善过失类型以及内容接口 【/auth/charityFault/typeAndContents】")
    private Integer cfTypeId;

    @ApiModelProperty(value = "类型内容ID，参考：获取行善过失类型以及内容接口 【/auth/charityFault/typeAndContents】")
    private Integer typeContentId;

    @ApiModelProperty(value = "捐款金额")
    private BigDecimal donateAmount;

    @ApiModelProperty(value = "物资详情")
    private String materialDetails;

    @ApiModelProperty(value = "经度")
    private Double longitude;

    @ApiModelProperty(value = "维度")
    private Double latitude;

    @ApiModelProperty(value = "地址")
    private String address;

    @ApiModelProperty(value = "是否隐藏")
    private Boolean hidden;

    @ApiModelProperty(value = "上传的图片ID")
    private List<FileImageDTO> images;
}
