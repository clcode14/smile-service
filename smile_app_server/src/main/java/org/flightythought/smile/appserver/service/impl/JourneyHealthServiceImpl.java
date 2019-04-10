package org.flightythought.smile.appserver.service.impl;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.flightythought.smile.appserver.bean.*;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.*;
import org.flightythought.smile.appserver.dto.*;
import org.flightythought.smile.appserver.service.JourneyHealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Value("${static-url}")
    private String staticUrl;
    @Value("${server.servlet.context-path}")
    private String contentPath;

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
        // 获取系统参数
        SysParameterEntity sysParameterEntity = sysParameterRepository.getFilePathParam();
        // 获取当前登陆用户
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        SysParameterEntity domainPortEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = domainPortEntity.getParameterValue();
        String filePath, userPath;
        if (sysParameterEntity == null) {
            throw new FlightyThoughtException("请设置上传文件路径系统参数");
        } else {
            filePath = sysParameterEntity.getParameterValue();
            if (!"".equals(fileType)) {
                userPath = File.separator + fileType + File.separator + userEntity.getId();
            } else {
                userPath = File.separator + userEntity.getId();
            }
            File file = new File(filePath + userPath);
            if (!file.exists()) {
                file.mkdirs();
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
                String fileUrl = domainPort + contentPath + staticUrl + medicalReportEntity.getPath();
                fileInfo.setUrl(fileUrl.replace("\\", "/"));
                fileInfos.add(fileInfo);
            });
            return fileInfos;
        }
        return null;
    }

    @Override
    @Transactional
    public JourneyEntity startHealthJourney(HealthJourneyStartDTO healthJourneyStartDTO) {
        // 获取养生旅程数据
        JourneyEntity journeyEntity = new JourneyEntity();
        // 养生旅程名称
        journeyEntity.setJourneyName(healthJourneyStartDTO.getJourneyName());
        // 获得当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        journeyEntity.setUserId(userId);
        // 设置开始时间
        journeyEntity.setStartTime(LocalDateTime.now());
        // 创建者
        journeyEntity.setCreateUserName(userId + "");
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
        List<FileImageDTO> files = healthJourneyStartDTO.getFiles();
        if (files != null && files.size() > 0) {
            List<JourneyToReportEntity> journeyToReportEntities = new ArrayList<>();
            for (FileImageDTO fileImageDTO : files) {
                JourneyToReportEntity journeyToReportEntity = new JourneyToReportEntity();
                journeyToReportEntity.setJourneyId(journeyEntity.getJourneyId());
                journeyToReportEntity.setReportId(fileImageDTO.getId());
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
    public JourneyEntity updateHealthJourney(HealthJourneyStartDTO healthJourneyStartDTO) throws FlightyThoughtException {
        Integer journeyId = healthJourneyStartDTO.getJourneyId();
        // 获取当前登录用户
        UserEntity userEntity = platformUtils.getCurrentLoginUser();
        Long userId = userEntity.getId();
        if (journeyId == null) {
            throw new FlightyThoughtException("修改接口需传递journeyId");
        } else {
            JourneyEntity journeyEntity = journeyRepository.findByJourneyIdAndUserId(journeyId, userId);
            // 旅程名称
            journeyEntity.setJourneyName(healthJourneyStartDTO.getJourneyName());
            // 概述
            journeyEntity.setSummarize(healthJourneyStartDTO.getSummarize());
            // 更新
            journeyEntity = journeyRepository.save(journeyEntity);
            return journeyEntity;
        }
    }

    @Override
    public Page<HealthJourneySimple> getHealthJourney(Long userId, PageFilterDTO pageFilterDTO) {
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
            journeyEntities = journeyRepository.findByUserId(userId);
            pageRequest = PageRequest.of(0, journeyEntities.size());
            total = (long) journeyEntities.size();
        } else {
            pageRequest = PageRequest.of(pageNumber - 1, pageSize);
            Page<JourneyEntity> journeyEntityPage = journeyRepository.findByUserId(userId, pageRequest);
            journeyEntities = journeyEntityPage.getContent();
            total = journeyEntityPage.getTotalElements();
        }
        List<HealthJourneySimple> result = new ArrayList<>();
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
                result.add(healthJourneySimple);
            });
            return new PageImpl<>(result, pageRequest, total);
        }
        return null;
    }

    @Override
    @Transactional
    public HealthJourney getHealthJourney(Integer journeyId) {
        // 获取养生旅程
        JourneyEntity journeyEntity = journeyRepository.findByJourneyId(journeyId);
        String domainPort = platformUtils.getDomainPort();
        if (journeyEntity != null) {
            // 判断是否增加阅读数量
            UserEntity userEntity = platformUtils.getCurrentLoginUser();
            if (journeyEntity.getUserId().longValue() != userEntity.getId().longValue()) {
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
                    String url = platformUtils.getImageUrlByPath(medicalReportEntity.getPath(), domainPort);
                    fileInfo.setUrl(url);
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
                    String coverImageUrl = platformUtils.getImageUrlByPath(coverImage.getPath(), domainPort);
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
                        ImageInfo imageInfo = new ImageInfo();
                        imageInfo.setName(imagesEntity.getFileName());
                        imageInfo.setSize(imagesEntity.getSize());
                        imageInfo.setId(imagesEntity.getId());
                        imageInfo.setUrl(platformUtils.getImageUrlByPath(imagesEntity.getPath(), domainPort));
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
                    });
                    journeyNote.setJourneyNoteNorms(journeyNorms);
                }
                journeyNotes.add(journeyNote);
            });
            return new PageImpl<>(journeyNotes, pageRequest, total);
        }
        return null;
    }
}
