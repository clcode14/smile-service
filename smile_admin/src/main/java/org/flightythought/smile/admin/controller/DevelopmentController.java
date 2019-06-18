package org.flightythought.smile.admin.controller;

import com.alibaba.fastjson.JSONArray;
import org.flightythought.smile.admin.database.entity.SysResourceUrlEntity;
import org.flightythought.smile.admin.service.DevelopmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName DevelopmentController
 * @CreateTime 2019/6/10 21:41
 * @Description: 开发测试控制层
 */
@Controller
public class DevelopmentController {
    @Autowired
    private DevelopmentService developmentService;

    @RequestMapping(value = "/getAllURL", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllURL() {
        List<Map<String, String>> resultList = developmentService.getAllURL();
        Set<String> classURL = new LinkedHashSet<>();
        resultList.forEach(stringStringMap -> classURL.add(stringStringMap.get("classURL")));
        return JSONArray.toJSON(resultList);
    }

    @RequestMapping(value = "/saveOrUpdateResourceUrl", method = RequestMethod.POST)
    @ResponseBody
    public List<SysResourceUrlEntity> saveOrUpdateResourceUrl() {
        return developmentService.saveOrUpdateResourceUrl();
    }
}
