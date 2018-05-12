package com.ices.pojo.hospital;

import com.ices.reservation.common.sql.Column;
import com.ices.reservation.common.sql.RefColumn;
import com.ices.reservation.common.sql.Table;
import com.ices.reservation.common.utils.Page;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @Author: ny
 * @Date: Created in 14:10 2018/4/12 0012
 */
@Getter
@Setter
@Table(name = "hospital_department")
public class Department extends Page{
    @Column(column = "department_id", isId = true)
    public String departmentId;
    @Column(column = "hospital_id")
    public String hospitalId;
    @NotNull(message = "请选择科室分类")
    @Column(column = "type_id")
    public Long typeId;
    @RefColumn(refSql = ("(select department_type_name from department_code " +
            "where department_type_id = typeId)"))
    public String typeName;
    @NotEmpty(message = "请输入科室名称")
    @Column(column = "department_name", isUseLike = true)
    public String departmentName;
}
