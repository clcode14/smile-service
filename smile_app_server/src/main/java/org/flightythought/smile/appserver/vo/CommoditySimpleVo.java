package org.flightythought.smile.appserver.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.flightythought.smile.appserver.bean.CommoditySimple;

/**
 * 用来解决ios description的问题，防止影响多个接口
 */
@Data
public class CommoditySimpleVo extends CommoditySimple {
    /**
     * 商品详情
     */
    @ApiModelProperty(name = "theDescription")
    @JsonProperty("theDescription")
    private String description;

}
