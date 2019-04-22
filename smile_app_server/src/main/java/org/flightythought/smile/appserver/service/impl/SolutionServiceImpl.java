package org.flightythought.smile.appserver.service.impl;

import org.flightythought.smile.appserver.bean.SolutionPage;
import org.flightythought.smile.appserver.bean.SolutionSimple;
import org.flightythought.smile.appserver.common.exception.FlightyThoughtException;
import org.flightythought.smile.appserver.common.utils.PlatformUtils;
import org.flightythought.smile.appserver.database.entity.ImagesEntity;
import org.flightythought.smile.appserver.database.entity.SolutionEntity;
import org.flightythought.smile.appserver.database.entity.UserEntity;
import org.flightythought.smile.appserver.database.repository.SolutionRepository;
import org.flightythought.smile.appserver.database.repository.UserFollowSolutionRepository;
import org.flightythought.smile.appserver.dto.HealthOrDiseaseByIdQueryDTO;
import org.flightythought.smile.appserver.dto.SolutionQueryDTO;
import org.flightythought.smile.appserver.service.SolutionService;
import org.hibernate.query.internal.NativeQueryImpl;
import org.hibernate.type.IntegerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class SolutionServiceImpl implements SolutionService {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private SolutionRepository solutionRepository;
    @Autowired
    private UserFollowSolutionRepository userFollowSolutionRepository;
    @Autowired
    private PlatformUtils platformUtils;

    @Override
    @Transactional
    public Page<SolutionSimple> getSolutionSimples(SolutionQueryDTO solutionQueryDTO) {
        Page<SolutionEntity> solutionEntities = getSolutions(solutionQueryDTO);
        List<SolutionSimple> solutionSimples = new ArrayList<>();
        String domainPort = platformUtils.getDomainPort();
        solutionEntities.forEach(solutionEntity -> {
            SolutionSimple solutionSimple = new SolutionSimple();
            List<String> imageUrls = new ArrayList<>();
            List<ImagesEntity> imagesEntities = solutionEntity.getImages();
            imagesEntities.forEach(imagesEntity -> {
                String imageUrl = platformUtils.getStaticUrlByPath(imagesEntity.getPath(), domainPort);
                imageUrls.add(imageUrl);
            });
            // 配图
            solutionSimple.setImageUrls(imageUrls);
            // 解决方案标题
            solutionSimple.setTitle(solutionEntity.getTitle());
            // 康复人数
            solutionSimple.setRecoverNumber(solutionEntity.getRecoverNumber());
            // 解决方案ID
            solutionSimple.setSolutionId(solutionEntity.getId());
            solutionSimples.add(solutionSimple);
        });
        PageImpl<SolutionSimple> result = new PageImpl<>(solutionSimples, solutionEntities.getPageable(), solutionEntities.getTotalElements());
        return result;
    }

    @Override
    @Transactional
    public Page<SolutionEntity> getSolutions(SolutionQueryDTO solutionQueryDTO) {
        // 组装SQL语句
        String totalSql = "SELECT COUNT(*) as total FROM (";
        String sql = "SELECT DISTINCT\n" +
                "  s.id " +
                "FROM\n" +
                "  `tb_disease_class_detail` dcd\n" +
                "  INNER JOIN `tb_disease_reason` dr\n" +
                "    ON dcd.`disease_detail_id` = dr.`disease_detail_id`\n" +
                "  INNER JOIN `tb_disease_reason_to_solution` drts\n" +
                "    ON dr.`id` = drts.`disease_reason_id`\n" +
                "  INNER JOIN `tb_solution` s\n" +
                "    ON drts.`solution_id` = s.`id`\n" +
                "WHERE 1 = 1";
        Integer diseaseDetailId = solutionQueryDTO.getDiseaseDetailId();
        if (diseaseDetailId != null) {
            sql += " AND dcd.`disease_detail_id` = " + diseaseDetailId;
        }
        Integer diseaseReasonId = solutionQueryDTO.getDiseaseReasonId();
        if (diseaseReasonId != null) {
            sql += " AND dr.`id` = " + diseaseReasonId;
        }
        totalSql += sql + ") T";
        // 获取ToTal总数
        Integer total = (Integer) entityManager.createNativeQuery(totalSql).unwrap(NativeQueryImpl.class).addScalar("total", IntegerType.INSTANCE).getSingleResult();
        if (total == 0) {
            throw new FlightyThoughtException("没有查询到对应的解决方案");
        }
        // 是否存在分页查询
        Integer pageNumber = solutionQueryDTO.getPageNumber();
        Integer pageSize = solutionQueryDTO.getPageSize();
        Pageable pageable;
        if (pageNumber != null && pageNumber > 0 && pageSize != null && pageSize > 0) {
            sql += " LIMIT " + (pageNumber - 1) * pageSize + "," + pageSize;
            pageable = PageRequest.of(pageNumber - 1, pageSize);
        } else {
            pageable = PageRequest.of(0, total);
        }
        // 查询结果
        List<Integer> solutionIds = entityManager.createNativeQuery(sql)
                .unwrap(NativeQueryImpl.class)
                .addScalar("id", IntegerType.INSTANCE)
                .list();
        // 获取全部SolutionEntities
        List<SolutionEntity> solutionEntities = solutionRepository.findByIdIn(solutionIds);
        PageImpl<SolutionEntity> result = new PageImpl<>(solutionEntities, pageable, total);
        return result;
    }

    @Override
    @Transactional
    public SolutionPage getSolutionPage(Integer solutionId) {
        // 根据解决方案ID获取解决方案
        SolutionEntity solutionEntity = solutionRepository.findById(solutionId);
        // 获取当前登陆用户
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // 判断用户有无收藏该解决方案
        boolean collection = userFollowSolutionRepository.findByUserIdAndSolutionId(userEntity.getId(), solutionEntity.getId()) != null;
        SolutionPage solutionPage = new SolutionPage();
        // 获取配图
        List<String> imageUrls = new ArrayList<>();
        List<ImagesEntity> imagesEntities = solutionEntity.getImages();
        String domainPort = platformUtils.getDomainPort();
        imagesEntities.forEach(imagesEntity -> {
            String imageUrl = platformUtils.getStaticUrlByPath(imagesEntity.getPath(), domainPort);
            imageUrls.add(imageUrl);
        });
        // 设置配图
        solutionPage.setImageUrls(imageUrls);
        // 是否收藏
        solutionPage.setCollection(collection);
        // 解决方案标题
        solutionPage.setTitle(solutionEntity.getTitle());
        // 解决方案内容
        solutionPage.setContent(solutionEntity.getContent());
        return solutionPage;
    }

    @Override
    public Page<SolutionSimple> getSolutionSimples(HealthOrDiseaseByIdQueryDTO querySolutionDTO) throws FlightyThoughtException {
        Page<SolutionEntity> solutionEntities = getSolutions(querySolutionDTO);
        List<SolutionSimple> result = new ArrayList<>();
        String domainPort = platformUtils.getDomainPort();
        solutionEntities.forEach(solutionEntity -> {
            SolutionSimple solutionSimple = new SolutionSimple();
            // 解决方案对应的图片URL
            List<ImagesEntity> imagesEntities = solutionEntity.getImages();
            if (imagesEntities != null && imagesEntities.size() > 0) {
                List<String> imageUrls = new ArrayList<>();
                imagesEntities.forEach(imagesEntity -> {
                    String url = platformUtils.getStaticUrlByPath(imagesEntity.getPath(), domainPort);
                    imageUrls.add(url);
                });
                solutionSimple.setImageUrls(imageUrls);
            }
            // 解决方案标题
            solutionSimple.setTitle(solutionEntity.getTitle());
            // 康复人数
            solutionSimple.setRecoverNumber(solutionEntity.getRecoverNumber());
            // 解决方案ID
            solutionSimple.setSolutionId(solutionEntity.getId());
            result.add(solutionSimple);
        });
        return new PageImpl<>(result, solutionEntities.getPageable(), solutionEntities.getTotalElements());
    }

    @Override
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
