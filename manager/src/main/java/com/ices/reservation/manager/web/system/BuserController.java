package com.ices.reservation.manager.web.system;

import com.ices.pojo.system.Buser;
import com.ices.reservation.common.sql.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ny
 * @Date: Created in 10:19 2018/3/27 0027
 */
@RestController
@CrossOrigin
@RequestMapping("system/buser")
@Api(tags = {"后台用户接口"})
public class BuserController extends BaseController<Buser> {
}
