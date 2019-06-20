package org.flightythought.smile.appserver.controller;

import org.apache.commons.lang3.StringUtils;
import org.flightythought.smile.appserver.bean.AppUpdateData;
import org.flightythought.smile.appserver.bean.ResponseBean;
import org.flightythought.smile.appserver.service.ViewService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

/**
 * Copyright 2019 Flighty-Thought All rights reserved.
 *
 * @Author: LiLei
 * @ClassName ViewController
 * @CreateTime 2019/6/20 2:19
 * @Description: TODO
 */
@Controller
@RequestMapping("/pages")
public class ViewController {

    @Autowired
    private ViewService viewService;

    private static final Logger LOG = LoggerFactory.getLogger(ViewController.class);

    @GetMapping("/invitationRegistration")
    public String InvitationRegistration(String username, Model model) {
        if (StringUtils.isBlank(username)) {
            return null;
        }
        model.addAttribute("recommender", username);
        return "register";
    }

    @PostMapping("/register")
    @ResponseBody
    public ResponseBean register(String phone, String vCode, String token, String recommender) {
        try {
            AppUpdateData result = viewService.register(phone, vCode, token, recommender);
            return ResponseBean.ok("注册成功", result);
        } catch (Exception e) {
            LOG.error("注册失败", e);
            return ResponseBean.error(e.getMessage());
        }
    }
}
