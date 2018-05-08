package com.ices.reservation.manager.service.info;

import com.ices.pojo.User;
import com.ices.pojo.hospital.Doctor;
import com.ices.pojo.hospital.Hospital;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.dao.UserDao;
import com.ices.reservation.manager.dao.hospital.DoctorDao;
import com.ices.reservation.manager.dao.hospital.HospitalDao;
import com.ices.reservation.manager.dao.info.InfoDao;
import com.ices.reservation.manager.service.hospital.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 20:34 2018/5/7 0007
 */
@Service
public class InfoService {
    @Autowired
    DoctorDao doctorDao;
    @Autowired
    HospitalDao hospitalDao;
    @Autowired
    UserDao userDao;
    @Autowired
    InfoDao infoDao;

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public final static int DAY = 3600 * 24 * 1000;

    public Object getBaseInfo(){
        Map result = new HashMap();
        result.put("userNum", userDao.selectCount(new User()));
        result.put("hospitalNum", hospitalDao.selectCount(new Hospital()));
        result.put("doctorNum", doctorDao.selectCount(new Doctor()));
        result.put("loginTime", 105);
        return ReturnUtil.success(result);
    }

    public Object getHostpitalStatus() {
        List<Map> re = infoDao.getHostpitalStatus();
        return re == null ? ReturnUtil.error("获取医院状态失败") : ReturnUtil.success(re);
    }

    public Object getReservationStauts() {
        long now = new Date().getTime();
        String end = simpleDateFormat.format(new Date(now - DAY));
        String begin = simpleDateFormat.format(new Date(now - DAY * 7));
        List<Map> result = infoDao.getReservationStatus(begin, end);
        return result == null ? ReturnUtil.error("获取预约状态失败") : ReturnUtil.success(result);
    }

    public Object getHostpitalAddr() {
        List<Map> result = infoDao.getHospitalAddr();
        return result == null ? ReturnUtil.error("获取医院地理位置失败") : ReturnUtil.success(result);
    }
}
