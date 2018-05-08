package com.ices.reservation.manager.dao.info;

import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ny
 * @Date: Created in 8:33 2018/5/8 0008
 */
public class InfoProvider {

    private Logger log = LoggerFactory.getLogger(InfoProvider.class);

    public String getReservationStatus(@Param("start") String start,@Param("end") String end){
        StringBuilder sql = new StringBuilder("select admission_date as admissionDate, is_admission as isAdmission, count(1) as num from admission_calendar a , reservation_info r \n" +
                "where admission_date between ");
        sql.append("'" + start + "' and '" + end + "'");
        sql.append(" and a.admission_id = r.admission_id GROUP BY admission_date, is_admission");
        this.log.info("SQL: {}", sql);
        return sql.toString();
    }
}
