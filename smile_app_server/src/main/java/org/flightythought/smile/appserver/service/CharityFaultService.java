package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.CharityAndFault;
import org.flightythought.smile.appserver.database.entity.UserCharityFaultRecordEntity;
import org.flightythought.smile.appserver.dto.CharityFaultRecordDTO;

public interface CharityFaultService {

    CharityAndFault getCharityAndFault(Integer cfTypeId);

    UserCharityFaultRecordEntity addCharityFaultRecord(CharityFaultRecordDTO charityFaultRecordDTO);
}
