package org.flightythought.smile.admin.service;

import org.flightythought.smile.admin.bean.JourneyNoteInfo;
import org.flightythought.smile.admin.database.entity.JourneyNoteEntity;

import java.util.List;
import java.util.Map;

public interface JourneyNoteService {

    List<JourneyNoteInfo> findAll(Map<String,String> params);
}
