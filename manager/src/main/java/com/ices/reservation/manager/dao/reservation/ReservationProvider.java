package com.ices.reservation.manager.dao.reservation;

import com.ices.pojo.Reservation;
import com.ices.pojo.hospital.Calendar;
import com.ices.reservation.manager.dao.hospital.CalendarProvider;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 10:29 2018/5/7 0007
 */
public class ReservationProvider {
    private Logger log = LoggerFactory.getLogger(CalendarProvider.class);

    public String getPageListByName(Map<String, Object> params){
        Reservation reservation = (Reservation) params.get("reservation");
        StringBuilder sql = new StringBuilder("select r.reservation_id as reservationId, r.user_phone as userPhone," +
                "r.patient_id as patientId, r.admission_id as admissionId," +
                "r.is_admission as isAdmission, p.patient_name as patientName from reservation_info r, patient_info p " +
                "where r.patient_id = p.patient_id ");
        if(StringUtils.isNotEmpty(reservation.getAdmissionId()))
            sql.append("and admission_id = '" + reservation.getAdmissionId() + "' ");
        if(StringUtils.isNotEmpty(reservation.getPatientName()))
            sql.append("and  p.patient_name like '%" + reservation.getPatientName() + "%' ");
        int offset = (Integer.valueOf(reservation.getPageNo()) - 1) * Integer.valueOf(reservation.getPageSize());
        int num = Integer.valueOf(reservation.getPageSize());
        sql.append(" limit " + offset + "," + num);
        this.log.info("SQL: {}", sql);
        return sql.toString();
    }

    public String countByName(Map<String, Object> params){
        Reservation reservation = (Reservation) params.get("reservation");
        StringBuilder sql = new StringBuilder("select count(1) from reservation_info r, patient_info p " +
                "where r.patient_id = p.patient_id ");
        if(StringUtils.isNotEmpty(reservation.getAdmissionId()))
            sql.append("and admission_id = '" + reservation.getAdmissionId() + "' ");
        if(StringUtils.isNotEmpty(reservation.getPatientName()))
            sql.append("and  p.patient_name like '%" + reservation.getPatientName() + "%' ");
        this.log.info("SQL: {}", sql);
        return sql.toString();
    }
}
