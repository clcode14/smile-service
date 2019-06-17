package org.flightythought.smile.admin.service.impl;

import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.bean.JourneyNote;
import org.flightythought.smile.admin.bean.JourneyNoteInfo;
import org.flightythought.smile.admin.bean.JourneyNoteNorm;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.admin.database.entity.ImagesEntity;
import org.flightythought.smile.admin.database.entity.JourneyNoteEntity;
import org.flightythought.smile.admin.database.entity.JourneyNoteNormEntity;
import org.flightythought.smile.admin.database.repository.HealthNormTypeRepository;
import org.flightythought.smile.admin.database.repository.JourneyNoteRepository;
import org.flightythought.smile.admin.dto.JourneyNoteQueryDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
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
    private PlatformUtils platformUtils;
    @Autowired
    private HealthNormTypeRepository healthNormTypeRepository;

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
        Pageable pageable = PageRequest.of(condition.getPageNumber() - 1, condition.getPageSize(), new Sort(Sort.Direction.DESC, "id"));

        Specification<JourneyNoteEntity> sp = new Specification<JourneyNoteEntity>() {
            private static final long serialVersionUID = 1L;

            @Override
            public Predicate toPredicate(Root<JourneyNoteEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = cb.conjunction();
                predicate.getExpressions().add(cb.equal(root.get("journeyId"), condition.getJourneyId()));
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

    @Override
    public Page<JourneyNote> getJourneyHealthNote(JourneyNoteQueryDTO journeyNoteQueryDTO) throws FlightyThoughtException {
        Integer journeyId = journeyNoteQueryDTO.getJourneyId();
        if (journeyId == null) {
            throw new FlightyThoughtException("请传递旅程ID");
        }
        String domainPort = platformUtils.getDomainPort();
        List<JourneyNoteEntity> journeyNoteEntities;
        PageRequest pageRequest;
        Integer pageSize = journeyNoteQueryDTO.getPageSize();
        Integer pageNumber = journeyNoteQueryDTO.getPageNumber();
        long total;
        if (pageSize == null || pageSize == 0 || pageNumber == null || pageNumber == 0) {
            journeyNoteEntities = journeyNoteRepository.findByJourneyId(journeyId);
            pageRequest = PageRequest.of(0, journeyNoteEntities.size());
            total = (long) journeyNoteEntities.size();
        } else {
            pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            Page<JourneyNoteEntity> journeyNoteEntityPage = journeyNoteRepository.findByJourneyId(journeyId, pageRequest);
            journeyNoteEntities = journeyNoteEntityPage.getContent();
            total = journeyNoteEntityPage.getTotalElements();
        }
        if (journeyNoteEntities.size() > 0) {
            // 获取体检指标
            List<HealthNormTypeEntity> healthNormTypeEntities = healthNormTypeRepository.findAll();
            Map<Integer, HealthNormTypeEntity> healthNormTypeEntityMap = new HashMap<>();
            healthNormTypeEntities.forEach(healthNormTypeEntity -> healthNormTypeEntityMap.put(healthNormTypeEntity.getNormTypeId(), healthNormTypeEntity));
            List<JourneyNote> journeyNotes = new ArrayList<>();
            DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM);
            journeyNoteEntities.forEach(journeyNoteEntity -> {
                JourneyNote journeyNote = new JourneyNote();
                // 养生旅程ID
                journeyNote.setJourneyId(journeyNoteEntity.getJourneyId());
                // 封面图片URL
                ImagesEntity coverImage = journeyNoteEntity.getCoverImage();
                if (coverImage != null) {
                    String coverImageUrl = platformUtils.getImageInfo(coverImage, domainPort).getUrl();
                    journeyNote.setCoverImageUrl(coverImageUrl);
                }
                // 日记内容
                journeyNote.setContent(journeyNoteEntity.getContent());
                // 是否发布到朋友圈
                journeyNote.setCircleOfFriends(journeyNoteEntity.getCircleOfFriends());
                // 日记时间
                journeyNote.setNoteDate(journeyNoteEntity.getNoteDate());
                // 日记时间
                String noteDateStr = formatter.format(journeyNoteEntity.getNoteDate());
                journeyNote.setNoteDateStr(noteDateStr);
                // 图片
                List<ImagesEntity> imagesEntities = journeyNoteEntity.getImages();
                if (imagesEntities != null) {
                    List<ImageInfo> imageInfos = new ArrayList<>();
                    imagesEntities.forEach(imagesEntity -> {
                        ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domainPort);
                        imageInfos.add(imageInfo);
                    });
                    journeyNote.setImages(imageInfos);
                }
                // 体检指标
                List<JourneyNoteNormEntity> journeyNoteNormEntities = journeyNoteEntity.getJourneyNoteNorms();
                if (journeyNoteNormEntities != null) {
                    List<JourneyNoteNorm> journeyNorms = new ArrayList<>();
                    journeyNoteNormEntities.forEach(journeyNoteNormEntity -> {
                        JourneyNoteNorm journeyNorm = new JourneyNoteNorm();
                        // 自增主键
                        journeyNorm.setId(journeyNoteNormEntity.getId());
                        // 体检指标类型ID
                        journeyNorm.setNormTypeId(journeyNoteNormEntity.getNormTypeId());
                        // 指标名称
                        HealthNormTypeEntity healthNormTypeEntity = healthNormTypeEntityMap.get(journeyNoteNormEntity.getNormTypeId());
                        if (healthNormTypeEntity != null) {
                            journeyNorm.setNormName(healthNormTypeEntity.getNormName());
                        }
                        // 养生旅程ID
                        journeyNorm.setJourneyId(journeyNoteEntity.getJourneyId());
                        // 数值1
                        journeyNorm.setValue1(journeyNoteNormEntity.getValue1());
                        // 数值2
                        journeyNorm.setValue2(journeyNoteNormEntity.getValue2());
                        journeyNorms.add(journeyNorm);
                    });
                    journeyNote.setJourneyNoteNorms(journeyNorms);
                }
                journeyNotes.add(journeyNote);
            });
            return new PageImpl<>(journeyNotes, pageRequest, total);
        }
        return new PageImpl<>(new ArrayList<>(), pageRequest, 0);
    }
}
