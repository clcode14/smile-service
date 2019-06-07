package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "旅程日记查询DTO", description = "旅程日记查询DTO")
public class JourneyNoteQueryDTO extends PageFilterDTO {

    @ApiModelProperty(value = "旅程ID")
    private String journeyId;

}
