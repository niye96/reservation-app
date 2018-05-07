package com.ices.pojo.hospital;

import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.RefColumn;
import com.ices.reservation.common.sql.Table;
import com.ices.reservation.common.utils.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ny
 * @Date: Created in 14:39 2018/4/2 0002
 */
@Getter
@Setter
@Table(name = "doctor_info")
public class Doctor extends Page {
    @Column(column = "doctor_id", isId = true)
    public String doctorId;
    @Column(column = "hospital_id")
    public String hospitalId;
    @RefColumn(refSql = "(select hospital_name from hospital_info where hospital_id = hospitalId)")
    public String hospitalName;
    @Column(column = "type_id")
    public Long typeId;
    @RefColumn(refSql = ("(select department_type_name from department_code " +
            "where department_type_id = typeId)"))
    public String typeName;
    @Column(column = "doctor_name", isUseLike = true)
    public String doctorName;
    @Column(column = "sex")
    public String sex;
    @Column(column = "doctor_title")
    public String doctorTitle;
    @Column(column = "skill")
    public String skill;
    @Column(column = "introduction")
    public String introduction;
    @Column(column = "doctor_photo")
    public String doctorPhoto;
    @Column(column = "login_id")
    public String loginId;
    public String loginPwd;
}
