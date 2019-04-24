package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "首页轮播图 DTO", description = "首页轮播图 DTO")
public class HomeBannerDTO {

    @ApiModelProperty(value = "首页轮播图ID")
    private Integer id;

    @ApiModelProperty(value = "标题")
    private String title;

    @ApiModelProperty(value = "排序")
    private Integer sort;

    @ApiModelProperty(value = "链接")
    private String link;

    @ApiModelProperty(value = "轮播图ID")
    private Integer imageId;

}
