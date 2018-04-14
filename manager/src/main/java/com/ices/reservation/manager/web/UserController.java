package com.ices.reservation.manager.web;

import com.ices.pojo.User;
import com.ices.reservation.common.sql.BaseController;
import com.ices.reservation.manager.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ny
 * @Date: Created in 21:31 2018/3/22 0022
 */
@RestController
@CrossOrigin
@RequestMapping(value = "user")
@Api(tags = {"用户接口"})
public class UserController extends BaseController<User> {
    @Autowired
    UserService userService;

    @RequestMapping(value = "changepwd", method = RequestMethod.POST)
    public Object changePwd(String userPhone, String oldPwd, String newPwd) {
        return userService.changePwd(userPhone, oldPwd, newPwd);
    }
}
