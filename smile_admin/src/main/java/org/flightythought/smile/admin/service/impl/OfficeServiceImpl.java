package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.OfficeImageRepository;
import org.flightythought.smile.admin.database.repository.OfficeRepository;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.flightythought.smile.admin.dto.OfficeDTO;
import org.flightythought.smile.admin.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OfficeServiceImpl implements OfficeService {

    @Autowired
    private OfficeRepository officeRepository;
    @Autowired
    private OfficeImageRepository officeImageRepository;
    @Autowired
    private SysParameterRepository sysParameterRepository;
    @Value("${image-url}")
    private String imageRequest;
    @Value("${server.servlet.context-path}")
    private String contentPath;

    @Transactional
    @Override
    public OfficeEntity save(OfficeDTO officeDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        OfficeEntity officeEntity = new OfficeEntity();

        officeEntity.setName(officeDTO.getName());
        officeEntity.setContactName(officeDTO.getContactName());
        officeEntity.setPhone(officeDTO.getPhone());
        officeEntity.setAddress(officeDTO.getAddress());
        officeEntity.setNumber(officeDTO.getNumber());
        officeEntity.setCreateUserName(sysUserEntity.getUserName());
        OfficeEntity office = officeRepository.save(officeEntity);
        //保存机构图片
        List<OfficeImageEntity> officeImageEntities = officeDTO.getOfficeImages()
                .stream()
                .map(imageDTO -> {
                    OfficeImageEntity officeImageEntity = new OfficeImageEntity();
                    officeImageEntity.setImageId(imageDTO.getImageId());
                    officeImageEntity.setOfficeId(office.getOfficeId());
                    return officeImageEntity;
                }).collect(Collectors.toList());
        officeImageRepository.saveAll(officeImageEntities);
        return office;
    }

    @Transactional
    @Override
    public OfficeEntity modify(OfficeDTO officeDTO, HttpSession session) {
        SysUserEntity sysUserEntity = (SysUserEntity) session.getAttribute(GlobalConstant.USER_SESSION);
        //查找机构对应的图片
        List<OfficeImageEntity> imageEntities = officeImageRepository.findByOfficeId(officeDTO.getOfficeId());
        imageEntities.forEach(officeImageEntity -> {
            //删除
            officeImageRepository.deleteById(officeImageEntity.getId());
        });

        OfficeEntity officeEntity = new OfficeEntity();
        officeEntity.setOfficeId(officeDTO.getOfficeId());
        officeEntity.setName(officeDTO.getName());
        officeEntity.setContactName(officeDTO.getContactName());
        officeEntity.setPhone(officeDTO.getPhone());
        officeEntity.setAddress(officeDTO.getAddress());
        officeEntity.setNumber(officeDTO.getNumber());
        officeEntity.setUpdateUserName(sysUserEntity.getUserName());
        OfficeEntity office = officeRepository.save(officeEntity);
        //保存机构图片
        List<OfficeImageEntity> officeImageEntities = officeDTO.getOfficeImages()
                .stream()
                .map(imageDTO -> {
                    OfficeImageEntity officeImageEntity = new OfficeImageEntity();
                    officeImageEntity.setImageId(imageDTO.getImageId());
                    officeImageEntity.setOfficeId(office.getOfficeId());
                    return officeImageEntity;
                }).collect(Collectors.toList());
        officeImageRepository.saveAll(officeImageEntities);
        return office;
    }

    @Transactional
    @Override
    public void deleteById(Long officeId, HttpSession session) {
        officeRepository.deleteById(officeId);
    }

    @Override
    public Page<OfficeEntity> findAllOffice(Map<String, String> params, HttpSession session) {
        String pageNumber = params.get("pageNumber");
        String pageSize = params.get("pageSize");
        String name = params.get("name");
        String number = params.get("number");

        OfficeEntity officeEntity = new OfficeEntity();

        if (StringUtils.isNotBlank(number)) {
            officeEntity.setNumber(number);
        }
        if (StringUtils.isNotBlank(name)) {
            officeEntity.setName(name);
        }
        if (StringUtils.isBlank(pageNumber)) {
            pageNumber = "1";
        }
        if (StringUtils.isBlank(pageSize)) {
            pageSize = "10";
        }
        PageRequest pageRequest = PageRequest.of(Integer.valueOf(pageNumber) - 1, Integer.valueOf(pageSize));
        return officeRepository.findAll(Example.of(officeEntity), pageRequest);
    }

    @Override
    public OfficeEntity findOffice(Long officeId, HttpSession session) {
        SysParameterEntity sysParameterEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = sysParameterEntity.getParameterValue();
        return officeRepository.findById(officeId)
                .map(officeEntity -> {
                    List<ImagesEntity> imagesEntities = officeEntity.getOfficeImages()
                            .stream()
                            .map(imagesEntity -> {
                                String imageUrl = domainPort + contentPath + imageRequest + imagesEntity.getPath();
                                imagesEntity.setPath(imageUrl.replace("\\", "/"));
                                return imagesEntity;
                            }).collect(Collectors.toList());
                    officeEntity.setOfficeImages(imagesEntities);
                    return officeEntity;
                }).orElse(null);
    }
}
