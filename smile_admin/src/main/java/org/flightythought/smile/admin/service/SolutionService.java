package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.database.entity.SolutionEntity;
import org.flightythought.smile.admin.dto.SolutionDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName SolutionService
 * @CreateTime 2019/3/27 18:50
 * @Description: 解决方案服务层
 */
public interface SolutionService {
    /**
     * 获取相关课程Option
     */
    List<SelectItemOption> getCourseItems();

    /**
     * 添加解决方案
     *
     * @param solutionDTO 解决方案数据传输对象
     * @param images      配图
     * @param session     SESSION
     */
    SolutionEntity addSolution(SolutionDTO solutionDTO, List<MultipartFile> images, HttpSession session) throws FlightyThoughtException;
}
