package com.ices.reservation.manager.service;

import com.ices.pojo.system.Buser;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.common.utils.PwdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.dao.system.BuserDao;
import com.ices.reservation.manager.service.system.BuserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 19:39 2018/3/28 0028
 */
@Service
public class LoginService {
    @Autowired
    BuserDao buserDao;

    public Object login(Buser buser){
        if(StringUtils.isNotEmpty(buser.getLoginId())){
            Buser user = new Buser();
            user.setLoginId(buser.loginId);
            user = ClassUtil.mapToClass(buserDao.selectOneByObject(user), Buser.class);
            try {
                if (user == null || !user.getLoginPwd().equals(PwdUtil.encrypt(buser.getLoginPwd()))) {
                    return ReturnUtil.error("用户名或密码错误");
                }
            }catch (Exception e){
                return ReturnUtil.error("服务器发生错误");
            }
            return ReturnUtil.success(user);
        }
        return ReturnUtil.error("请输入用户名或密码");
    }
}
