package org.flightythought.smile.admin.service.impl;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.CaseAuditInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.CourseRegistrationEntity;
import org.flightythought.smile.admin.database.entity.JourneyEntity;
import org.flightythought.smile.admin.database.entity.RecoverCaseEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.*;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.CaseAuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CaseAuditServiceImpl implements CaseAuditService {

    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private JourneyToReportRepository journeyToReportRepository;
    @Autowired
    private JourneyNormRepository journeyNormRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private RecoverCaseRepository recoverCaseRepository;

    @Override
    public Page<CaseAuditInfo> findAll(Map<String, String> params) {
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");
        String audit = params.get("audit");
        String recoverCase = params.get("recoverCase");

        JourneyEntity journey = new JourneyEntity();
        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        if (StringUtils.isNotBlank(audit)) {
            journey.setAudit(Boolean.valueOf(audit));
        }
        if (StringUtils.isNotBlank(recoverCase)) {
            journey.setRecoverCase(Boolean.valueOf(recoverCase));
        }

        PageRequest pageRequest = PageRequest.of(Integer.valueOf(pageNumber) - 1, Integer.valueOf(pageSize));
        Page<JourneyEntity> page = journeyRepository.findAll(Example.of(journey), pageRequest);
        List<CaseAuditInfo> auditInfos = page
                .getContent()
                .stream()
                .map(journeyEntity -> {
                    CaseAuditInfo caseAuditInfo = new CaseAuditInfo();
                    caseAuditInfo.setJourneyId(journeyEntity.getJourneyId());
                    caseAuditInfo.setJourneyName(journeyEntity.getJourneyName());
                    userRepository.findById(journeyEntity.getUserId())
                            .ifPresent(userEntity -> {
                                caseAuditInfo.setUserName(userEntity.getUsername());
                                caseAuditInfo.setUserId(userEntity.getId());
                            });
                    //相关疾病
                    List<String> diseases = journeyEntity.getDiseaseClassDetails()
                            .stream()
                            .map(diseaseClassDetailEntity -> diseaseClassDetailEntity.getDiseaseDetailName())
                            .collect(Collectors.toList());
                    caseAuditInfo.setDiseaseNames(diseases);
                    //加入课程
                    List<String> courses = journeyEntity.getCourses()
                            .stream().map(CourseRegistrationEntity::getTitle)
                            .collect(Collectors.toList());
                    caseAuditInfo.setJoinCourses(courses);
                    //解决方案
                    List<String> solutionNames = Lists.newArrayList();
                    List<Integer> solutionIds = Lists.newArrayList();
                    journeyEntity.getDiseaseClassDetails()
                            .forEach(diseaseClassDetailEntity -> {
                                solutionNames.add(diseaseClassDetailEntity.getDiseaseDetailName());
                                solutionIds.add(diseaseClassDetailEntity.getDiseaseDetailId());
                            });
                    caseAuditInfo.setSolutionNames(solutionNames);
                    caseAuditInfo.setSolutionIds(solutionIds);

                    return caseAuditInfo;
                }).collect(Collectors.toList());

        PageImpl<CaseAuditInfo> result = new PageImpl<>(auditInfos, pageRequest, page.getTotalElements());
        return result;
    }

    @Override
    public void addCase(Map<String, String> params, HttpSession session) throws Exception {
        String journeyId = params.get("journeyId");
        String solutionIds = params.get("solutionIds");
        String coverImage = params.get("coverImage");
        if (StringUtils.isBlank(journeyId)) {
            throw new FlightyThoughtException("journeyId 不能为空");
        }
        if (StringUtils.isBlank(solutionIds)) {
            throw new FlightyThoughtException("solutionId 不能为空");
        }
        if (StringUtils.isBlank(coverImage)) {
            throw new FlightyThoughtException("coverImage 不能为空");
        }
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        journeyRepository.findById(Integer.valueOf(journeyId))
                .ifPresent(journeyEntity -> {
                    //更改RecoverCase 为案例
                    journeyEntity.setRecoverCase(true);
                    journeyEntity.setUpdateUserName(sysUserEntity.getUserName());
                    journeyRepository.save(journeyEntity);
                    //解决方案
                    String[] split = solutionIds.split(",");
                    Arrays.stream(split)
                            .forEach(solutionId -> {
                                RecoverCaseEntity recoverCaseEntity = new RecoverCaseEntity();
                                recoverCaseEntity.setTitle(journeyEntity.getJourneyName());
                                recoverCaseEntity.setSolutionId(Integer.valueOf(solutionId));
                                recoverCaseEntity.setJourneyId(Integer.valueOf(journeyId));
                                recoverCaseEntity.setCoverImage(Integer.valueOf(coverImage));
                                recoverCaseEntity.setCaseStartTime(journeyEntity.getStartTime());
                                recoverCaseEntity.setCaseStartTime(journeyEntity.getEndTime());
                                recoverCaseEntity.setUserId(journeyEntity.getUserId());
                                recoverCaseEntity.setCreateUserName(sysUserEntity.getUserName());
                                recoverCaseRepository.save(recoverCaseEntity);

                                //评为案例后，解决方案看复人数+1
                                solutionRepository.findById(Integer.valueOf(solutionId))
                                        .ifPresent(solutionEntity -> {
                                            int recoverNum = solutionEntity.getRecoverNumber() == null ? 0 : solutionEntity.getRecoverNumber();
                                            solutionEntity.setRecoverNumber(recoverNum + 1);
                                            solutionRepository.save(solutionEntity);
                                        });

                            });
                });

    }

    @Override
    public void cancelCase(Integer id) throws Exception {
        recoverCaseRepository.findById(id)
                .ifPresent(solutionEntity -> {
                    recoverCaseRepository.deleteById(solutionEntity.getId());
                    //解决方案中的康复人数-1
                    solutionRepository.updateRecoverNum(solutionEntity.getSolutionId());

                });
    }

    @Override
    public void doAudit(Map<String, String> params, HttpSession session) throws FlightyThoughtException {
        String journeyId = params.get("journeyId");
        if (StringUtils.isBlank(journeyId)) {
            throw new FlightyThoughtException("journeyId 不能为空");
        }

        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        journeyRepository.findById(Integer.valueOf(journeyId))
                .ifPresent(journeyEntity -> {
                    //更改旅程表审核状态为已审核
                    journeyEntity.setAudit(true);
                    journeyEntity.setRecoverCase(false);
                    journeyEntity.setUpdateUserName(sysUserEntity.getUserName());
                    journeyRepository.save(journeyEntity);
                });

    }
}
