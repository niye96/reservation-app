package com.ices.reservation.manager.service.system;

import com.alibaba.fastjson.JSONObject;
import com.ices.pojo.system.Menu;
import com.ices.pojo.system.Role;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.manager.dao.system.MenuDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @Author: ny
 * @Date: Created in 13:49 2018/3/27 0027
 */
@Service
public class MenuService extends BaseService<Menu> {
    @Autowired
    MenuDao menuDao;

    public Object getAllMenu(){
        List menus = baseDao.selectNoPageList(new Menu(), "menu_index");
        return list2tree(menus);
    }

    public Object getMenuByRole(Role role){
        return list2tree(menuDao.getListByRole(role.getRoleId()));
    }

    private Object list2tree(List menus){
        Map<Long, Menu> tree = new HashMap();

        List result = new ArrayList();
        Iterator<Map<String, Object>> iterator = menus.iterator();
        int level = 0;
        while(!menus.isEmpty()) {
            while (iterator.hasNext()) {
                Menu menu = ClassUtil.mapToClass(iterator.next(), Menu.class);
                if(menu.getMenuLevel() == level){
                    iterator.remove();
                    if(level!=0){
                        tree.get(menu.getParentMenuId()).children.add(menu);
                    }else{
                        result.add(menu);
                    }
                    tree.put(menu.getMenuId(), menu);
                }
            }
            level++;
            iterator = menus.iterator();
        }
        return result;
    }
}
