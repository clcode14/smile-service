package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthDetailQueryDTO
 * @CreateTime 2019/4/9 18:35
 * @Description: TODO
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel(value = "养生小类查询DTO", description = "养生小类查询DTO")
public class HealthDetailQueryDTO extends PageFilterDTO {
    /**
     * 养生大类ID
     */
    private Integer healthId;
}
