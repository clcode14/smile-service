package org.flightythought.smile.admin.controller;

import com.alibaba.fastjson.JSONArray;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.mvc.condition.PatternsRequestCondition;
import org.springframework.web.servlet.mvc.condition.RequestMethodsRequestCondition;
import org.springframework.web.servlet.mvc.method.RequestMappingInfo;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName TestController
 * @CreateTime 2019/6/10 21:41
 * @Description: TODO
 */
@Controller
public class TestController {
    @Autowired
    WebApplicationContext applicationContext;

    @RequestMapping(value = "/getAllURL", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllURL() {
        List<Map<String, String>> resultList = new ArrayList<>();

        RequestMappingHandlerMapping requestMappingHandlerMapping = (RequestMappingHandlerMapping) applicationContext.getBean("requestMappingHandlerMapping");
        // 获取url与类和方法的对应信息
        Map<RequestMappingInfo, HandlerMethod> map = requestMappingHandlerMapping.getHandlerMethods();

        for (Map.Entry<RequestMappingInfo, HandlerMethod> mappingInfoHandlerMethodEntry : map.entrySet()) {
            Map<String, String> resultMap = new LinkedHashMap<>();

            RequestMappingInfo requestMappingInfo = mappingInfoHandlerMethodEntry.getKey();
            HandlerMethod handlerMethod = mappingInfoHandlerMethodEntry.getValue();

            resultMap.put("className",handlerMethod.getMethod().getDeclaringClass().getName()); // 类名
            Annotation[] parentAnnotations = handlerMethod.getBeanType().getAnnotations();
            for (Annotation annotation : parentAnnotations) {
                if (annotation instanceof Api) {
                    Api api = (Api) annotation;
                    resultMap.put("classDesc",api.value());
                } else if (annotation instanceof RequestMapping) {
                    RequestMapping requestMapping = (RequestMapping) annotation;
                    if (null != requestMapping.value() && requestMapping.value().length > 0) {
                        resultMap.put("classURL",requestMapping.value()[0]);//类URL
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
                        resultMap.put("methodDesc",desc);//接口描述
                    }
                }
            }
            PatternsRequestCondition p = requestMappingInfo.getPatternsCondition();
            for (String url : p.getPatterns()) {
                resultMap.put("methodURL",url);//请求URL
            }
            RequestMethodsRequestCondition methodsCondition = requestMappingInfo.getMethodsCondition();
            for (RequestMethod requestMethod : methodsCondition.getMethods()) {
                resultMap.put("requestType",requestMethod.toString());//请求方式：POST/PUT/GET/DELETE
            }
            resultList.add(resultMap);
        }
        Set<String> classURL = new LinkedHashSet<>();
        resultList.forEach(stringStringMap -> classURL.add(stringStringMap.get("classURL")));
        return JSONArray.toJSON(resultList);
    }
}
