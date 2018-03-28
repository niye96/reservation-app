package com.ices.reservation.manager.web.system;

import com.ices.pojo.system.Role;
import com.ices.reservation.common.sql.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ny
 * @Date: Created in 10:15 2018/3/27 0027
 */
@RestController
@CrossOrigin
@RequestMapping("system/role")
@Api(tags = {"角色接口"})
public class RoleController extends BaseController<Role>{
}
