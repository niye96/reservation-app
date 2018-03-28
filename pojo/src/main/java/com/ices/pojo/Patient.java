package com.ices.pojo;

import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.Table;
import com.ices.reservation.common.utils.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ny
 * @Date: Created in 9:08 2018/3/24 0024
 */
@Getter
@Setter
@Table(name = "patient_info")
public class Patient extends Page {
    @Column(column = "patient_id", isId = true)
    public String patientId;
    @Column(column = "user_id")
    public String userId;
    @Column(column = "id_card")
    public String idCard;
    @Column(column = "relation")
    public String relation;
    @Column(column = "gmt_create", isDateTime = true)
    public String gmtCreate;
    @Column(column = "gmt_modified", isDateTime = true)
    public String gmtModified;
}
