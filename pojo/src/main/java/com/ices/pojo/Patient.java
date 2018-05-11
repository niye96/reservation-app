package com.ices.pojo;

import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.Table;
import com.ices.reservation.common.utils.Page;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

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
    @NotEmpty(message = "就诊人姓名不能为空")
    @Size(max = 20, min = 2, message = "姓名长度在2到20位之间")
    @Column(column = "patient_name")
    public String patientName;
    @Column(column = "user_id")
    public String userId;
    @Column(column = "card_type")
    public String cardType;
    @NotEmpty(message = "证件号不能为空")
    @Column(column = "id_card")
    public String idCard;
    @NotEmpty(message = "请选择就诊人关系")
    @Column(column = "relation")
    public String relation;
    @Column(column = "gmt_create", isDateTime = true)
    public String gmtCreate;
    @Column(column = "gmt_modified", isDateTime = true)
    public String gmtModified;
}
