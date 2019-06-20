package org.flightythought.smile.admin.service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Copyright 2019 Oriental Standard All rights reserved.
 *
 * @Author: LiLei
 * @ClassName LoginService
 * @CreateTime 2019/6/20 13:51
 * @Description: TODO
 */
public interface LoginService {
    List<String> getRoles(HttpSession session);
}
