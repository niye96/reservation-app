package com.ices.reservation.manager.service.hospital;

import com.ices.pojo.hospital.Calendar;
import com.ices.reservation.common.sql.BaseService;
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
        List<Map> re = calendarDao.getListByName(calendar);
        return re == null ? ReturnUtil.error("查询失败") : ReturnUtil.success(re, this.calendarDao.countListByName(calendar));
    }

    public Object getCalendarByDate(Calendar calendar, String begin, String end){
        if(StringUtils.isEmpty(calendar.getHospitalId()) || StringUtils.isEmpty(calendar.getDepartmentId())){
            return ReturnUtil.error("请传入医院ID与科室ID");
        }
        if(StringUtils.isEmpty(begin) || StringUtils.isEmpty(end)){
            return ReturnUtil.error("必须传入时间");
        }
        List<Map> re = calendarDao.getCalendarByDate(calendar, begin, end);
        return re == null ? ReturnUtil.error("查询失败") : ReturnUtil.success(re);
    }

    public Object getSelectCalendar(Calendar calendar){
        if(StringUtils.isEmpty(calendar.getHospitalId()) || StringUtils.isEmpty(calendar.getDepartmentId())){
            return ReturnUtil.error("请传入医院ID与科室ID");
        }
        if(StringUtils.isEmpty(calendar.getAdmissionDate())){
            return ReturnUtil.error("请选择日期");
        }
        List<Map> re = calendarDao.getSelectCalendar(calendar);
        return re == null ? ReturnUtil.error("查询失败") : ReturnUtil.success(re);
    }
}
