package com.ices.reservation.manager.web;

import com.ices.pojo.system.Buser;
import com.ices.reservation.manager.service.LoginService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ny
 * @Date: Created in 19:38 2018/3/28 0028
 */
@RestController
@CrossOrigin
@RequestMapping(value = "login")
@Api(tags = {"登录接口"})
public class LoginController {
    @Autowired
    LoginService loginService;

    @RequestMapping(value = "buser", method = RequestMethod.POST)
    public Object login(@RequestBody Buser buser){
        return loginService.login(buser);
    }
}
