package org.flightythought.smile.admin.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.SelectItemOption;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.MD5Util;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.*;
import org.flightythought.smile.admin.dto.CaseEntryDTO;
import org.flightythought.smile.admin.dto.DiseaseResultDTO;
import org.flightythought.smile.admin.dto.HealthNormDTO;
import org.flightythought.smile.admin.dto.NoteDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.CaseInputService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
public class CaseInputServiceImpl implements CaseInputService {

    @Autowired
    private DiseaseClassDetailRepository diseaseClassDetailRepository;
    @Autowired
    private HealthResultRepository healthResultRepository;
    @Autowired
    private HealthNormTypeRepository healthNormTypeRepository;
    @Autowired
    private HealthRepository healthRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private JourneyNormRepository journeyNormRepository;
    @Autowired
    private JourneyDiseaseRepository journeyDiseaseRepository;
    @Autowired
    private JourneyHealthRepository journeyHealthRepository;
    @Autowired
    private JourneyNoteRepository journeyNoteRepository;
    @Autowired
    private JourneyNoteToImageRepository journeyNoteToImageRepository;
    @Autowired
    private JourneyToSolutionRepository journeyToSolutionRepository;

    @Override
    public List<SelectItemOption> getDisease() {
        List<DiseaseClassDetailEntity> entities = diseaseClassDetailRepository.findAll();
        return entities.stream().map(disease -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(disease.getDiseaseDetailId() + "");
            option.setValue(disease.getDiseaseDetailName());
            return option;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SelectItemOption> getHealthResult() {
        List<HealthResultEntity> healthResultEntities = healthResultRepository.findAll();
        return healthResultEntities.stream().map(healthResult -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(healthResult.getId() + "");
            option.setValue(healthResult.getName());
            return option;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SelectItemOption> getSolution() {
        List<SolutionEntity> entities = solutionRepository.findAll();
        return entities.stream().map(solution -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(solution.getId() + "");
            option.setValue(solution.getTitle());
            return option;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SelectItemOption> getHealthNormType() {
        List<HealthNormTypeEntity> normTypeEntities = healthNormTypeRepository.findAll();
        return normTypeEntities.stream().map(healthNormType -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(healthNormType.getNormTypeId() + "");
            option.setValue(healthNormType.getNormName());
            option.setType(healthNormType.getInputType() + "");
            return option;
        }).collect(Collectors.toList());
    }

    @Override
    public List<SelectItemOption> getHealth() {
        List<HealthEntity> entities = healthRepository.findAll();
        return entities.stream().map(healthNormType -> {
            SelectItemOption option = new SelectItemOption();
            option.setKey(healthNormType.getHealthId() + "");
            option.setValue(healthNormType.getHealthName());
            return option;
        }).collect(Collectors.toList());
    }

    @Override
    public void addCase(CaseEntryDTO caseEntryDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        // 新增用户
        String phone = caseEntryDTO.getPhone();
        if (StringUtils.isBlank(phone)) {
            throw new FlightyThoughtException("请输入手机号");
        }
        String username = MD5Util.md5(phone);
        UserEntity userEntity = userRepository.findByUsername(username);
        if (userEntity == null) {
            userEntity = new UserEntity();
            String nickname = caseEntryDTO.getNickname();
            userEntity.setNickName(nickname);
            userEntity.setMobile(phone);
            userEntity.setUsername(username);
            userEntity = userRepository.save(userEntity);
        }


        // 新增养生旅程
        JourneyEntity journeyEntity = new JourneyEntity();
        // 是否审核
        journeyEntity.setAudit(false);
        // 访问量
        journeyEntity.setReadNum(0);
        // 是否为康复案例
        journeyEntity.setRecoverCase(false);
        // 是否结束
        journeyEntity.setFinished(true);
        // 开始时间
        journeyEntity.setStartTime(caseEntryDTO.getStartTime());
        // 结束时间
        journeyEntity.setEndTime(caseEntryDTO.getEndTime());
        // 养生旅程名称
        journeyEntity.setJourneyName(caseEntryDTO.getTitle());
        // 养生旅程概述
        journeyEntity.setSummarize(caseEntryDTO.getContent());
        // 创建者
        journeyEntity.setCreateUserName(sysUserEntity.getLoginName());
        // 日记数量
        journeyEntity.setNoteNum(caseEntryDTO.getNotes() == null ? 0 : caseEntryDTO.getNotes().size());
        // 用户ID
        journeyEntity.setUserId(userEntity.getId());
        journeyEntity = journeyRepository.save(journeyEntity);

        // 体检指标
        List<HealthNormDTO> healthNormDTOS = caseEntryDTO.getNorm();
        JourneyEntity finalJourneyEntity = journeyEntity;
        List<JourneyNormEntity> journeyNormEntities = healthNormDTOS.stream().map(healthNormDTO -> {
            JourneyNormEntity journeyNormEntity = new JourneyNormEntity();
            journeyNormEntity.setJourneyId(finalJourneyEntity.getJourneyId());
            journeyNormEntity.setNormTypeId(healthNormDTO.getNormTypeId());
            journeyNormEntity.setStartValue1(healthNormDTO.getStartValue1());
            journeyNormEntity.setStartValue2(healthNormDTO.getStartValue2());
            journeyNormEntity.setEndValue1(healthNormDTO.getEndValue1());
            journeyNormEntity.setEndValue2(healthNormDTO.getEndValue2());
            return journeyNormEntity;
        }).collect(Collectors.toList());
        journeyNormRepository.saveAll(journeyNormEntities);

        // 疾病
        List<DiseaseResultDTO> diseaseResultDTOS = caseEntryDTO.getDisease();
        List<JourneyDiseaseEntity> journeyDiseaseEntities = diseaseResultDTOS.stream().map(diseaseResultDTO -> {
            JourneyDiseaseEntity journeyDiseaseEntity = new JourneyDiseaseEntity();
            journeyDiseaseEntity.setJourneyId(finalJourneyEntity.getJourneyId());
            journeyDiseaseEntity.setDiseaseDetailId(diseaseResultDTO.getDiseaseDetailId());
            journeyDiseaseEntity.setHealthResultId(diseaseResultDTO.getHealthResultId());
            return journeyDiseaseEntity;
        }).collect(Collectors.toList());
        journeyDiseaseRepository.saveAll(journeyDiseaseEntities);

        // 养生
        List<Integer> healthIds = caseEntryDTO.getHealthIds();
        List<JourneyHealthEntity> journeyHealthEntities = healthIds.stream().map(healthId -> {
            JourneyHealthEntity journeyHealthEntity = new JourneyHealthEntity();
            journeyHealthEntity.setHealthId(healthId);
            journeyHealthEntity.setJourneyId(finalJourneyEntity.getJourneyId());
            return journeyHealthEntity;
        }).collect(Collectors.toList());
        journeyHealthRepository.saveAll(journeyHealthEntities);

        // 解决方案
        List<Integer> solutionIds = caseEntryDTO.getSolutionIds();
        if (solutionIds != null && solutionIds.size() > 0) {
            for (Integer solutionId : solutionIds) {
                JourneyToSolutionEntity journeyToSolutionEntity = new JourneyToSolutionEntity();
                journeyToSolutionEntity.setJourneyId(finalJourneyEntity.getJourneyId());
                journeyToSolutionEntity.setSolutionId(solutionId);
                journeyToSolutionRepository.save(journeyToSolutionEntity);
            }
        }

        // 日记
        List<NoteDTO> noteDTOS = caseEntryDTO.getNotes();
        for (NoteDTO noteDTO : noteDTOS) {
            // 养生日记
            JourneyNoteEntity journeyNoteEntity = new JourneyNoteEntity();
            journeyNoteEntity.setCoverImageId(noteDTO.getCoverImageId());
            journeyNoteEntity.setContent(noteDTO.getContent());
            journeyNoteEntity.setNoteDate(noteDTO.getNoteTime());
            journeyNoteEntity.setJourneyId(finalJourneyEntity.getJourneyId());
            // 保存日记
            journeyNoteEntity = journeyNoteRepository.save(journeyNoteEntity);
            // 保存配图
            List<Integer> imageIds = noteDTO.getImageIds();
            if (imageIds != null && imageIds.size() > 0) {
                for (Integer imageId : imageIds) {
                    JourneyNoteToImageEntity journeyNoteToImageEntity = new JourneyNoteToImageEntity();
                    journeyNoteToImageEntity.setImageId(imageId);
                    journeyNoteToImageEntity.setNoteId(journeyNoteEntity.getId());
                    journeyNoteToImageRepository.save(journeyNoteToImageEntity);
                }
            }
        }
    }

}
