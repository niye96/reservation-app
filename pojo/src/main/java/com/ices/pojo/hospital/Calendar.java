package com.ices.pojo.hospital;

import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.RefColumn;
import com.ices.reservation.common.sql.Table;
import com.ices.reservation.common.utils.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ny
 * @Date: Created in 9:37 2018/5/6 0006
 */

@Getter
@Setter
@Table(name = "admission_calendar")
public class Calendar extends Page{
    @Column(column = "admission_id", isId = true)
    public String admissionId;
    @Column(column = "hospital_id")
    public String hospitalId;
    @RefColumn(refSql = ("(select hospital_name from hospital_info " +
            "where hospital_id = hospitalId)"))
    public String hospitalName;
    @Column(column = "department_id")
    public String departmentId;
    @RefColumn(refSql = ("(select department_name from hospital_department " +
            "where department_id = departmentId)"))
    public String departmentName;
    @Column(column = "doctor_id")
    public String doctorId;
    @RefColumn(refSql = ("(select doctor_name from doctor_info " +
            "where doctor_id = doctorId)"))
    public String doctorName;
    @Column(column = "admission_date")
    public String admissionDate;
    @Column(column = "admission_period")
    public String admissionPeriod;
    @Column(column = "admission_num")
    public Integer admissionNum;
    @Column(column = "remaining_num")
    public Integer remainingNum;
    @Column(column = "is_valid")
    public Integer isValid;
}
