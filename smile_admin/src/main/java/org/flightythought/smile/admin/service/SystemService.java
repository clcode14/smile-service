package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.database.entity.AppVersionEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SystemService
 * @CreateTime 2019/5/27 21:14
 * @Description: TODO
 */
public interface SystemService {
    AppVersionEntity uploadAppFile(MultipartFile file, Integer versionId, String version,
                                   Boolean forceUpdate, String description);
}
