package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthResultSimple
 * @CreateTime 2019/4/28 21:26
 * @Description: TODO
 */
@Data
public class HealthResultSimple {

    /**
     * 主键ID
     */
    private Integer id;

    /**
     * 养生成果名称
     */
    private String name;

    /**
     * 编码
     */
    private String number;
}
