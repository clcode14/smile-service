package org.flightythought.smile.appserver.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/1 11:20
 * @Description: TODO
 */
@Data
@ApiModel(value = "用户ID查询DTO", description = "利用用户ID查询DTO")
public class UserIdQueryDTO extends PageFilterDTO {

    @ApiModelProperty(name = "用户ID")
    private Long userId;

    @ApiModelProperty(name = "是否包括自己的信息，如果userId为null，" +
            "该值为false会过滤掉自己创建的数据，注意：如果userId为自身ID，includingMyself为false会返回空集")
    private Boolean includingMyself;
}
