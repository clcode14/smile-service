package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 热门搜索
 * 
 * @author cl47872
 * @version $Id: HotSearchDTO.java, v 0.1 Jun 29, 2019 8:54:39 PM cl47872 Exp $
 */
@ApiModel(value = "热门搜索DTO", description = "热门搜索DTO")
@Data
public class HotSearchDTO {

    @ApiModelProperty(value = "ID")
    private Integer id;

    @ApiModelProperty(value = "编码")
    private String  code;

    @ApiModelProperty(value = "描述")
    private String  description;
}
