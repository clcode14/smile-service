package org.flightythought.smile.admin.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.admin.common.PlatformUtils;
import org.flightythought.smile.admin.database.entity.HealthIntroductionEntity;
import org.flightythought.smile.admin.database.entity.SysUserEntity;
import org.flightythought.smile.admin.database.repository.HealthIntroductionRepository;
import org.flightythought.smile.admin.service.HealthIntroductionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

/**
 * @author cl47872
 * @version $Id: EncourageServiceImpl.java, v 0.1 Jun 8, 2019 10:11:51 PM cl47872 Exp $
 */
@Service
public class HealthIntroductionServiceImpl implements HealthIntroductionService {
    @Autowired
    private PlatformUtils platformUtils;
    @Autowired
    private HealthIntroductionRepository healthIntroductionRepository;
    @Value("html")
    private String html;

    @Override
    public void saveIntroduction(String content) {
        // 登陆用户
//        SysUserEntity userEntity = platformUtils.getCurrentLoginUser();
        List<HealthIntroductionEntity> entities = healthIntroductionRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        if (entities != null && entities.size() > 0) {
            HealthIntroductionEntity entity = entities.get(0);
            if (StringUtils.isNotBlank(content)) {
                entity.setContent(html + content);
            }
            entity.setUpdateUserName("system");
            healthIntroductionRepository.save(entity);
        } else {
            HealthIntroductionEntity healthIntroductionEntity = new HealthIntroductionEntity();
            if (StringUtils.isNotBlank(content)) {
                healthIntroductionEntity.setContent(content);
            }
            // 创建者
            healthIntroductionEntity.setCreateUserName("system");
            // 保存
            healthIntroductionRepository.save(healthIntroductionEntity);
        }

    }

    @Override
    public String getIntroduction() {
        List<HealthIntroductionEntity> entities = healthIntroductionRepository.findAll(new Sort(Sort.Direction.DESC, "id"));
        if (entities != null && entities.size() > 0) {
            return entities.get(0).getContent();
        } else {
            return "";
        }
    }

}
