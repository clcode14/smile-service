package org.flightythought.smile.appserver.service.impl;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.*;
import org.flightythought.smile.appserver.database.repository.*;
import org.flightythought.smile.appserver.dto.FileImageDTO;
import org.flightythought.smile.appserver.dto.HealthJourneyStartDTO;
import org.flightythought.smile.appserver.dto.HealthNormTypeDTO;
import org.flightythought.smile.appserver.service.JourneyHealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
        return journeyEntity;
    }
}
