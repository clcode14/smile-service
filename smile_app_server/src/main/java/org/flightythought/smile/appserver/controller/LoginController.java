package org.flightythought.smile.appserver.controller;

import org.apache.http.HttpResponse;
import org.flightythought.smile.appserver.common.redis.RedisUtil;
import org.flightythought.smile.appserver.common.utils.HttpUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName LoginController
 * @CreateTime 2019/3/17 19:37
 * @Description: TODO
 */
@Controller
public class LoginController {
    private Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    private RedisUtil redisUtil;

    @RequestMapping("/")
    @ResponseBody
    public String showHome(HttpServletRequest request) {
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        logger.info("当前登陆用户：" + name);
        String authHeader = request.getHeader("Authorization");

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String authToken = authHeader.substring("Bearer ".length());
            String username = (String) redisUtil.get(authToken);
            System.out.println(username);
        }
        return name;
    }

    @RequestMapping("/sms/code")
    @ResponseBody
    public String sms(String mobile, HttpSession session) {
        int code = (int) Math.ceil(Math.random() * 9000 + 1000);

        Map<String, Object> map = new HashMap<>(16);
        map.put("mobile", mobile);
        map.put("code", code);

        String host = "https://cdcxdxjk.market.alicloudapi.com";
        String path = "/chuangxin/dxjk";
        String method = "POST";
        String appcode = "4e84bebc30684ce681c98547087784db";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appcode);
        Map<String, String> querys = new HashMap<String, String>();
        querys.put("content", "【笑美】你的验证码是：" + code + "，3分钟内有效！");
        querys.put("mobile", "15850374663");
        Map<String, String> bodys = new HashMap<String, String>();


        try {
            /**
             * 重要提示如下:
             * HttpUtils请从
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/src/main/java/com/aliyun/api/gateway/demo/util/HttpUtils.java
             * 下载
             *
             * 相应的依赖请参照
             * https://github.com/aliyun/api-gateway-demo-sign-java/blob/master/pom.xml
             */
            HttpResponse response = HttpUtils.doPost(host, path, method, headers, querys, bodys);
            System.out.println(response.toString());
            //获取response的body
            //System.out.println(EntityUtils.toString(response.getEntity()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        session.setAttribute("smsCode", map);

        logger.info("{}：为 {} 设置短信验证码：{}", session.getId(), mobile, code);
        return "发送短信成功" + code;
    }

}
