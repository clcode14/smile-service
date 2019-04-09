package org.flightythought.smile.appserver.service.impl;

import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.flightythought.smile.appserver.bean.FileInfo;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.HealthNormTypeEntity;
import org.flightythought.smile.appserver.database.entity.MedicalReportEntity;
import org.flightythought.smile.appserver.database.entity.SysParameterEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.HealthNormTypeRepository;
import org.flightythought.smile.appserver.database.repository.MedicalReportRepository;
import org.flightythought.smile.appserver.database.repository.SysParameterRepository;
import org.flightythought.smile.appserver.service.JourneyHealthService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
}
