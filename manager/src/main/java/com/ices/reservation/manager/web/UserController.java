package com.ices.reservation.manager.web;

import com.ices.pojo.User;
import com.ices.reservation.common.sql.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
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
}
