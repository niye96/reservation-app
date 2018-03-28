package com.ices.pojo.system;

import com.ices.reservation.common.sql.RefColumn;
import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.Table;
import com.ices.reservation.common.utils.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ny
 * @Date: Created in 10:02 2018/3/27 0027
 */
@Getter
@Setter
@Table(name = "buser_info")
public class Buser extends Page{
    @Column(column = "login_id", isId = true)
    public String loginId;
    @Column(column = "user_name", isUseLike = true)
    public String userName;
    @Column(column = "role_id")
    public Long roleId;
    @RefColumn(refSql = "(select role_name from role where role_id = roleId)")
    public String roleName;
    @Column(column = "login_pwd")
    public String loginPwd;
}
