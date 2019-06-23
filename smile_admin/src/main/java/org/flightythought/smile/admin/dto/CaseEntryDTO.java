package org.flightythought.smile.admin.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName CaseEntryDTO
 * @CreateTime 2019/6/21 15:50
 * @Description: TODO
 */
@Data
@ApiModel(value = "案例录入DTO", description = "案例录入DTO")
public class CaseEntryDTO {
    @ApiModelProperty(value = "用户昵称")
    private String nickname;

    @ApiModelProperty(value = "手机号码")
    private String phone;

    @ApiModelProperty(value = "案例标题")
    private String title;

    @ApiModelProperty(value = "案例介绍")
    private String content;

    /**
     * 疾病
     */
    private List<DiseaseResultDTO> disease;

    /**
     * 体检指标
     */
    private List<HealthNormDTO> norm;

    /**
     * 养生ID
     */
    @ApiModelProperty(value = "养生ID")
    private List<Integer> healthIds;

    /**
     * 解决方案ID
     */
    @ApiModelProperty(value = "解决方案ID")
    private List<Integer> solutionIds;

    /**
     * 开始时间
     */
    @ApiModelProperty(value = "开始时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime startTime;

    /**
     * 结束时间
     */
    @ApiModelProperty(value = "结束时间")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime endTime;

    /**
     * 日记
     */
    private List<NoteDTO> notes;
}
