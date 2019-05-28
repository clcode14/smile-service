package org.flightythought.smile.appserver.service.impl;

import com.aliyun.oss.OSSClient;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.flightythought.smile.appserver.bean.*;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.config.ALiOSSConfig;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.*;
import org.flightythought.smile.appserver.dto.*;
import org.flightythought.smile.appserver.service.CommodityService;
import org.flightythought.smile.appserver.service.JourneyHealthService;
import org.flightythought.smile.appserver.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

import static org.flightythought.smile.appserver.service.impl.CommonServiceImpl.getSuffix;

@Service
public class JourneyHealthServiceImpl implements JourneyHealthService {

    @Autowired
    private HealthNormTypeRepository healthNormTypeRepository;
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Autowired
    private MedicalReportRepository medicalReportRepository;
    @Autowired
    private JourneyRepository journeyRepository;
    @Autowired
    private JourneyNormRepository journeyNormRepository;
    @Autowired
    private JourneyToReportRepository journeyToReportRepository;
    @Autowired
    private JourneyDiseaseRepository journeyDiseaseRepository;
    @Autowired
    private JourneyNoteRepository journeyNoteRepository;
    @Autowired
    private JourneyNoteToImageRepository journeyNoteToImageRepository;
    @Autowired
    private JourneyNoteNormRepository journeyNoteNormRepository;
    @Autowired
    private JourneyHealthRepository journeyHealthRepository;
    @Autowired
    private JourneyToCourseRepository journeyToCourseRepository;
    @Autowired
    private HealthResultRepository healthResultRepository;
    @Autowired
    private UserFollowCourseRepository userFollowCourseRepository;
    @Autowired
    private RecoverCaseRepository recoverCaseRepository;
    @Autowired
    private JourneyToCommodityRepository journeyToCommodityRepository;
    @Autowired
    private CommodityService commodityService;
    @Autowired
    private UserService userService;
    @Autowired
    private ALiOSSConfig aLiOSSConfig;

    @Value("${static-url}")
    private String staticUrl;
    @Value("${server.servlet.context-path}")
    private String contentPath;
    @Value("${oss-status}")
    private Boolean ossStatus;


    private static final Logger LOG = LoggerFactory.getLogger(JourneyHealthServiceImpl.class);

    @Override
    public List<HealthNormTypeEntity> getHealthNormTypes() {
        return healthNormTypeRepository.findAll();
    }

