package org.flightythought.smile.appserver.bean;

import lombok.Data;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName AppUpdateData
 * @CreateTime 2019/5/26 22:50
 * @Description: TODO
 */
@Data
public class AppUpdateData {
    private Integer versionId;

    private String version;

    private Boolean forceUpdate;

    private String url;

    private String description;
}
