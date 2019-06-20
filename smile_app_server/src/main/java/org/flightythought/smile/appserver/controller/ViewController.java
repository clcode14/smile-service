package org.flightythought.smile.appserver.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

    @GetMapping("/invitationRegistration")
    public String InvitationRegistration(String username) {
        return "register";
    }
}
