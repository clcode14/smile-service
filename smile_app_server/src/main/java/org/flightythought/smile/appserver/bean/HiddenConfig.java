package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName null.java
 * @CreateTime 2019/6/3 19:36
 * @Description: TODO
 */
@Data
public class HiddenConfig {
    /**
     * 善行是否公开
     */
    private boolean charityHidden;

    /**
     * 忏悔是否公开
     */
    private boolean faultHidden;

    /**
     * 动态是否公开
     */
    private boolean dynamicHidden;
}
