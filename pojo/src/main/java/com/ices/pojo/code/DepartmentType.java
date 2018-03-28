package com.ices.pojo.code;

import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.Table;
import com.ices.reservation.common.utils.Page;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author: ny
 * @Date: Created in 9:06 2018/3/25 0025
 */
@Getter
@Setter
@Table(name = "department_code")
public class DepartmentType extends Page{
    @Column(column = "department_type_id", isId = true)
    public Long departmentTypeId;
    @Column(column = "department_type_name", isUseLike = true)
    public String departmentTypeName;
    @Column(column = "remark")
    public String remark;
}
