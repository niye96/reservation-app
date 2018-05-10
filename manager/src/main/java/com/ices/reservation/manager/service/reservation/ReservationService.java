package com.ices.reservation.manager.service.reservation;

import com.ices.pojo.reservation.Reservation;
import com.ices.pojo.hospital.Calendar;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.common.utils.IdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.dao.reservation.ReservationDao;
import com.ices.reservation.manager.service.hospital.CalendarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
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

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    @Transactional
    public Object reservation(Reservation reservation){
        // 判断同个用户是否预约了4次以上
        Reservation temp = new Reservation();
        temp.setUserPhone(reservation.getUserPhone());
        temp.setIsAdmission(0);
        List<Map> tempList = reservationDao.selectNoPageList(temp, null);
        if(tempList.size() >= 4) return ReturnUtil.error("已到达最大预约数");
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

    @Transactional
    public Object cancelReservation(Reservation r){
        Reservation reservation = new Reservation();
        reservation.setReservationId(r.getReservationId());
        r = ClassUtil.mapToClass(reservationDao.selectOneByObject(r), Reservation.class);
        if(r == null) return ReturnUtil.error("此预约项不存在");
        // 判断日期是否符合
        Calendar calendar = new Calendar();
        calendar.setAdmissionId(r.getAdmissionId());
        Object data = ((Map)calendarService.detailUsedByBase(calendar)).get("data");
        calendar = ClassUtil.mapToClass((Map)data, Calendar.class);
        try {
            long orderTime = simpleDateFormat.parse(calendar.getAdmissionDate()).getTime();
            long today = new Date().getTime();
            if(orderTime - today <= 3600 * 24 * 2) return ReturnUtil.error("已经无法取消预约");
        } catch (ParseException e) {
            throw new RuntimeException();
        }
        reservationDao.deleteByObject(reservation);
        calendar.setRemainingNum(calendar.getRemainingNum() + 1);
        calendarService.updateUsedByBase(calendar);
        return ReturnUtil.success();
    }

    public Object getReservationByDoctor(Calendar c, String patientName){
        // 查询医生的排班
        Calendar calendar = new Calendar();
        calendar.setDoctorId(c.getDoctorId());
        calendar.setAdmissionPeriod(c.getAdmissionPeriod());
        calendar.setAdmissionDate(simpleDateFormat.format(new Date()));
        Map re =(Map)((Map)calendarService.detailUsedByBase(calendar)).get("data");
        // 查询当前排班的用户
        if(re == null) return ReturnUtil.error("今天该时间段没有排班");
        Reservation reservation = new Reservation();
        String admissionId = (String) re.get("admissionId");
        reservation.setAdmissionId(admissionId);
        reservation.setPageSize(c.getPageSize());
        reservation.setPageNo(c.getPageNo());
        reservation.setPatientName(patientName);
        List<Map> reservations = reservationDao.getPageListByName(reservation);
        return reservations == null ? ReturnUtil.error("未查到数据") : ReturnUtil.success(reservations,reservationDao.countByName(reservation));
    }

}
