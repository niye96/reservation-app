package com.ices.reservation.manager.dao.hospital;

import com.ices.pojo.hospital.Calendar;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 10:57 2018/5/6 0006
 */
public class CalendarProvider {
    private Logger log = LoggerFactory.getLogger(CalendarProvider.class);

    public String getListByName(Map<String, Object> params){
        Calendar calendar = (Calendar) params.get("calendar");
        StringBuilder sql =new StringBuilder("select a.admission_id as admissionId, a.hospital_id as hospitalId," +
                "a.department_id as departmentId, a.doctor_id as doctorId, a.admission_date as admissionDate," +
                "a.admission_period as admissionPeriod, a.admission_num as admissionNum, a.remaining_num as remainingNum," +
                "a.is_valid as isValid, h.department_name as departmentName,d.doctor_name as doctorName from admission_calendar a,hospital_department h, doctor_info d " +
                "where a.department_id = h.department_id and d.doctor_id = a.doctor_id ");
        if(StringUtils.isNotEmpty(calendar.getHospitalId())) {
            sql.append("and a.hospital_id='" + calendar.getHospitalId() + "' ");
        }
        if(StringUtils.isNotEmpty(calendar.getDepartmentName())) {
            sql.append("and h.department_name like '%" + calendar.getDepartmentName() + "%'");
        }
        if(StringUtils.isNotEmpty(calendar.getDoctorName())){
            sql.append("and d.doctor_name like '%" + calendar.getDoctorName() + "%'");
        }
        int offset = (Integer.valueOf(calendar.getPageNo()) - 1) * Integer.valueOf(calendar.getPageSize());
        int num = Integer.valueOf(calendar.getPageSize());
        sql.append(" limit " + offset + "," + num);
        this.log.info("SQL: {}", sql);
        return sql.toString();
    }

    public String countListByName(Map<String, Object> params){
        Calendar calendar = (Calendar) params.get("calendar");
        StringBuilder sql =new StringBuilder("select count(1) from admission_calendar a,hospital_department h, doctor_info d " +
                "where a.department_id = h.department_id and d.doctor_id = a.doctor_id ");
        if(StringUtils.isNotEmpty(calendar.getHospitalId())) {
            sql.append("and a.hospital_id='" + calendar.getHospitalId() + "' ");
        }
        if(StringUtils.isNotEmpty(calendar.getDoctorName())) {
            sql.append("and h.department_name like '%" + calendar.getDoctorName() + "%'");
        }
        if(StringUtils.isNotEmpty(calendar.getDepartmentName())){
            sql.append("and d.doctor_name like '%" + calendar.getDepartmentName() + "%'");
        }
        this.log.info("SQL: {}", sql);
        return sql.toString();
    }

    public String getCalendarByDate(Map<String, Object> params){
        Calendar calendar = (Calendar) params.get("calendar");
        String begin = (String) params.get("begin");
        String end = (String) params.get("end");
        StringBuilder sql = new StringBuilder("select DISTINCT a.admission_date as admissionDate from admission_calendar a ");
        sql.append("where a.department_id = '" + calendar.getDepartmentId() + "'");
        sql.append(" and a.hospital_id = '" + calendar.getHospitalId() + "'");
        sql.append(" and a.admission_date between '" + begin + "' and '" + end + "'");
        this.log.info("SQL: {}", sql);
        return sql.toString();
    }

    public String getSelectCalendar(Map<String, Object> params){
        Calendar calendar = (Calendar) params.get("calendar");
        StringBuilder sql = new StringBuilder("select a.admission_id as admissionId, a.admission_period as admissionPeriod, a.remaining_num as remainingNum, " +
                "d.doctor_name as doctorName, d.doctor_title as doctorTitle from admission_calendar a, doctor_info d " +
                "where d.doctor_id = a.doctor_id");
        sql.append(" and a.department_id = '" + calendar.getDepartmentId() + "'");
        sql.append(" and a.hospital_id = '" + calendar.getHospitalId() + "'");
        sql.append(" and a.admission_date = '" + calendar.getAdmissionDate() + "'");
        this.log.info("SQL: {}", sql);
        return sql.toString();
    }
}
