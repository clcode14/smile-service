package org.flightythought.smile.admin.bean;

import lombok.Data;
import org.flightythought.smile.admin.database.entity.CharityFaultTypeContentEntity;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/5/14 16:37
 * @Description: TODO
 */
@Data
public class CharityFaultTypeContent extends CharityFaultTypeContentEntity {
    /**
     * 类型名称
     */
    private String typeName;
}
