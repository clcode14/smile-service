package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.JourneyNormInfo;
import org.flightythought.smile.admin.bean.JourneyNoteInfo;
import org.flightythought.smile.admin.bean.NormTypeInfo;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.JourneyNormEntity;
import org.flightythought.smile.admin.database.entity.JourneyNoteEntity;
import org.flightythought.smile.admin.database.repository.JourneyNormRepository;
import org.flightythought.smile.admin.database.repository.JourneyNoteRepository;
import org.flightythought.smile.admin.service.JourneyNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class JourneyNoteServiceImpl implements JourneyNoteService {

    @Autowired
    private JourneyNoteRepository journeyNoteRepository;

    @Autowired
    private JourneyNormRepository journeyNormRepository;

    @Autowired
    private PlatformUtils platformUtils;

    @Override
    public List<JourneyNoteInfo> findAll(Map<String, String> params) {
        String journeyId = params.get("journeyId");
        JourneyNoteEntity journeyNoteEntity = new JourneyNoteEntity();
        if (StringUtils.isNotBlank(journeyId)) {
            journeyNoteEntity.setJourneyId(Integer.parseInt(journeyId));
        }
        return journeyNoteRepository.findAll(Example.of(journeyNoteEntity))
                .stream()
                .map(journeyNote -> {
                    JourneyNoteInfo journeyNoteInfo = new JourneyNoteInfo();

                    journeyNoteInfo.setContent(journeyNote.getContent());
                    journeyNoteInfo.setJourneyId(journeyNote.getId());
                    journeyNoteInfo.setId(journeyNote.getId());
                    String domainPort = platformUtils.getDomainPort();

                    ImagesEntity imagesEntity = journeyNote.getCoverImage();
                    String imageUrlByPath = platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort);
                    journeyNoteInfo.setCoverImageUrl(imageUrlByPath);
                    journeyNoteInfo.setNoteDate(journeyNote.getNoteDate());

                    return journeyNoteInfo;
                }).collect(Collectors.toList());
    }
}
