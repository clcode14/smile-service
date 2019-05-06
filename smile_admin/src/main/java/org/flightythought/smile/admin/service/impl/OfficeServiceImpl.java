package org.flightythought.smile.admin.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.bean.ImageInfo;
import org.flightythought.smile.admin.bean.OfficeInfo;
import org.flightythought.smile.admin.common.GlobalConstant;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.*;
import org.flightythought.smile.admin.database.repository.OfficeImageRepository;
import org.flightythought.smile.admin.database.repository.OfficeRepository;
import org.flightythought.smile.admin.database.repository.SysParameterRepository;
import org.flightythought.smile.admin.dto.OfficeDTO;
import org.flightythought.smile.admin.service.OfficeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    @Autowired
    private PlatformUtils platformUtils;

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
        officeImageRepository.deleteAll(imageEntities);

        OfficeEntity officeEntity = officeRepository.getOne(officeDTO.getOfficeId());
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
    public Page<OfficeInfo> findAllOffice(Map<String, String> params, HttpSession session) {
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
        Page<OfficeEntity> officeEntities = officeRepository.findAll(Example.of(officeEntity), pageRequest);
        return getOfficeInfo(officeEntities);
    }

    @Override
    public OfficeInfo findOffice(Long officeId, HttpSession session) {
        SysParameterEntity sysParameterEntity = sysParameterRepository.getDomainPortParam();
        String domainPort = sysParameterEntity.getParameterValue();
        return officeRepository.findById(officeId)
                .map(officeEntity -> {
                    List<ImageInfo> imageInfos = officeEntity.getOfficeImages()
                            .stream()
                            .map(imagesEntity -> platformUtils.getImageInfo(imagesEntity, domainPort)).collect(Collectors.toList());
                    OfficeInfo officeInfo = new OfficeInfo();
                    // 主键ID
                    officeInfo.setOfficeId(officeEntity.getOfficeId());
                    // 机构名称
                    officeInfo.setName(officeEntity.getName());
                    // 机构编号
                    officeInfo.setNumber(officeEntity.getNumber());
                    // 机构联系人
                    officeInfo.setContactName(officeEntity.getContactName());
                    // 手机
                    officeInfo.setPhone(officeEntity.getPhone());
                    // 机构图片
                    officeInfo.setOfficeImages(imageInfos);
                    // 机构地址
                    officeInfo.setAddress(officeEntity.getAddress());
                    // 机构描述
                    officeInfo.setDescription(officeEntity.getDescription());
                    return officeInfo;
                }).orElse(null);
    }

    @Override
    public Page<OfficeInfo> getOfficeInfo(Page<OfficeEntity> officeEntities) {
        if (officeEntities != null && officeEntities.getContent().size() > 0) {
            List<OfficeEntity> officeEntityList = officeEntities.getContent();
            long total = officeEntities.getTotalElements();
            Pageable pageable = officeEntities.getPageable();
            String domain = platformUtils.getDomainPort();
            List<OfficeInfo> officeInfos = new ArrayList<>();
            officeEntityList.forEach(officeEntity -> {
                OfficeInfo officeInfo = new OfficeInfo();
                // 主键ID
                officeInfo.setOfficeId(officeEntity.getOfficeId());
                // 机构名称
                officeInfo.setName(officeEntity.getName());
                // 机构编号
                officeInfo.setNumber(officeEntity.getNumber());
                // 机构联系人
                officeInfo.setContactName(officeEntity.getContactName());
                // 手机
                officeInfo.setPhone(officeEntity.getPhone());
                // 机构图片
                List<ImagesEntity> imagesEntities = officeEntity.getOfficeImages();
                if (imagesEntities != null) {
                    List<ImageInfo> imageInfos = new ArrayList<>();
                    imagesEntities.forEach(imagesEntity -> {
                        ImageInfo imageInfo = platformUtils.getImageInfo(imagesEntity, domain);
                        imageInfos.add(imageInfo);
                    });
                    officeInfo.setOfficeImages(imageInfos);
                }
                // 地址
                officeInfo.setAddress(officeEntity.getAddress());
                // 描述
                officeInfo.setDescription(officeEntity.getDescription());
                officeInfos.add(officeInfo);
            });
            return new PageImpl<>(officeInfos, pageable, total);
        }
        return null;
    }
}
