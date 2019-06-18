package org.flightythought.smile.admin.service.impl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.flightythought.smile.admin.database.entity.SysResourceEntity;
import org.flightythought.smile.admin.database.entity.SysResourceUrlEntity;
import org.flightythought.smile.admin.database.repository.SysResourceRepository;
import org.flightythought.smile.admin.database.repository.SysResourceUrlRepository;
import org.flightythought.smile.admin.service.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DevelopmentServiceImpl
 * @CreateTime 2019/6/11 0:51
 * @Description: TODO
 */
@Service
public class DevelopmentServiceImpl implements DevelopmentService {
    @Autowired
    WebApplicationContext applicationContext;
    @Autowired
    private SysResourceRepository sysResourceRepository;
    @Autowired
    private SysResourceUrlRepository sysResourceUrlRepository;

    @Override
    public List<Map<String, String>> getAllURL() {
        List<Map<String, String>> resultList = new ArrayList<>();

        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : map.entrySet()) {
            Map<String, String> resultMap = new LinkedHashMap<>();

            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();

            resultMap.put("className", handlerMethod.getMethod().getDeclaringClass().getName()); // 类名
            Annotation[] parentAnnotations = handlerMethod.getBeanType().getAnnotations();
            for (Annotation annotation : parentAnnotations) {
                if (annotation instanceof Api) {
                    Api api = (Api) annotation;
                    resultMap.put("classDesc", api.value());
                } else if (annotation instanceof RequestMapping) {
                    RequestMapping requestMapping = (RequestMapping) annotation;
                    if (null != requestMapping.value() && requestMapping.value().length > 0) {
                        resultMap.put("classURL", requestMapping.value()[0]);//类URL
                    }
                }
            }
            resultMap.put("methodName", handlerMethod.getMethod().getName()); // 方法名
            Annotation[] annotations = handlerMethod.getMethod().getDeclaredAnnotations();
            if (annotations != null) {
                // 处理具体的方法信息
                for (Annotation annotation : annotations) {
                    if (annotation instanceof ApiOperation) {
                        ApiOperation methodDesc = (ApiOperation) annotation;
                        String desc = methodDesc.value();
                        resultMap.put("methodDesc", desc);//接口描述
                    }
                }
            }
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            for (String url : p.getPatterns()) {
                resultMap.put("methodURL", url);//请求URL
            }
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                resultMap.put("requestType", requestMethod.toString());//请求方式：POST/PUT/GET/DELETE
            }
            resultList.add(resultMap);
        }
        return resultList;
    }

    @Override
    @Transactional
    public List<SysResourceUrlEntity> saveOrUpdateResourceUrl() {
        // 删除表tb_sys_resource_url数据
        sysResourceUrlRepository.deleteAll();
        // 查询资源列表
        List<SysResourceEntity> sysResourceEntities = sysResourceRepository.findAll();
        Map<String, SysResourceEntity> sysResourceEntityMap = sysResourceEntities.stream()
                .collect(Collectors.toMap(SysResourceEntity::getName, Function.identity()));
        List<Map<String, String>> resultList = this.getAllURL();
        List<SysResourceUrlEntity> sysResourceUrlEntities = resultList.stream().map(map -> {
            // 获取@API->value(模块名称)
            String classDesc = map.get("classDesc");
            SysResourceEntity sysResourceEntity = sysResourceEntityMap.get(classDesc);
            if (sysResourceEntity != null) {
                SysResourceUrlEntity sysResourceUrlEntity = new SysResourceUrlEntity();
                // 主类注释
                sysResourceUrlEntity.setClassDesc(map.get("classDesc"));
                // 方法注释
                sysResourceUrlEntity.setMethodDesc(map.get("methodDesc"));
                String classUrl = map.get("classURL");
                String methodUrl = map.get("methodURL");
                // 类URL
                sysResourceUrlEntity.setClassUrl(map.get("classURL"));
                // 方法URL
                sysResourceUrlEntity.setMethodUrl(map.get("methodURL"));
                if (classUrl != null && methodUrl != null) {
                    // URL
                    sysResourceUrlEntity.setUrl(classUrl + methodUrl);
                }
                // 请求方式
                sysResourceUrlEntity.setRequestMethod(map.get("requestType"));
                // 备注
                sysResourceUrlEntity.setMemo("className:" + map.get("className") + "  methodName:" + map.get("methodName"));
                // 资源
                sysResourceUrlEntity.setResourceId(sysResourceEntity.getId());
                // 创建者
                sysResourceUrlEntity.setCreateUserName("system");
                // 创建时间
                sysResourceUrlEntity.setCreateTime(LocalDateTime.now());
                return sysResourceUrlEntity;
            }
            return null;
        }).filter(Objects::nonNull).collect(Collectors.toList());
        // 保存元素
        return sysResourceUrlRepository.saveAll(sysResourceUrlEntities);
    }


}
