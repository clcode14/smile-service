package org.flightythought.smile.admin.service;

import java.util.List;
import java.util.Map;

import org.flightythought.smile.admin.bean.JourneyNoteInfo;
import org.flightythought.smile.admin.dto.JourneyNoteQueryDTO;
import org.springframework.data.domain.Page;

public interface JourneyNoteService {

    List<JourneyNoteInfo> findAll(Map<String,String> params);

    Page<JourneyNoteInfo> queryNotePage(JourneyNoteQueryDTO journeyNoteQueryDTO);
}
