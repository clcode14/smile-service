package org.flightythought.smile.admin.service.impl;

import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.CaseAuditInfo;
import org.flightythought.smile.admin.bean.FileInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.*;
import org.flightythought.smile.admin.dto.CheckCaseAuditDTO;
import org.flightythought.smile.admin.dto.HealthOrDiseaseByIdQueryDTO;
import org.flightythought.smile.admin.framework.exception.FlightyThoughtException;
import org.flightythought.smile.admin.service.CaseAuditService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class CaseAuditServiceImpl implements CaseAuditService {

    private static final Logger LOG = LoggerFactory.getLogger(CaseAuditServiceImpl.class);

    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private JourneyToSolutionRepository journeyToSolutionRepository;
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
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private EntityManager entityManager;
    @Value("${static-url}")
    private String staticUrl;
    @Value("${server.servlet.context-path}")
    private String contentPath;
    @Value("${oss-status}")
    private Boolean ossStatus;

    @Override
    @Transactional
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
                    // 旅程ID
                    caseAuditInfo.setJourneyId(journeyEntity.getJourneyId());
                    // 旅程名称
                    caseAuditInfo.setJourneyName(journeyEntity.getJourneyName());
                    // 创建用户
                    UserEntity userEntity = journeyEntity.getUser();
                    // 用户名
                    caseAuditInfo.setUserName(userEntity.getUsername());
                    // 用户ID
                    caseAuditInfo.setUserId(userEntity.getId());
                    // 用户昵称
                    caseAuditInfo.setNickName(userEntity.getNickName());
                    // 是否结束
                    caseAuditInfo.setFinished(journeyEntity.getFinished() == null ? false : journeyEntity.getFinished());
                    // 是否为康复案例
                    caseAuditInfo.setRecoverCase(journeyEntity.getRecoverCase() == null ? false : journeyEntity.getRecoverCase());
                    // 审核状态
                    caseAuditInfo.setAudit(journeyEntity.getAudit() == null ? false : journeyEntity.getAudit());
                    // 养生旅程标题
                    caseAuditInfo.setTitle(journeyEntity.getJourneyName());
                    //相关疾病
                    List<String> diseases = journeyEntity.getDiseaseClassDetails()
                            .stream()
                            .map(DiseaseClassDetailEntity::getDiseaseDetailName)
                            .collect(Collectors.toList());
                    caseAuditInfo.setDiseaseNames(diseases);
                    // 加入课程
                    List<String> courses = journeyEntity.getCourses()
                            .stream().map(CourseRegistrationEntity::getTitle)
                            .collect(Collectors.toList());
                    caseAuditInfo.setCourses(courses);
                    // 解决方案
                    List<String> solutionNames = Lists.newArrayList();
                    journeyEntity.getDiseaseClassDetails()
                            .forEach(diseaseClassDetailEntity -> {
                                solutionNames.add(diseaseClassDetailEntity.getDiseaseDetailName());
                            });
                    caseAuditInfo.setSolutions(solutionNames);
                    // 养生
                    List<String> healths = journeyEntity.getHealthClassDetails()
                            .stream().map(HealthEntity::getHealthName)
                            .collect(Collectors.toList());
                    caseAuditInfo.setHealths(healths);
                    // 养生成果
                    List<String> healthResult = journeyEntity.getHealthResults()
                            .stream().map(HealthResultEntity::getName)
                            .collect(Collectors.toList());
                    caseAuditInfo.setHealthResult(healthResult);
                    // 获取旅程体检报告
                    List<FileInfo> startReports = new ArrayList<>();
                    List<FileInfo> endReports = new ArrayList<>();
                    List<MedicalReportEntity> medicalReportEntities = journeyEntity.getMedicalReports();
                    if (medicalReportEntities != null && medicalReportEntities.size() > 0) {
                        String domainPort = platformUtils.getDomainPort();
                        medicalReportEntities.forEach(medicalReportEntity -> {
                            FileInfo fileInfo = new FileInfo();
                            // 资源URL
                            if (ossStatus) {
                                fileInfo.setUrl(platformUtils.getOssUrl(medicalReportEntity.getOssKey()));
                            } else {
                                String url = platformUtils.getMedicalReportStaticUrlByPath(medicalReportEntity.getPath(), domainPort);
                                fileInfo.setUrl(url);
                            }
                            // 文件名称
                            fileInfo.setName(medicalReportEntity.getFileName());
                            // 主键ID
                            fileInfo.setId(medicalReportEntity.getId());
                            // 文件大小
                            fileInfo.setSize(medicalReportEntity.getSize());
                            // 类型
                            fileInfo.setType(medicalReportEntity.getType());
                            if (medicalReportEntity.getType() == 0) {
                                // 开启养生旅程上传报告
                                startReports.add(fileInfo);
                            } else {
                                endReports.add(fileInfo);
                            }
                        });
                    }
                    List<FileInfo> fileInfos = new ArrayList<>();
                    fileInfos.addAll(startReports);
                    fileInfos.addAll(endReports);
                    caseAuditInfo.setReports(fileInfos);
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
//                                recoverCaseEntity.setCoverImage(Integer.valueOf(coverImage));
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
    @Transactional(rollbackFor = Exception.class)
    public void doAudit(CheckCaseAuditDTO checkCaseAuditDTO) throws FlightyThoughtException {
        Integer journeyId = checkCaseAuditDTO.getJourneyId();
        if (journeyId == null) {
            throw new FlightyThoughtException("journeyId 不能为空");
        }
        // 获取养生旅程
        JourneyEntity journeyEntity = journeyRepository.findByJourneyId(journeyId);
        if (journeyEntity.getAudit() != null && journeyEntity.getAudit()) {
            throw new FlightyThoughtException("该旅程已审核！");
        }
        SysUserEntity sysUserEntity = platformUtils.getCurrentLoginUser();
        // 判断是标为审核还是被评为案例
        if (checkCaseAuditDTO.getCheckType() == GlobalConstant.CHECK_CASE_RESULT) {
            // 审核被被评为案例
            journeyEntity.setAudit(true);
            journeyEntity.setRecoverCase(true);
            // 获取该养生旅程所关联的全部解决方案
            List<Integer> diseaseDetailIds = journeyEntity.getDiseaseClassDetails().stream().map(DiseaseClassDetailEntity::getDiseaseDetailId).collect(Collectors.toList());
            List<Integer> healthIds = journeyEntity.getHealthClassDetails().stream().map(HealthEntity::getHealthId).collect(Collectors.toList());
            HealthOrDiseaseByIdQueryDTO queryDTO = new HealthOrDiseaseByIdQueryDTO();
            queryDTO.setDiseaseDetailIds(diseaseDetailIds);
            queryDTO.setHealthIds(healthIds);
            List<SolutionEntity> solutionEntities = null;
            try {
                solutionEntities = getSolutions(queryDTO).getContent();
            } catch (FlightyThoughtException e) {
                LOG.error("找不多关联的解决方案", e);
                // 查找旅程关联的解决方案
               List<JourneyToSolutionEntity> journeyToSolutionEntities = journeyToSolutionRepository.findByJourneyId(journeyId);
               List<Integer> solutionIds = journeyToSolutionEntities.stream().map(JourneyToSolutionEntity::getSolutionId).collect(Collectors.toList());
               if (solutionIds.size() > 0) {
                   solutionEntities = solutionRepository.findByIdIn(solutionIds);
               }
            }
            // 创建案例集合
            List<RecoverCaseEntity> recoverCaseEntities = new ArrayList<>();
            // 获取养生旅程用户
            UserEntity userEntity = journeyEntity.getUser();
            if (solutionEntities == null) {
                // 标记为已审核
                journeyEntity.setAudit(true);
                // 不是案例
                journeyEntity.setRecoverCase(false);
                journeyRepository.save(journeyEntity);
                return;
            }
            solutionEntities.forEach(solutionEntity -> {
                // 查询该解决方案对应该旅程的用户是否已经被列入为康复人数
                Integer solutionId = solutionEntity.getId();
                // 用户ID
                Long userId = userEntity.getId();
                List<RecoverCaseEntity> historyEntities = recoverCaseRepository.findBySolutionIdAndUserId(solutionId, userId);
                if (historyEntities == null || historyEntities.size() == 0) {
                    // 该解决方案对于该用户来讲，没有列入康复人数,所以康复人数 + 1
                    solutionEntity.setRecoverNumber(solutionEntity.getRecoverNumber() == null ? 1 : solutionEntity.getRecoverNumber() + 1);
                    solutionRepository.save(solutionEntity);
                }
                // 创建康复案例对象
                RecoverCaseEntity recoverCaseEntity = new RecoverCaseEntity();
                // 养生旅程ID
                recoverCaseEntity.setJourneyId(journeyId);
                // 解决方案ID
                recoverCaseEntity.setSolutionId(solutionEntity.getId());
                // 养生旅程对应的封面图
                recoverCaseEntity.setCoverImageId(journeyEntity.getCoverImageId());
                // 标题
                recoverCaseEntity.setTitle(checkCaseAuditDTO.getTitle());
                // 案例开始时间
                recoverCaseEntity.setCaseStartTime(journeyEntity.getStartTime());
                // 案例结束时间
                recoverCaseEntity.setCaseEndTime(journeyEntity.getEndTime());
                // 作者
                recoverCaseEntity.setUserId(userId);
                // 阅读数
                recoverCaseEntity.setReadNum(0L);
                // 创建者
                recoverCaseEntity.setCreateUserName(sysUserEntity.getLoginName());
                recoverCaseEntities.add(recoverCaseEntity);
            });
            // 保存康复案例
            recoverCaseRepository.saveAll(recoverCaseEntities);
        } else if (checkCaseAuditDTO.getCheckType() == GlobalConstant.CHECK_CASE) {
            // 标记为已审核
            journeyEntity.setAudit(true);
            // 不是案例
            journeyEntity.setRecoverCase(false);
        }
        // 报错养生旅程
        journeyRepository.save(journeyEntity);
    }

    public Page<SolutionEntity> getSolutions(HealthOrDiseaseByIdQueryDTO querySolutionDTO) throws FlightyThoughtException {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) AS total FROM (";
        StringBuilder sql = new StringBuilder("SELECT DISTINCT a.`id` AS solutionId FROM `vw_disease_health_solution` a WHERE 1 = 1");
        List<Integer> diseaseDetailIds = querySolutionDTO.getDiseaseDetailIds();
        if (diseaseDetailIds != null && diseaseDetailIds.size() > 0) {
            sql.append(" AND a.`disease_detail_id` IN (");
            diseaseDetailIds.forEach(integer -> sql.append(integer).append(","));
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        List<Integer> healthIds = querySolutionDTO.getHealthIds();
        if (healthIds != null && healthIds.size() > 0) {
            sql.append(" OR a.`health_id` IN (");
            healthIds.forEach(integer -> sql.append(integer).append(","));
            sql.deleteCharAt(sql.length() - 1);
            sql.append(")");
        }
        totalSql += sql.toString() + ") T";
        // 获取Total总数
        Integer total = (Integer) entityManager.createNativeQuery(totalSql).unwrap(NativeQueryImpl.class).addScalar("total", IntegerType.INSTANCE).getSingleResult();
        if (total == 0) {
            throw new FlightyThoughtException("没有查询到对应的解决方案");
        }
        // 是否存在分页查询
        Integer pageNumber = querySolutionDTO.getPageNumber();
        Integer pageSize = querySolutionDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql.append(" LIMIT ").append((pageNumber - 1) * pageSize).append(",").append(pageSize);
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Integer> solutionIds = entityManager.createNativeQuery(sql.toString())
                .unwrap(NativeQueryImpl.class)
                .addScalar("solutionId", IntegerType.INSTANCE)
                .list();
        List<SolutionEntity> solutionEntities = solutionRepository.findByIdIn(solutionIds);
        return new PageImpl<>(solutionEntities, pageable, total);
    }
}
