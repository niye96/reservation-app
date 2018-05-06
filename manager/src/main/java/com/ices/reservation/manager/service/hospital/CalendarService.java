package com.ices.reservation.manager.service.hospital;

import com.ices.pojo.hospital.Calendar;
import com.ices.pojo.hospital.Department;
import com.ices.pojo.hospital.Doctor;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.common.utils.IdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.dao.hospital.CalendarDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 9:46 2018/5/6 0006
 */

@Service
public class CalendarService extends BaseService<Calendar> {

    @Autowired
    CalendarDao calendarDao;

    @Override
    protected void beforeAdd(Calendar clz) throws RuntimeException {
        clz.setAdmissionId(IdUtil.generateId());
        clz.setRemainingNum(clz.getAdmissionNum());
        clz.setIsValid(1);
    }

    public Object getPageListByCondition(Calendar calendar){
//        if(StringUtils.isNotEmpty(calendar.getDepartmentName())){
//            Department department = new Department();
//            department.setDepartmentName(calendar.getDepartmentName());
//            department.setHospitalId(calendar.getHospitalId());
//            Object data = ((Map)departmentService.detailUsedByBase(department)).get("data");
//            department = ClassUtil.mapToClass((Map)data, Department.class);
//            calendar.setDepartmentId(department.getDepartmentId());
//        }
//        if(StringUtils.isNotEmpty(calendar.getDoctorName())){
//            Doctor doctor = new Doctor();
//            doctor.setDoctorName(calendar.getDoctorName());
//            doctor.setHospitalId(calendar.getHospitalId());
//            Object data = ((Map)doctorService.detailUsedByBase(doctor)).get("data");
//            doctor = ClassUtil.mapToClass((Map)data, Doctor.class);
//            calendar.setDoctorId(doctor.getDoctorId());
//        }
        List<Map> re = calendarDao.getListByName(calendar);
        return re == null ? ReturnUtil.error("查询失败") : ReturnUtil.success(re, this.calendarDao.countListByName(calendar));
    }
}
