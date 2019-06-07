package org.flightythought.smile.admin.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.JourneyNoteInfo;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.JourneyNoteEntity;
import org.flightythought.smile.admin.database.repository.JourneyNoteRepository;
import org.flightythought.smile.admin.dto.JourneyNoteQueryDTO;
import org.flightythought.smile.admin.service.JourneyNoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class JourneyNoteServiceImpl implements JourneyNoteService {

    @Autowired
    private JourneyNoteRepository journeyNoteRepository;

    @Autowired
    private PlatformUtils         platformUtils;

    @Override
    public List<JourneyNoteInfo> findAll(Map<String, String> params) {
        String journeyId = params.get("journeyId");
        JourneyNoteEntity journeyNoteEntity = new JourneyNoteEntity();
        if (StringUtils.isNotBlank(journeyId)) {
            journeyNoteEntity.setJourneyId(Integer.parseInt(journeyId));
        }
        return journeyNoteRepository.findAll(Example.of(journeyNoteEntity)).stream().map(journeyNote -> {
            JourneyNoteInfo journeyNoteInfo = new JourneyNoteInfo();

            journeyNoteInfo.setContent(journeyNote.getContent());
            journeyNoteInfo.setJourneyId(journeyNote.getId());
            journeyNoteInfo.setId(journeyNote.getId());
            String domainPort = platformUtils.getDomainPort();

            ImagesEntity imagesEntity = journeyNote.getCoverImage();
            if (imagesEntity != null) {
                String imageUrlByPath = platformUtils.getImageInfo(imagesEntity, domainPort).getUrl();
                journeyNoteInfo.setCoverImageUrl(imageUrlByPath);
            }
            journeyNoteInfo.setNoteDate(journeyNote.getNoteDate());

            return journeyNoteInfo;
        }).collect(Collectors.toList());
    }

    @Override
    public Page<JourneyNoteInfo> queryNotePage(JourneyNoteQueryDTO condition) {
        Pageable pageable = PageRequest.of(condition.getPageNumber()-1, condition.getPageSize(), new Sort(Sort.Direction.DESC, "id"));

        Specification<JourneyNoteEntity> sp = new Specification<JourneyNoteEntity>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<JourneyNoteEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                if (StringUtils.isNotEmpty(condition.getJourneyId())) {
                    predicate.getExpressions().add(cb.equal(root.get("journeyId"), condition.getJourneyId()));
                }
                return predicate;
            }
        };
        Page<JourneyNoteEntity> pageList = journeyNoteRepository.findAll(sp, pageable);
        List<JourneyNoteEntity> list = pageList.getContent();
        List<JourneyNoteInfo> noteInfos = list.stream().map(journeyNote -> {
            JourneyNoteInfo journeyNoteInfo = new JourneyNoteInfo();

            journeyNoteInfo.setContent(journeyNote.getContent());
            journeyNoteInfo.setJourneyId(journeyNote.getId());
            journeyNoteInfo.setId(journeyNote.getId());
            String domainPort = platformUtils.getDomainPort();

            ImagesEntity imagesEntity = journeyNote.getCoverImage();
            if (imagesEntity != null) {
                String imageUrlByPath = platformUtils.getImageInfo(imagesEntity, domainPort).getUrl();
                journeyNoteInfo.setCoverImageUrl(imageUrlByPath);
            }
            journeyNoteInfo.setNoteDate(journeyNote.getNoteDate());

            return journeyNoteInfo;
        }).collect(Collectors.toList());
        PageImpl<JourneyNoteInfo> result = new PageImpl<>(noteInfos, pageable, pageList.getTotalElements());
        return result;
    }
}
