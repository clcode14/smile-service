package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.FileInfo;
import org.flightythought.smile.admin.bean.HealthClassInfo;
import org.flightythought.smile.admin.bean.HealthWay;
import org.flightythought.smile.admin.database.entity.HealthEntity;
import org.flightythought.smile.admin.database.entity.HealthWayEntity;
import org.flightythought.smile.admin.dto.HealthClassDTO;
import org.flightythought.smile.admin.dto.HealthWayDTO;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName HealthService
 * @CreateTime 2019/4/9 18:12
 * @Description: TODO
 */
public interface HealthService {

    Page<HealthClassInfo> findHealthClass(Map<String, String> params);

    HealthClassInfo getHealthClass(Integer healthClassId);

    HealthEntity saveHealthClass(HealthClassDTO healthClassDTO, HttpSession session);

    HealthEntity modifyHealthClass(HealthClassDTO healthClassDTO, HttpSession session);

    void deleteHealthClass(Integer healthId, HttpSession session);

    HealthWayEntity addHealthWay(HealthWayDTO healthWayDTO);

    Page<HealthWay> getHealthWays(Map<String, String> params);

    HealthWayEntity modifyHealthWay(HealthWayDTO healthWayDTO);

    void deleteHealthWay(Integer healthWayId);

    FileInfo uploadFile(MultipartFile file, String musicName, HttpSession session);

    List<FileInfo> getAllMusicFile();

    Page<FileInfo> getMusics(Integer pageNumber, Integer pageSize, String musicName);

    void deleteFile(Integer fileId);
}
