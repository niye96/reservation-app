package com.ices.reservation.manager.web.system;

import com.ices.pojo.system.Buser;
import com.ices.reservation.common.sql.BaseController;
import com.ices.reservation.manager.service.system.BuserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ny
 * @Date: Created in 10:19 2018/3/27 0027
 */
@RestController
@CrossOrigin
@RequestMapping("system/buser")
@Api(tags = {"后台用户接口"})
public class BuserController extends BaseController<Buser> {

    @Autowired
    BuserService buserService;

    @RequestMapping(value = "changepwd", method = RequestMethod.POST)
    public Object changePwd(@RequestParam String loginId, @RequestParam String oldPwd, @RequestParam String newPwd){
        return buserService.changePwd(loginId, oldPwd, newPwd);
    }
}
