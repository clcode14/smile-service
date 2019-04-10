package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel(value = "养生日记体检指标DTO", description = "养生日记体检指标DTO")
@Data
public class JourneyNoteNormDTO {
    /**
     * 指标类型ID
     */
    @ApiModelProperty(value = "指标类型ID")
    private Integer normTypeId;

    /**
     * 日记ID
     */
    @ApiModelProperty(value = "日记ID")
    private Integer noteId;

    /**
     * 数值1
     */
    @ApiModelProperty(value = "数值1")
    private String value1;

    /**
     * 数值2
     */
    @ApiModelProperty(value = "数值2")
    private String value2;
}
