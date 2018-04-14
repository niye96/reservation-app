package com.ices.reservation.manager.service;

import com.ices.pojo.User;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.common.utils.PwdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: ny
 * @Date: Created in 21:31 2018/3/22 0022
 */
@Service
public class UserService extends BaseService<User> {
    @Override
    protected void beforeAdd(User clz) throws RuntimeException {
        try {
            clz.setUserPwd(PwdUtil.encrypt(clz.getUserPwd()));
        } catch (Exception e) {
            throw new RuntimeException("保存错误");
        }
    }

    @Override
    protected void beforeUpdate(User clz) throws RuntimeException {
        clz.setUserPwd(null);
    }

    @Transactional
    public Object changePwd(String userPhone, String oldPwd, String newPwd){
        User user = new User();
        user.setUserPhone(userPhone);
        try {
            user = ClassUtil.mapToClass(this.baseDao.selectOneByObject(user), User.class);
            if(user == null) return ReturnUtil.error("无此用户!");
            else if(!user.getUserPwd().equals(PwdUtil.encrypt(oldPwd))) return  ReturnUtil.error("与原密码不符合!");
            else {
                user.setUserPwd(PwdUtil.encrypt(newPwd));
                return ReturnUtil.success(baseDao.updateById(user));
            }
        } catch (Exception e) {
            throw new RuntimeException("修改密码错误");
        }
    }
}
