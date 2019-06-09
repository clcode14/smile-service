package org.flightythought.smile.admin.bean;

import org.flightythought.smile.admin.database.entity.BaseEntity;

import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/22 14:56
 * @Description: TODO
 */
@Data
public class EncourageInfo extends BaseEntity {
    /**
     * ID
     */
    private Integer id;

    /**
     * 编码
     */
    private String code;
    
    /**
     * 描述
     */
    private String description;

}
