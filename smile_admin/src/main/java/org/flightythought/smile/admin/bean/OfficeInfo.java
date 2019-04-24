package org.flightythought.smile.admin.bean;

import lombok.Data;

import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName OfficeInfo
 * @CreateTime 2019/4/24 23:59
 * @Description: TODO
 */
@Data
public class OfficeInfo {

    /**
     * 主键ID
     */
    private Long officeId;

    /**
     * 机构名称
     */
    private String name;

    /**
     * 机构编号
     */
    private String number;

    /**
     * 机构联系人
     */
    private String contactName;

    /**
     * 手机
     */
    private String phone;

    /**
     * 机构图片
     */
    private List<ImageInfo> officeImages;

    /**
     * 地址
     */
    private String address;

    /**
     * 描述
     */
    private String description;
}
