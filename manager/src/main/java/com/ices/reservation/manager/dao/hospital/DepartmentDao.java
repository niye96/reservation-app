package com.ices.reservation.manager.dao.hospital;

import com.ices.pojo.hospital.Department;
import com.ices.reservation.common.sql.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ny
 * @Date: Created in 14:15 2018/4/12 0012
 */
@Mapper
public interface DepartmentDao extends BaseDao<Department> {
}
