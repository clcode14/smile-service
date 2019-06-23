package org.flightythought.smile.admin.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SelectItemOption
 * @CreateTime 2019/3/27 18:54
 * @Description: 下拉选Option对象
 */
@Data
@JsonInclude(value = JsonInclude.Include.NON_NULL)
public class SelectItemOption {
    private String key;
    private String value;
    private String type;

    public SelectItemOption() {
    }

    public SelectItemOption(String key, String value) {
        this.key = key;
        this.value = value;
    }
}
