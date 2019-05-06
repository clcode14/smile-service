package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.DiseaseClass;
import org.flightythought.smile.admin.database.entity.DiseaseClassEntity;
import org.flightythought.smile.admin.dto.DiseaseClassDTO;
import org.springframework.data.domain.Page;

import javax.servlet.http.HttpSession;

/**
 * Copyright 2018 欧瑞思丹 All rights reserved.
 *
 * @author LiLei
 * @date 2019/1/21 13:23
 */
public interface DiseaseConfigService {

    /**
     * 获取疾病大类
     *
     * @param pageNumber 页数
     * @param pageSize   每页显示数
     * @return Page<DiseaseClassEntity>
     */
    Page<DiseaseClass> getDiseaseClass(int pageNumber, int pageSize);

    /**
     * 新增疾病大类
     *
     * @param diseaseClassEntity 疾病大类对象
     * @return DiseaseClassEntity
     */
    DiseaseClassEntity addDiseaseClass(DiseaseClassDTO diseaseClassDTO, HttpSession session);

    /**
     * 修改疾病大类
     *
     * @param diseaseClassEntity 疾病大类对象
     * @return DiseaseClassEntity
     */
    DiseaseClassEntity updateDiseaseClass(DiseaseClassEntity diseaseClassEntity);

    /**
     * 删除疾病大类
     *
     * @param id 主键ID
     */
    void deleteDiseaseClass(Integer id);
}
