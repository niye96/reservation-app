package com.ices.reservation.manager.service;

import com.ices.pojo.Reservation;
import com.ices.pojo.hospital.Calendar;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.common.utils.IdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.dao.ReservationDao;
import com.ices.reservation.manager.service.hospital.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 16:38 2018/5/6 0006
 */
@Service
public class ReservationService extends BaseService<Reservation>{

    @Autowired
    CalendarService calendarService;
    @Autowired
    ReservationDao reservationDao;

    @Transactional
    public Object reservation(Reservation reservation){
        // 判断有没有重复预约
        Map re = reservationDao.selectOneByObject(reservation);
        if(re != null) return ReturnUtil.error("请勿重复预约");
        // 生成主键
        reservation.setReservationId(IdUtil.generateId());
        // 更新接诊表
        Calendar calendar = new Calendar();
        calendar.setAdmissionId(reservation.getAdmissionId());
        Object data = ((Map) calendarService.detailUsedByBase(calendar)).get("data");
        calendar = ClassUtil.mapToClass((Map) data, Calendar.class);
        if(calendar.getRemainingNum() <=0 ){
            return ReturnUtil.error("预约名额已满");
        }else{
            calendar.setRemainingNum(calendar.getRemainingNum() - 1);
        }
        calendarService.updateUsedByBase(calendar);
        // 插入预约表
        reservation.setIsAdmission(0);
        reservationDao.insertOne(reservation);
        reservation.setCalendar(calendar);
        return ReturnUtil.success(reservation);
    }

    public Object getDetail(Reservation reservation){
        Calendar calendar = new Calendar();
        reservation = ClassUtil.mapToClass(reservationDao.selectOneByObject(reservation), Reservation.class);
        if(reservation == null) return ReturnUtil.error("查询失败");
        calendar.setAdmissionId(reservation.getAdmissionId());
        Object data = ((Map)calendarService.detailUsedByBase(calendar)).get("data");
        calendar = ClassUtil.mapToClass((Map)data, Calendar.class);
        reservation.setCalendar(calendar);
        return reservation;
    }
}
