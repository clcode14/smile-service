package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.CharityAndFault;
import org.flightythought.smile.appserver.bean.CharityFaultMessageSimple;
import org.flightythought.smile.appserver.bean.CharityFaultStatistics;
import org.flightythought.smile.appserver.bean.UserCharityFaultRecord;
import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;
import org.flightythought.smile.appserver.dto.*;
import org.springframework.data.domain.Page;

public interface CharityFaultService {

    CharityAndFault getCharityAndFault(Integer cfTypeId);

    UserCharityFaultRecordEntity addCharityFaultRecord(CharityFaultRecordDTO charityFaultRecordDTO);

    Page<UserCharityFaultRecord> getCharityFaults(CharityFaultQueryDTO charityFaultQueryDTO);

    Page<UserCharityFaultRecord> getMyCharityFaults(PageFilterDTO pageFilterDTO);

    UserCharityFaultRecord getUserCharityFaultRecord(UserCharityFaultRecordEntity userCharityFaultRecordEntity);

    UserCharityFaultRecord getUserCharityFaultRecord(Integer id);

    /**
     * 获取养生分数排名
     *
     * @param pageFilterDTO 分页查询DTO
     */
    Page<CharityFaultStatistics> getCharityFaultInfoOrRanking(PageFilterDTO pageFilterDTO, Long userId);

    CharityFaultMessageSimple addCharityFaultMessage(CharityFaultMessageDTO charityFaultMessageDTO);

    void likeCharityFaultMessage(Integer messageId);

    Page<CharityFaultMessageSimple> getCharityFaultMessage(CharityFaultMessageQueryDTO charityFaultMessageQueryDTO);

    Page<CharityFaultMessageSimple> getCharityFaultMessageInfo(CharityFaultMessageQueryDTO charityFaultMessageQueryDTO);
}
