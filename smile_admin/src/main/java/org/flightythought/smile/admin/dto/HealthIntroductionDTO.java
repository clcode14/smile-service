package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 爱心养身介绍
 * 
 * @author cl47872
 * @version $Id: HealthIntroductionDTO.java, v 0.1 Jun 9, 2019 9:16:52 AM cl47872 Exp $
 */
@ApiModel(value = "爱心养身介绍DTO", description = "爱心养身介绍DTO")
@Data
public class HealthIntroductionDTO {

    @ApiModelProperty(value = "介绍内容")
    private String content;
}
