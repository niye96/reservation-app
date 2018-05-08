package com.ices.reservation.manager.service.system;

import com.ices.pojo.system.Buser;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.common.utils.PwdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 10:19 2018/3/27 0027
 */
@Service
public class BuserService extends BaseService<Buser> {
    @Override
    protected void beforeAdd(Buser clz) throws RuntimeException {
        try {
            clz.setLoginPwd(PwdUtil.encrypt(clz.getLoginPwd()));
        } catch (Exception e) {
            throw new RuntimeException("保存错误");
        }
    }

    @Override
    @Transactional
    public Object addOneUsedByBase(Buser clz) {
        try {
            clz.setLoginPwd(PwdUtil.encrypt(clz.getLoginPwd()));
            this.baseDao.insertOne(clz);
            return ReturnUtil.success(clz);
        } catch (DuplicateKeyException var3) {
            return ReturnUtil.error("该账户已存在");
        } catch (Exception var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    @Transactional
    public Object changePwd(String loginId, String oldPwd, String newPwd){
        Buser buser = new Buser();
        buser.setLoginId(loginId);
        try {
            buser = ClassUtil.mapToClass(this.baseDao.selectOneByObject(buser), Buser.class);
            if(buser == null) return ReturnUtil.error("无此用户!");
            else if(!buser.getLoginPwd().equals(PwdUtil.encrypt(oldPwd))) return  ReturnUtil.error("与原密码不符合!");
            else {
                buser.setLoginPwd(PwdUtil.encrypt(newPwd));
                return super.updateUsedByBase(buser);
            }
        } catch (Exception e) {
            throw new RuntimeException("修改密码错误");
        }
    }
}
