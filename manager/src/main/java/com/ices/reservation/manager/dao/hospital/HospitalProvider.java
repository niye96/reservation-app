package com.ices.reservation.manager.dao.hospital;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Author: ny
 * @Date: Created in 16:41 2018/5/11 0011
 */
public class HospitalProvider {
    private Logger log = LoggerFactory.getLogger(CalendarProvider.class);

    public String getHospitalDepartmentType(String hospitalId){
        StringBuilder sql = new StringBuilder("select c.department_type_id as id, c.department_type_name as name from department_code c, hospital_department d\n" +
                "where d.type_id = c.department_type_id and d.hospital_id = '");
        sql.append(hospitalId + "'\n");
        sql.append("group by c.department_type_name");
        log.info("SQL: {}", sql);
        return sql.toString();
    }
}
