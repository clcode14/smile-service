package org.flightythought.smile.admin.controller;

import org.flightythought.smile.admin.bean.ResponseBean;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @PostMapping("/hello")
    public ResponseBean hello(){
        return ResponseBean.ok("success");
    }
}
