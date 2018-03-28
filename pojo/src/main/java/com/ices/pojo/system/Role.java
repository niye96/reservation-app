package com.ices.pojo.system;

import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.Table;
import com.ices.reservation.common.utils.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ny
 * @Date: Created in 10:10 2018/3/27 0027
 */
@Getter
@Setter
@Table(name = "role")
public class Role extends Page {
    @Column(column = "role_id", isId = true)
    public Long roleId;
    @Column(column = "role_name")
    public String roleName;
}
