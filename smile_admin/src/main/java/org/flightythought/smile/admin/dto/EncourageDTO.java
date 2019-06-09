package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 鼓励话语
 * 
 * @author cl47872
 * @version $Id: EncourageDTO.java, v 0.1 Jun 8, 2019 9:13:19 PM cl47872 Exp $
 */
@ApiModel(value = "鼓励话语DTO", description = "鼓励话语DTO")
@Data
public class EncourageDTO {
    
    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "描述")
    private String description;
}
