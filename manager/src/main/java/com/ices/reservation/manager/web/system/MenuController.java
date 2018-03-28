package com.ices.reservation.manager.web.system;

import com.ices.pojo.system.Menu;
import com.ices.pojo.system.Role;
import com.ices.reservation.common.sql.BaseController;
import com.ices.reservation.manager.service.system.MenuService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ny
 * @Date: Created in 13:50 2018/3/27 0027
 */
@RestController
@CrossOrigin
@RequestMapping(value = "system/menu")
@Api(tags = "菜单接口")
public class MenuController extends BaseController<Menu> {
    @Autowired
    MenuService menuService;

    @RequestMapping(value = "tree", method = RequestMethod.POST)
    public Object getMenuTree(){
        return menuService.getAllMenu();
    }

    @RequestMapping(value = "treebyrole",method = RequestMethod.POST)
    public Object getMenuTreeByRole(@RequestBody Role role){
        return menuService.getMenuByRole(role);
    }
}
