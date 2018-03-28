package com.ices.pojo;


import com.ices.reservation.common.sql.RefColumn;
import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.Table;
import com.ices.reservation.common.utils.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ny
 * @Date: Created in 21:30 2018/3/23 0023
 */
@Getter
@Setter
@Table(name = "user_info")
public class User extends Page{
    @Column(column = "user_phone", isId = true)
    public String userPhone;
    @Column(column = "user_name", isUseLike = true)
    public String userName;
    @Column(column = "sex")
    public String sex;
    @Column(column = "province")
    public Integer province;
    @RefColumn(refSql = "(select adName from addr_code where hid = province)")
    public String provinceName;
    @Column(column = "city")
    public Integer city;
    @RefColumn(refSql = "(select adName from addr_code where hid = city)")
    public String cityName;
    @Column(column = "county")
    public Integer county;
    @RefColumn(refSql = "(select adName from addr_code where hid = county)")
    public String countyName;
    @Column(column = "detail_addr")
    public String detailAddr;
    @Column(column = "user_pwd")
    public String userPwd;
    @Column(column = "gmt_create", isDateTime = true)
    public String gmtCreate;
    @Column(column = "gmt_modified", isDateTime = true)
    public String gmtModified;
}
