package com.ices.reservation.manager.service;

import com.ices.pojo.User;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.PwdUtil;
import org.springframework.stereotype.Service;

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
}
