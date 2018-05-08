package com.ices.reservation.manager.service.hospital;

import com.ices.pojo.hospital.Doctor;
import com.ices.pojo.system.Buser;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.common.utils.IdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.service.system.BuserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 14:49 2018/4/2 0002
 */
@Service
public class DoctorService extends BaseService<Doctor> {
    @Autowired
    BuserService buserService;

    @Override
    @Transactional
    public Object addOneUsedByBase(Doctor clz) {
        clz.setDoctorId(IdUtil.generateId());
        try {
            Buser buser = new Buser();
            buser.setLoginId(clz.getLoginId());
            buser.setLoginPwd("123456");
            buser.setRoleId(3L);
            buser.setUserName(clz.getDoctorName());
            // 判断是否已存在此用户名
            Object result = buserService.addOneUsedByBase(buser);
            if((boolean)((Map)result).get("status") == false){
                return result;
            }
            this.baseDao.insertOne(clz);
            return ReturnUtil.success(clz);
        } catch (DuplicateKeyException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    @Override
    @Transactional
    public Object deleteUsedByBase(Doctor doctor) {
        try {
            Map d = (Map) ((Map) detailUsedByBase(doctor)).get("data");
            if(d == null) throw new RuntimeException();
            Buser buser = new Buser();
            buser.setLoginId((String) d.get("loginId"));
            buserService.deleteUsedByBase(buser);
            this.baseDao.deleteByObject(doctor);
            return ReturnUtil.success("删除成功");
        } catch (Exception var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    @Override
    @Transactional
    public Object deleteAllUsedByBase(List<Doctor> list) {
        try {
            Buser buser = new Buser();
            Map d;
            for(Doctor t : list) {
                d = (Map) ((Map) detailUsedByBase(t)).get("data");
                if(d == null) throw new RuntimeException();
                buser.setLoginId((String) d.get("loginId"));
                buserService.deleteUsedByBase(buser);
                this.baseDao.deleteByObject(t);
            }
            return ReturnUtil.success("删除成功");
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }
}
