package org.flightythought.smile.admin.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(value = "机构 DTO", description = "机构 DTO")
public class OfficeDTO {
    @ApiModelProperty(value = "机构ID")
    private Integer officeId;

    @ApiModelProperty(value = "机构name")
    private String name;

    @ApiModelProperty(value = "机构编码")
    private String number;

    @ApiModelProperty(value = "联系人")
    private String contactName;

    @ApiModelProperty(value = "联系人电话")
    private String phone;

    @ApiModelProperty(value = "配图")
    private List<ImageDTO> officeImages;

    private String address;

    private String description;
}
