package org.flightythought.smile.appserver.service;

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
    List<DiseaseClassDetailEntity> getCommonDiseases();

    /**
     * 保存用户选中的常见疾病
     *
     * @param userFollowDiseases 用户关注的常见疾病列表
     */
    void saveUserFollowDiseases(List<UserFollowDiseaseDTO> userFollowDiseases);

    /**
     * 获取全部疾病
     */
    List<DiseaseClassEntity> getAllThousandDisease();

    /**
     * 获取疾病小类
     */
    DiseaseClassDetailEntity getThousandDiseaseDetail(Integer diseaseDetailId);
}
