package org.flightythought.smile.appserver.service;

import org.flightythought.smile.appserver.bean.*;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.appserver.database.entity.HealthResultEntity;
import org.flightythought.smile.appserver.database.entity.JourneyEntity;
import org.flightythought.smile.appserver.database.entity.JourneyNoteEntity;
import org.flightythought.smile.appserver.database.entity.JourneyNoteMessageEntity;
import org.flightythought.smile.appserver.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface JourneyHealthService {
    List<HealthNormTypeEntity> getHealthNormTypes();

    List<FileInfo> uploadReport(List<MultipartFile> files, Integer type) throws FlightyThoughtException;

    JourneyEntity startHealthJourney(HealthJourneyStartDTO healthJourneyStartDTO);

    HealthJourney updateHealthJourney(HealthJourneyStartDTO healthJourneyStartDTO) throws FlightyThoughtException;

    Page<HealthJourneySimple> getHealthJourney(Long userId, PageFilterDTO pageFilterDTO, Boolean finished);

    HealthJourney getHealthJourney(Integer journeyId, Integer recoverId);

    JourneyNoteEntity addJourneyNoteEntity(JourneyNoteDTO journeyNoteDTO) throws FlightyThoughtException;

    Page<JourneyNote> getJourneyHealthNote(JourneyNoteQueryDTO journeyNoteQueryDTO) throws FlightyThoughtException;

    HealthJourney endHealthJourney(HealthJourneyEndDTO healthJourneyEndDTO) throws FlightyThoughtException;

    Page<HealthResultEntity> getHealthResultList(PageFilterDTO pageFilterDTO);

    List<DiseaseClassDetailSimple> getDiseaseByJourneyId(Integer journeyId);

    void deleteJourneyById(Integer journeyId);

    JourneyNote getJourneyNote(Integer noteId);

    JourneyNoteMessageEntity addJourneyNoteMessage(JourneyNoteMessageDTO journeyNoteMessageDTO);

    Page<JourneyNoteMessageSimple> getJourneyNoteMessageInfo(JourneyNoteMessageQueryDTO journeyNoteMessageQueryDTO);

    void likeJourneyNote(Integer journeyNoteId);

    void deleteJourneyNoteMessage(Integer messageId);

    void deleteJourneyNoteById(Integer noteId);
}
