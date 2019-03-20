package org.flightythought.smile.admin.controller;

import org.flightythought.smile.admin.bean.ResponseBean;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录控制器
 * @author lilei
 */
@RestController
public class LoginController {
    @RequestMapping(value = "/login_p", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean login() {
        return ResponseBean.error("尚未登录，请登录!");
    }

    @RequestMapping(value = "/logout_p", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean logout() {
        return ResponseBean.ok("退出登录");
    }

    @RequestMapping(value = "/session/invalid", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseBean sessionInvalid() {
        return ResponseBean.sessionInvalid("session已过期，请重新登录!");
    }
}
