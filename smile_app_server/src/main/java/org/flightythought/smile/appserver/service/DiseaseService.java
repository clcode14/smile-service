package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.DiseaseClass;
import org.flightythought.smile.appserver.bean.DiseaseClassDetailSimple;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.database.entity.DiseaseClassDetailEntity;
import org.flightythought.smile.appserver.database.entity.DiseaseClassEntity;
import org.flightythought.smile.appserver.dto.UserFollowDiseaseDTO;

import java.util.List;

public interface DiseaseService {
    /**
     * 获取常见疾病列表
     *
     * @return 疾病小类集合
     */
    List<DiseaseClassDetailSimple> getCommonDiseases();

    /**
     * 获取疾病小类简单对象集合
     *
     * @param diseaseClassDetailEntities
     * @return
     */
    List<DiseaseClassDetailSimple> getDiseaseClassDetailSimple(List<DiseaseClassDetailEntity> diseaseClassDetailEntities);

    /**
     * 保存用户选中的常见疾病
     *
     * @param userFollowDiseases 用户关注的常见疾病列表
     */
    void saveUserFollowDiseases(List<UserFollowDiseaseDTO> userFollowDiseases);

    /**
     * 获取全部疾病
     */
    List<DiseaseClass> getAllThousandDisease();

    /**
     * 获取疾病小类内容展示
     */
    DiseaseClassDetailSimple getThousandDiseaseDetail(Integer diseaseDetailId) throws FlightyThoughtException;

    List<DiseaseClass> getDiseaseClass(List<DiseaseClassEntity> diseaseClassEntities);
}
