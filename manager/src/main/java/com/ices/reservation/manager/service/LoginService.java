package com.ices.reservation.manager.service;

import com.ices.pojo.User;
import com.ices.pojo.system.Buser;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.common.utils.PwdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.dao.UserDao;
import com.ices.reservation.manager.dao.system.BuserDao;
import com.ices.reservation.manager.service.system.BuserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 19:39 2018/3/28 0028
 */
@Service
public class LoginService {
    @Autowired
    BuserDao buserDao;
    @Autowired
    UserDao userDao;

    public Object login(Buser buser){
        if(StringUtils.isNotEmpty(buser.getLoginId())){
            Buser user = new Buser();
            user.setLoginId(buser.getLoginId());
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

    public Object userLogin(User user){
        if(StringUtils.isNotEmpty(user.getUserPhone())){
            User tempUser = new User();
            tempUser.setUserPhone(user.getUserPhone());
            tempUser = ClassUtil.mapToClass(userDao.selectOneByObject(tempUser), User.class);
            try {
                if (tempUser == null || !tempUser.getUserPwd().equals(PwdUtil.encrypt(user.getUserPwd()))) {
                    return ReturnUtil.error("手机号或密码错误");
                }
            }catch (Exception e){
                return ReturnUtil.error("服务器发生错误");
            }
            return ReturnUtil.success("修改成功");
        }
        return ReturnUtil.error("请输入用户名或密码");
    }

    @Transactional
    public Object registerUser(User user){
        if(StringUtils.isNotEmpty(user.getUserPhone()) && StringUtils.isNotEmpty(user.getUserPwd())){
            User search = new User();
            search.setUserPhone(user.getUserPhone());
            search = ClassUtil.mapToClass(userDao.selectOneByObject(search), User.class);
            if(search != null) return ReturnUtil.error("手机号已重复!");
            try {
                user.setUserPwd(PwdUtil.encrypt(user.getUserPwd()));
                return ReturnUtil.success(userDao.insertOne(user));
            } catch (Exception e) {
                throw new RuntimeException();
            }
        }
        return ReturnUtil.error("请填写完整信息");
    }

}
