package org.flightythought.smile.appserver.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeSimple
 * @CreateTime 2019/5/21 22:52
 * @Description: 相关机构简单对象
 */
@Data
public class OfficeSimple {
    /**
     * 机构ID
     */
    private Long officeId;

    /**
     * 公司名称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 描述
     */
    private String description;

    /**
     * 编码
     */
    private String number;

    /**
     * 联系人
     */
    private String contactName;

    /**
     * 电话
     */
    private String phone;

    /**
     * 图片
     */
    List<ImageInfo> images;
}