    @Override
    public List<FileInfo> uploadReport(List<MultipartFile> files, Integer type) throws FlightyThoughtException {
        String fileType = "";
        if (type == null) {
            throw new FlightyThoughtException("请指定上传的体检报告类型");
        }
        switch (type) {
            // 开启养生旅程上传体检报告
            case 0: {
                fileType = "startReport";
                break;
            }
            case 1: {
                fileType = "endReport";
                break;
            }
        }
        // 获取当前登陆用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        SysParameterEntity domainPortEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = domainPortEntity.getParameterValue();
        String filePath = "", userPath;
        if (ossStatus) {
            if (!"".equals(fileType)) {
                userPath = fileType + "/" + userEntity.getId();
            } else {
                userPath = userEntity.getId() + "";
            }
        } else {
            if (!"".equals(fileType)) {
                userPath = File.separator + fileType + File.separator + userEntity.getId();
            } else {
                userPath = File.separator + userEntity.getId();
            }
        }
        if (!ossStatus) {
            // 获取系统参数
            SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
            if (sysParameterEntity == null) {
                throw new FlightyThoughtException("请设置上传文件路径系统参数");
            } else {
                filePath = sysParameterEntity.getParameterValue();
                File file = new File(filePath + userPath);
                if (!file.exists()) {
                    file.mkdirs();
                }
            }
        }
        List<MedicalReportEntity> medicalReportEntities = new ArrayList<>();
        if (files == null || files.size() == 0) {
            throw new FlightyThoughtException("请选择要上传的体检报告");
        }
        for (MultipartFile multipartFile : files) {
            if (multipartFile != null) {
                MedicalReportEntity medicalReportEntity = new MedicalReportEntity();
                // 上传者
                medicalReportEntity.setCreateUserName(userEntity.getId() + "");
                // 文件大小
                medicalReportEntity.setSize(multipartFile.getSize());
                // 文件类型
                medicalReportEntity.setFileType(multipartFile.getContentType());
                // 类型
                medicalReportEntity.setType(type);
                // 文件名称
                String fileName = multipartFile.getOriginalFilename();
                // 新建文件
                if (fileName != null) {
                    if (!ossStatus) {
                        String path = userPath + File.separator + System.currentTimeMillis() + fileName.substring(fileName.lastIndexOf("."));
                        // 文件名称
                        medicalReportEntity.setFileName(fileName);
                        // 文件路径
                        medicalReportEntity.setPath(path);
                        File file = new File(filePath + path);
                        try {
                            // 创建文件输出流
                            FileOutputStream fileOutputStream = new FileOutputStream(file);
                            // 复制文件
                            IOUtils.copy(multipartFile.getInputStream(), fileOutputStream);
                            fileOutputStream.flush();
                            fileOutputStream.close();
                            medicalReportEntities.add(medicalReportEntity);
                        } catch (IOException e) {
                            LOG.error("上传体检报告失败", e);
                            throw new FlightyThoughtException("上传体检报告失败", e);
                        }
                    } else {
                        // OSS 对象存储
                        // 1. 获取文件后缀名
                        String suffix = getSuffix(multipartFile);
                        // 2. 获取OSS参数信息
                        String endpoint = aLiOSSConfig.getEndpoint();
                        String accessKeyId = aLiOSSConfig.getAccessKeyId();
                        String accessKeySecret = aLiOSSConfig.getAccessKeySecret();
                        String bucketName = aLiOSSConfig.getBucketName();
                        // 创建OSSClient实例
                        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
                        // 文件KEY
                        String fileKey = userPath + "/" + System.currentTimeMillis() + "." + suffix;
                        // 上传文件
                        try {
                            ossClient.putObject(bucketName, fileKey, multipartFile.getInputStream());
                            // 设置URL过期时间为100年
                            Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000 * 24 * 365 * 100);

                            // 生成URL
                            URL url = ossClient.generatePresignedUrl(bucketName, fileKey, expiration);
                            // 保存数据对象
                            // 文件名称
                            medicalReportEntity.setFileName(fileName);
                            medicalReportEntity.setOssKey(fileKey);
                            medicalReportEntity.setOssUrl(url.toString());
                            medicalReportEntities.add(medicalReportEntity);
                        } catch (IOException e) {
                            LOG.error("上传图片失败", e);
                            throw new FlightyThoughtException("上传图片失败", e);
                        } finally {
                            ossClient.shutdown();
                        }
                    }
                }
            }
        }
        if (medicalReportEntities.size() > 0) {
            medicalReportEntities = medicalReportRepository.saveAll(medicalReportEntities);
            List<FileInfo> fileInfos = new ArrayList<>();
            medicalReportEntities.forEach(medicalReportEntity -> {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setId(medicalReportEntity.getId());
                fileInfo.setName(medicalReportEntity.getFileName());
                fileInfo.setSize(medicalReportEntity.getSize());
                fileInfo.setType(medicalReportEntity.getType());
                if (ossStatus) {
                    fileInfo.setUrl(medicalReportEntity.getOssUrl());
                } else {
                    String fileUrl = domainPort + contentPath + staticUrl + medicalReportEntity.getPath();
                    fileInfo.setUrl(fileUrl.replace("\\", "/"));
                }
                fileInfos.add(fileInfo);
            });
            return fileInfos;
        }
        return null;
    }

    @Override
    @Transactional
    public JourneyEntity startHealthJourney(HealthJourneyStartDTO healthJourneyStartDTO) {
        // 获得当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        // 查看当前用户有无未完成的养生旅程
        List<JourneyEntity> hasJourneyEntities = journeyRepository.findByUserIdAndFinished(userId, false);
        if (hasJourneyEntities != null && hasJourneyEntities.size() > 0) {
            throw new FlightyThoughtException("还有未完成的养生旅程，不能再次开启新的旅程");
        }
        // 获取养生旅程数据
        JourneyEntity journeyEntity = new JourneyEntity();
        // 养生旅程名称
        journeyEntity.setJourneyName(healthJourneyStartDTO.getJourneyName());
        // 养生旅程概述
        journeyEntity.setSummarize(healthJourneyStartDTO.getSummarize());
        // 用户ID
        journeyEntity.setUserId(userId);
        // 设置开始时间
        journeyEntity.setStartTime(LocalDateTime.now());
        // 创建者
        journeyEntity.setCreateUserName(userId + "");
        // 日记数量为0
        journeyEntity.setNoteNum(0);
        // 阅读数
        journeyEntity.setReadNum(0);
        // 封面图片ID
        journeyEntity.setCoverImageId(healthJourneyStartDTO.getCoverImageId());
        // 是否审核
        journeyEntity.setAudit(false);
        // 是否是案例
        journeyEntity.setRecoverCase(false);
        // 养生旅程是否结束
        journeyEntity.setFinished(false);
        // 保存养生旅程
        journeyEntity = journeyRepository.save(journeyEntity);
        // 获取体检指标
        List<HealthNormTypeDTO> healthNormTypeDTOS = healthJourneyStartDTO.getHealthNormTypes();
        if (healthNormTypeDTOS != null && healthNormTypeDTOS.size() > 0) {
            // 新增养生旅程指标数据对象
            List<JourneyNormEntity> journeyNormEntities = new ArrayList<>();
            for (HealthNormTypeDTO healthNormTypeDTO : healthNormTypeDTOS) {
                JourneyNormEntity journeyNormEntity = new JourneyNormEntity();
                // 体检指标类型ID
                journeyNormEntity.setNormTypeId(healthNormTypeDTO.getNormTypeId());
                // 开始数值1
                journeyNormEntity.setStartValue1(healthNormTypeDTO.getValue1());
                // 开始数值2
                journeyNormEntity.setStartValue2(healthNormTypeDTO.getValue2());
                // 创建者
                journeyNormEntity.setCreateUserName(userId + "");
                // 养生旅程ID
                journeyNormEntity.setJourneyId(journeyEntity.getJourneyId());
                journeyNormEntities.add(journeyNormEntity);
            }
            // 保存养生旅程指标数据
            journeyNormRepository.saveAll(journeyNormEntities);
        }
        // 获取体检报告
        // TODO 还需要判断体检报告是不是开始类型的体检报告
        List<FileImageDTO> files = healthJourneyStartDTO.getFiles();
        if (files != null && files.size() > 0) {
            List<JourneyToReportEntity> journeyToReportEntities = new ArrayList<>();
            for (FileImageDTO fileImageDTO : files) {
                // 判断绑定的体检报告是不是开启养生旅程的体检报告
                MedicalReportEntity medicalReportEntity = medicalReportRepository.findById(fileImageDTO.getId());
                if (medicalReportEntity == null) {
                    throw new FlightyThoughtException("id:" + fileImageDTO.getId() + "找不到对应的体检报告");
                } else {
                    if (medicalReportEntity.getType() != 0) {
                        throw new FlightyThoughtException("id：" + fileImageDTO.getId() + "的体检报告类型非开启养生旅程上传类型，不能绑定");
                    }
                    if (!medicalReportEntity.getCreateUserName().equals(userEntity.getId() + "")) {
                        throw new FlightyThoughtException("id：" + fileImageDTO.getId() + "的体检报告非自己上传不能绑定自身的养生旅程！");
                    }
                }
                JourneyToReportEntity journeyToReportEntity = new JourneyToReportEntity();
                journeyToReportEntity.setJourneyId(journeyEntity.getJourneyId());
                journeyToReportEntity.setReportId(fileImageDTO.getId());
                journeyToReportEntity.setStartFlag(true);
                journeyToReportEntities.add(journeyToReportEntity);
            }
            journeyToReportRepository.saveAll(journeyToReportEntities);
        }
        // 获取养生旅程选择的疾病小类（现有症状）
        List<Integer> diseaseClassDetailIds = healthJourneyStartDTO.getDiseaseClassDetailIds();
        List<JourneyDiseaseEntity> journeyDiseaseEntities = new ArrayList<>();
        if (diseaseClassDetailIds != null && diseaseClassDetailIds.size() > 0) {
            for (Integer id : diseaseClassDetailIds) {
                JourneyDiseaseEntity journeyDiseaseEntity = new JourneyDiseaseEntity();
                journeyDiseaseEntity.setJourneyId(journeyEntity.getJourneyId());
                journeyDiseaseEntity.setDiseaseDetailId(id);
                journeyDiseaseEntities.add(journeyDiseaseEntity);
            }
            journeyDiseaseRepository.saveAll(journeyDiseaseEntities);
        }

        return journeyEntity;
    }

    @Override
    public HealthJourney updateHealthJourney(HealthJourneyStartDTO healthJourneyStartDTO) throws FlightyThoughtException {
        Integer journeyId = healthJourneyStartDTO.getJourneyId();
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        if (journeyId == null) {
            throw new FlightyThoughtException("修改接口需传递journeyId");
        } else {
            JourneyEntity journeyEntity = journeyRepository.findByJourneyIdAndUserId(journeyId, userId);
            if (journeyEntity == null) {
                throw new FlightyThoughtException("未找到对应的养生旅程");
            }
            // 旅程名称
            journeyEntity.setJourneyName(healthJourneyStartDTO.getJourneyName());
            // 概述
            journeyEntity.setSummarize(healthJourneyStartDTO.getSummarize());
            // 更新者
            journeyEntity.setUpdateUserName(userEntity.getId() + "");
            // 更新
            journeyEntity = journeyRepository.save(journeyEntity);
            return getHealthJourney(journeyEntity.getJourneyId(), null);
        }
    }

    @Override
    public Page<HealthJourneySimple> getHealthJourney(Long userId, PageFilterDTO pageFilterDTO, Boolean finished) {
        List<JourneyEntity> journeyEntities;
        Integer pageSize = pageFilterDTO.getPageSize();
        Integer pageNumber = pageFilterDTO.getPageNumber();
        Long total;
        PageRequest pageRequest;
        if (userId == null || userId == 0) {
            UserEntity userEntity = platformUtils.getCurrentLoginUser();
            userId = userEntity.getId();
        }
        if (pageSize == null || pageSize == 0 || pageNumber == null || pageNumber == 0) {
            if (finished != null) {
                journeyEntities = journeyRepository.findByUserIdAndFinishedOrderByFinishedAsc(userId, finished);
            } else {
                journeyEntities = journeyRepository.findByUserIdOrderByFinishedAsc(userId);
            }
            pageRequest = PageRequest.of(0, journeyEntities.size() + 1);
            total = (long) journeyEntities.size();
        } else {
            pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            Page<JourneyEntity> journeyEntityPage;
            if (finished != null) {
                journeyEntityPage = journeyRepository.findByUserIdAndFinishedOrderByFinishedAsc(userId, finished, pageRequest);
            } else {
                journeyEntityPage = journeyRepository.findByUserIdOrderByFinishedAsc(userId, pageRequest);
            }
            journeyEntities = journeyEntityPage.getContent();
            total = journeyEntityPage.getTotalElements();
        }
        List<HealthJourneySimple> result = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        if (journeyEntities.size() > 0) {
            journeyEntities.forEach(journeyEntity -> {
                HealthJourneySimple healthJourneySimple = new HealthJourneySimple();
                healthJourneySimple.setJourneyId(journeyEntity.getJourneyId());
                healthJourneySimple.setJourneyName(journeyEntity.getJourneyName());
                healthJourneySimple.setSummarize(journeyEntity.getSummarize());
                healthJourneySimple.setUserId(journeyEntity.getUserId());
                healthJourneySimple.setStartTime(journeyEntity.getStartTime());
                healthJourneySimple.setEndTime(journeyEntity.getEndTime());
                healthJourneySimple.setFinished(journeyEntity.getFinished());
                healthJourneySimple.setReadNum(journeyEntity.getReadNum());
                healthJourneySimple.setNoteNum(journeyEntity.getNoteNum());
                long daysDiff = ChronoUnit.DAYS.between(journeyEntity.getStartTime(), now);
                healthJourneySimple.setDays(daysDiff);
                result.add(healthJourneySimple);
            });
        }
        return new PageImpl<>(result, pageRequest, total);
    }

    @Override
    @Transactional
    public HealthJourney getHealthJourney(Integer journeyId, Integer recoverId) {
        // 获取养生旅程
        JourneyEntity journeyEntity = journeyRepository.findByJourneyId(journeyId);
        String domainPort = platformUtils.getDomainPort();
        if (journeyEntity != null) {
            // 判断是否增加阅读数量
            UserEntity userEntity = platformUtils.getCurrentLoginUser();
            if (journeyEntity.getUserId().longValue() != userEntity.getId().longValue()) {
                if (recoverId != null) {
                    // 增加康复案例阅读量
                    RecoverCaseEntity recoverCaseEntity = recoverCaseRepository.findByJourneyIdAndId(journeyId, recoverId);
                    if (recoverCaseEntity != null) {
                        Long readNum = recoverCaseEntity.getReadNum();
                        if (readNum == null) {
                            readNum = 1L;
                        } else {
                            readNum += 1;
                        }
                        recoverCaseEntity.setReadNum(readNum);
                        recoverCaseRepository.save(recoverCaseEntity);
                    }
                }
                Integer readNum = journeyEntity.getReadNum();
                if (readNum == null) {
                    journeyEntity.setReadNum(1);
                } else {
                    journeyEntity.setReadNum(++readNum);
                }
                journeyEntity = journeyRepository.save(journeyEntity);
            }
            HealthJourney healthJourney = new HealthJourney();
            // 养生旅程Id
            healthJourney.setJourneyId(journeyEntity.getJourneyId());
            // 养生旅程名称
            healthJourney.setJourneyName(journeyEntity.getJourneyName());
            // 概述
            healthJourney.setSummarize(journeyEntity.getSummarize());
            // 用户ID
            healthJourney.setUserId(journeyEntity.getUserId());
            // 开始时间
            healthJourney.setStartTime(journeyEntity.getStartTime());
            // 结束时间
            healthJourney.setEndTime(journeyEntity.getEndTime());
            // 阅读数
            healthJourney.setReadNum(journeyEntity.getReadNum());
            // 日记天数
            healthJourney.setDays(ChronoUnit.DAYS.between(journeyEntity.getStartTime(), LocalDateTime.now()));
            // 日记数量
            healthJourney.setNoteNum(journeyEntity.getNoteNum());
            // 封面图
            healthJourney.setCoverImage(platformUtils.getImageInfo(journeyEntity.getCoverImage(), domainPort));
            // 获取养生旅程用户信息
            UserEntity user = journeyEntity.getUser();
            if (user != null) {
                healthJourney.setUser(userService.getUserInfo(user));
            }
            // 获取关联的疾病小类
            List<DiseaseClassDetailSimple> diseaseClassDetailSimples = new ArrayList<>();
            List<DiseaseClassDetailEntity> diseaseClassDetailEntities = journeyEntity.getDiseaseClassDetails();
            if (diseaseClassDetailEntities != null && diseaseClassDetailEntities.size() > 0) {
                diseaseClassDetailEntities.forEach(diseaseClassDetailEntity -> {
                    DiseaseClassDetailSimple diseaseClassDetailSimple = new DiseaseClassDetailSimple();
                    // 疾病小类ID
                    diseaseClassDetailSimple.setDiseaseDetailId(diseaseClassDetailEntity.getDiseaseDetailId());
                    // 疾病大类ID
                    diseaseClassDetailSimple.setDiseaseId(diseaseClassDetailEntity.getDiseaseId());
                    // 疾病小类名称
                    diseaseClassDetailSimple.setDiseaseClassDetailName(diseaseClassDetailEntity.getDiseaseDetailName());
                    diseaseClassDetailSimples.add(diseaseClassDetailSimple);
                });
            }
            healthJourney.setDiseaseClassDetails(diseaseClassDetailSimples);
            // 获取关联的体检指标
            List<Integer> normTypeIds = new ArrayList<>();
            List<JourneyNormEntity> journeyNormEntities = journeyEntity.getJourneyNorms();
            List<JourneyNorm> journeyNorms = new ArrayList<>();
            if (journeyNormEntities != null && journeyNormEntities.size() > 0) {
                journeyNormEntities.forEach(journeyNormEntity -> {
                    JourneyNorm journeyNorm = new JourneyNorm();
                    // 旅程体检指标ID
                    journeyNorm.setId(journeyNormEntity.getId());
                    // 体检指标类型ID
                    journeyNorm.setNormTypeId(journeyNormEntity.getNormTypeId());
                    normTypeIds.add(journeyNormEntity.getNormTypeId());
                    // 养生旅程ID
                    journeyNorm.setJourneyId(journeyNormEntity.getJourneyId());
                    // 开始数值1
                    journeyNorm.setStartValue1(journeyNormEntity.getStartValue1());
                    // 开始数值2
                    journeyNorm.setStartValue2(journeyNormEntity.getStartValue2());
                    // 结束数值1
                    journeyNorm.setEndValue1(journeyNormEntity.getEndValue1());
                    // 结束数值2
                    journeyNorm.setEndValue2(journeyNormEntity.getEndValue2());
                    journeyNorms.add(journeyNorm);
                });
            }
            healthJourney.setJourneyNorm(journeyNorms);
            // 获取旅程选择的体检指标
            List<HealthNormTypeEntity> healthNormTypeEntities = healthNormTypeRepository.findByNormTypeIdIn(normTypeIds);
            healthJourney.setHealthNormTypes(healthNormTypeEntities);
            // 获取旅程体检报告
            List<FileInfo> startReports = new ArrayList<>();
            List<FileInfo> endReports = new ArrayList<>();
            List<MedicalReportEntity> medicalReportEntities = journeyEntity.getMedicalReports();
            if (medicalReportEntities != null && medicalReportEntities.size() > 0) {
                medicalReportEntities.forEach(medicalReportEntity -> {
                    FileInfo fileInfo = new FileInfo();
                    // 资源URL
                    if (ossStatus) {
                        fileInfo.setUrl(medicalReportEntity.getOssUrl());
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
            healthJourney.setStartReports(startReports);
            healthJourney.setEndReports(endReports);
            // 获取旅程对应的养生
            List<HealthClass> healthClasses = new ArrayList<>();
            List<HealthEntity> healthEntities = journeyEntity.getHealthClassDetails();
            if (healthEntities != null && healthEntities.size() > 0) {
                healthEntities.forEach(healthEntity -> {
                    HealthClass healthClass = new HealthClass();
                    // 养生ID
                    healthClass.setHealthId(healthEntity.getHealthId());
                    // 编码
                    healthClass.setNumber(healthEntity.getNumber());
                    // 养生大类名称
                    healthClass.setHealthName(healthEntity.getHealthName());
                    // 背景图片
                    ImagesEntity imagesEntity = healthEntity.getBgImage();
                    if (imagesEntity != null) {
                        ImageInfo bgImage = platformUtils.getImageInfo(imagesEntity, domainPort);
                        healthClass.setBgImage(bgImage);
                    }
                    // 内容介绍
                    healthClass.setContent(healthEntity.getContent());
                    // 养生对应的解决方案
                    healthClasses.add(healthClass);
                });
            }
            healthJourney.setHealthClasses(healthClasses);
            // 旅程对应养生成果
            List<HealthResultEntity> healthResultEntities = journeyEntity.getHealthResults();
            if (healthResultEntities != null && healthResultEntities.size() > 0) {
                List<HealthResultSimple> healthResultSimples = healthResultEntities.stream().map(healthResultEntity -> {
                    HealthResultSimple healthResultSimple = new HealthResultSimple();
                    healthResultSimple.setId(healthResultEntity.getId());
                    healthResultSimple.setName(healthResultEntity.getName());
                    healthResultSimple.setNumber(healthResultEntity.getNumber());
                    return healthResultSimple;
                }).collect(Collectors.toList());
                healthJourney.setHealthResult(healthResultSimples);
            }
            // 旅程对应的商品
            List<CommodityEntity> commodityEntities = journeyEntity.getCommodities();
            if (commodityEntities != null && commodityEntities.size() > 0) {
                List<CommoditySimple> commoditySimples = commodityService.getCommoditySimples(commodityEntities);
                healthJourney.setCommodities(commoditySimples);
            }
            return healthJourney;
        }
        return null;
    }

    @Override
    @Transactional
    public JourneyNoteEntity addJourneyNoteEntity(JourneyNoteDTO journeyNoteDTO) throws FlightyThoughtException {
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        // 判断养生旅程是不是该用户的
        Integer journeyId = journeyNoteDTO.getJourneyId();
        JourneyEntity journeyEntity = journeyRepository.findByJourneyIdAndUserId(journeyId, userEntity.getId());
        if (journeyEntity == null) {
            throw new FlightyThoughtException("不能在非自己创建的旅程上增加养生日记");
        }
        // 新增养生日记
        JourneyNoteEntity journeyNoteEntity = new JourneyNoteEntity();
        // 养生旅程ID
        journeyNoteEntity.setJourneyId(journeyNoteDTO.getJourneyId());
        // 内容
        journeyNoteEntity.setContent(journeyNoteDTO.getContent());
        // 封面图片ID
        journeyNoteEntity.setCoverImageId(journeyNoteDTO.getCoverImageId());
        // 是否发布到朋友圈
        journeyNoteEntity.setCircleOfFriends(journeyNoteDTO.getCircleOfFriends());
        // 创建者
        journeyNoteEntity.setCreateUserName(userEntity.getId() + "");
        // 养生日记时间
        journeyNoteEntity.setNoteDate(LocalDateTime.now());
        // 保存养生日记
        journeyNoteEntity = journeyNoteRepository.save(journeyNoteEntity);
        Integer noteId = journeyNoteEntity.getId();
        // 新增图片
        List<Integer> imageIds = journeyNoteDTO.getImageIds();
        if (imageIds != null && imageIds.size() > 0) {
            List<JourneyNoteToImageEntity> imageEntities = new ArrayList<>();
            imageIds.forEach(imageId -> {
                JourneyNoteToImageEntity journeyNoteToImageEntity = new JourneyNoteToImageEntity();
                journeyNoteToImageEntity.setImageId(imageId);
                journeyNoteToImageEntity.setNoteId(noteId);
                imageEntities.add(journeyNoteToImageEntity);
            });
            journeyNoteToImageRepository.saveAll(imageEntities);
        }
        // 新增日记指标
        List<JourneyNoteNormDTO> journeyNoteNormDTOS = journeyNoteDTO.getNorms();
        if (journeyNoteNormDTOS != null && journeyNoteNormDTOS.size() > 0) {
            List<JourneyNoteNormEntity> journeyNoteNormEntities = new ArrayList<>();
            journeyNoteNormDTOS.forEach(journeyNoteNormDTO -> {
                JourneyNoteNormEntity journeyNoteNormEntity = new JourneyNoteNormEntity();
                // 指标ID
                journeyNoteNormEntity.setNormTypeId(journeyNoteNormDTO.getNormTypeId());
                // 日记ID
                journeyNoteNormEntity.setNoteId(noteId);
                // 数值1
                journeyNoteNormEntity.setValue1(journeyNoteNormDTO.getValue1());
                // 数值2
                journeyNoteNormEntity.setValue2(journeyNoteNormDTO.getValue2());
                // 创建者
                journeyNoteNormEntity.setCreateUserName(userEntity.getId() + "");
                journeyNoteNormEntities.add(journeyNoteNormEntity);
            });
            journeyNoteNormRepository.saveAll(journeyNoteNormEntities);
        }
        // 养生旅程日记+1
        journeyEntity.setNoteNum(journeyEntity.getNoteNum() + 1);
        journeyRepository.save(journeyEntity);
        return journeyNoteEntity;
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
        return null;
    }

    @Override
    @Transactional
    public HealthJourney endHealthJourney(HealthJourneyEndDTO healthJourneyEndDTO) throws FlightyThoughtException {
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        // 判断结束的养生旅程是不是该用户创建的
        JourneyEntity journeyEntity = journeyRepository.findByJourneyIdAndUserId(healthJourneyEndDTO.getJourneyId(), userEntity.getId());
        if (journeyEntity == null) {
            throw new FlightyThoughtException("要修改的养生旅程非当前登录用户所创建的，无权限操作！");
        }
        if (journeyEntity.getFinished() != null && journeyEntity.getFinished()) {
            throw new FlightyThoughtException("该养生旅程已结束！");
        }
        // 体检指标
        List<HealthNormTypeDTO> healthNormTypeDTOS = healthJourneyEndDTO.getHealthNormTypes();
        if (healthNormTypeDTOS != null || healthNormTypeDTOS.size() > 0) {
            for (HealthNormTypeDTO healthNormTypeDTO : healthNormTypeDTOS) {
                JourneyNormEntity journeyNormEntity = journeyNormRepository.findByJourneyIdAndNormTypeId(healthJourneyEndDTO.getJourneyId(), healthNormTypeDTO.getNormTypeId());
                if (journeyNormEntity == null) {
                    journeyNormEntity = new JourneyNormEntity();
                    // 体检指标类型ID
                    journeyNormEntity.setNormTypeId(healthNormTypeDTO.getNormTypeId());
                    // 养生旅程ID
                    journeyNormEntity.setJourneyId(journeyEntity.getJourneyId());
                    // 结束数值
                    journeyNormEntity.setEndValue1(healthNormTypeDTO.getValue1());
                    journeyNormEntity.setEndValue2(healthNormTypeDTO.getValue2());
                    // 创建者
                    journeyNormEntity.setCreateUserName(userEntity.getId() + "");
                    journeyNormRepository.save(journeyNormEntity);
                } else {
                    journeyNormEntity.setEndValue1(healthNormTypeDTO.getValue1());
                    journeyNormEntity.setEndValue2(healthNormTypeDTO.getValue2());
                    journeyNormEntity.setUpdateUserName(userEntity.getId() + "");
                    journeyNormEntity.setUpdateTime(LocalDateTime.now());
                    journeyNormRepository.save(journeyNormEntity);
                }
            }
        } else {
            throw new FlightyThoughtException("请填写提交养生旅程结束时的体检指标");
        }
        // 修改结束养生旅程
        // 养生旅程标题
        journeyEntity.setJourneyName(healthJourneyEndDTO.getJourneyName());
        // 养生旅程概述
        journeyEntity.setSummarize(healthJourneyEndDTO.getSummarize());
        // 养生旅程结束时间
        journeyEntity.setEndTime(LocalDateTime.now());
        // 养生旅程结束标识
        journeyEntity.setFinished(true);
        // 修改者
        journeyEntity.setUpdateUserName(userEntity.getId() + "");
        // 修改时间
        journeyEntity.setUpdateTime(LocalDateTime.now());
        // 保存修改的养生旅程
        journeyRepository.save(journeyEntity);
        // 关联疾病小类以及对应的养生成果
        List<DiseaseAndHealthResult> diseaseAndHealthResults = healthJourneyEndDTO.getDiseaseAndHealthResults();
        if (diseaseAndHealthResults != null && diseaseAndHealthResults.size() > 0) {
            // 删除之前绑定的疾病小类
            journeyDiseaseRepository.deleteAllByJourneyId(journeyEntity.getJourneyId());
            List<JourneyDiseaseEntity> journeyDiseaseEntities = journeyDiseaseRepository.findByJourneyId(journeyEntity.getJourneyId());
            if (journeyDiseaseEntities == null || journeyDiseaseEntities.size() == 0) {
                LOG.info("养生旅程ID：{}, 对应的疾病小类删除成功", journeyEntity.getJourneyId());
            }
            List<JourneyDiseaseEntity> journeyDiseaseEntityList = new ArrayList<>();
            diseaseAndHealthResults.forEach(diseaseAndHealthResult -> {
                JourneyDiseaseEntity journeyDiseaseEntity = new JourneyDiseaseEntity();
                // 旅程ID
                journeyDiseaseEntity.setJourneyId(journeyEntity.getJourneyId());
                // 疾病ID
                journeyDiseaseEntity.setDiseaseDetailId(diseaseAndHealthResult.getDiseaseDetailId());
                // 养生结果ID
                journeyDiseaseEntity.setHealthResultId(diseaseAndHealthResult.getHealthResultId());
                journeyDiseaseEntityList.add(journeyDiseaseEntity);
            });
            journeyDiseaseRepository.saveAll(journeyDiseaseEntityList);
        }
        // 参加课程
        List<Integer> courseIds = healthJourneyEndDTO.getCourseIds();
        if (courseIds != null && courseIds.size() > 0) {
            List<JourneyToCourseEntity> journeyToCourseEntities = new ArrayList<>();
            courseIds.forEach(courseId -> {
                // 判断是否是当前用户报名课程
                /*
                UserFollowCourseEntity userFollowCourseEntity = userFollowCourseRepository.findByUserIdAndCourseId(userEntity.getId(), courseId);
                if (userFollowCourseEntity == null) {
                    throw new FlightyThoughtException("所选课程用户未报名参加");
                }
                */
                JourneyToCourseEntity journeyToCourseEntity = new JourneyToCourseEntity();
                // 旅程ID
                journeyToCourseEntity.setJourneyId(journeyEntity.getJourneyId());
                // 课程ID
                journeyToCourseEntity.setCourseId(courseId);
                journeyToCourseEntities.add(journeyToCourseEntity);
            });
            journeyToCourseRepository.saveAll(journeyToCourseEntities);
        }
        // 获取体检报告
        List<FileImageDTO> files = healthJourneyEndDTO.getFiles();
        if (files != null && files.size() > 0) {
            List<JourneyToReportEntity> journeyToReportEntities = new ArrayList<>();
            for (FileImageDTO fileImageDTO : files) {
                // 判断体检报告是不是结束旅程上传报告
                MedicalReportEntity medicalReportEntity = medicalReportRepository.findById(fileImageDTO.getId());
                if (medicalReportEntity == null) {
                    throw new FlightyThoughtException("id：" + fileImageDTO.getId() + "，找不到对应的体检报告");
                } else {
                    if (medicalReportEntity.getType() != 1) {
                        throw new FlightyThoughtException("id：" + fileImageDTO.getId() + "，体检报告类型非结束养生旅程上传类型，不能绑定");
                    }
                    if (!medicalReportEntity.getCreateUserName().equals(userEntity.getId() + "")) {
                        throw new FlightyThoughtException("id：" + fileImageDTO.getId() + "，体检报告非自己上传不能绑定自身的养生旅程！");
                    }
                }
                JourneyToReportEntity journeyToReportEntity = new JourneyToReportEntity();
                journeyToReportEntity.setJourneyId(journeyEntity.getJourneyId());
                journeyToReportEntity.setReportId(fileImageDTO.getId());
                journeyToReportEntity.setStartFlag(false);
                journeyToReportEntities.add(journeyToReportEntity);
            }
            journeyToReportRepository.saveAll(journeyToReportEntities);
        }
        List<Integer> healthIds = healthJourneyEndDTO.getHealthIds();
        if (healthIds != null && healthIds.size() > 0) {
            List<JourneyHealthEntity> journeyHealthEntities = new ArrayList<>();
            healthIds.forEach(healthId -> {
                JourneyHealthEntity journeyHealthEntity = new JourneyHealthEntity();
                // 疾病小类ID
                journeyHealthEntity.setJourneyId(journeyEntity.getJourneyId());
                // 养生旅程ID
                journeyHealthEntity.setHealthId(healthId);
                journeyHealthEntities.add(journeyHealthEntity);
            });
            journeyHealthRepository.saveAll(journeyHealthEntities);
        }
        // 商品集合
        List<Integer> commodityIds = healthJourneyEndDTO.getCommodityIds();
        if (commodityIds != null && commodityIds.size() > 0) {
            List<JourneyToCommodityEntity> journeyToCommodityEntities = commodityIds.stream().map(commodityId -> {
                JourneyToCommodityEntity journeyToCommodityEntity = new JourneyToCommodityEntity();
                journeyToCommodityEntity.setCommodityId(commodityId);
                journeyToCommodityEntity.setJourneyId(journeyEntity.getJourneyId());
                return journeyToCommodityEntity;
            }).collect(Collectors.toList());
            journeyToCommodityRepository.saveAll(journeyToCommodityEntities);
        }
        return getHealthJourney(journeyEntity.getJourneyId(), null);
    }

    @Override
    public Page<HealthResultEntity> getHealthResultList(PageFilterDTO pageFilterDTO) {
        PageRequest pageRequest;
        List<HealthResultEntity> healthResultEntities;
        Page<HealthResultEntity> result;
        Integer pageSize = pageFilterDTO.getPageSize();
        Integer pageNumber = pageFilterDTO.getPageNumber();
        if (pageSize == null || pageSize == 0 || pageNumber == null || pageNumber == 0) {
            healthResultEntities = healthResultRepository.findAll();
            pageRequest = PageRequest.of(0, healthResultEntities.size() + 1);
            result = new PageImpl<>(healthResultEntities, pageRequest, healthResultEntities.size());
        } else {
            pageRequest = PageRequest.of(pageSize, pageNumber);
            result = healthResultRepository.findAll(pageRequest);
        }
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteJourneyById(Integer journeyId) {
        // 获取养生旅程
        String filePath = platformUtils.getFilePath();
        // 获取用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        JourneyEntity journeyEntity = journeyRepository.findByJourneyIdAndUserId(journeyId, userEntity.getId());
        if (journeyEntity != null) {
            // 获取养生旅程对应的体检报告
            List<MedicalReportEntity> medicalReportEntities = journeyEntity.getMedicalReports();
            if (medicalReportEntities != null && medicalReportEntities.size() > 0) {
                medicalReportEntities.forEach(medicalReportEntity -> {
                    String path = filePath + medicalReportEntity.getPath();
                    File file = new File(path);
                    if (file.exists()) {
                        boolean isDelete = file.delete();
                        if (isDelete) {
                            LOG.info("ID:{},体检报告已删除", medicalReportEntity.getId());
                        }
                    }
                });
            }
            // 获取养生旅程对应的日记
            List<JourneyNoteEntity> journeyNoteEntities = journeyNoteRepository.findByJourneyId(journeyId);
            if (journeyNoteEntities.size() > 0) {
                journeyNoteEntities.forEach(journeyNoteEntity -> {
                    // 删除封面图片
                    ImagesEntity imagesEntity = journeyNoteEntity.getCoverImage();
                    if (imagesEntity != null) {
                        String path = filePath + imagesEntity.getPath();
                        File file = new File(path);
                        if (file.exists()) {
                            boolean isDelete = file.delete();
                            if (isDelete) {
                                LOG.info("JourneyNoteId:{},ImageId:{},封面图片已删除", journeyNoteEntity.getId(), imagesEntity.getId());
                            }
                        }
                    }
                    // 日记配图删除
                });
            }
        } else {
            throw new FlightyThoughtException("非自身创建的养生旅程不能删除");
        }
    }
}
