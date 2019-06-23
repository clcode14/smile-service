package org.flightythought.smile.admin.bean;

import java.util.List;

import lombok.Data;

@Data
public class ResourceInfo {
    /**
     * 菜单ID
     */
    private String             id;

    /**
     * 菜单名称
     */
    private String             lable;

    /**
     * 子菜单
     */
    private List<ResourceInfo> children;

}
