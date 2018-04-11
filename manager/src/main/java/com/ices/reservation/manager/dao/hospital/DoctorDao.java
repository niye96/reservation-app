package com.ices.reservation.manager.dao.hospital;

import com.ices.pojo.hospital.Doctor;
import com.ices.reservation.common.sql.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ny
 * @Date: Created in 14:48 2018/4/2 0002
 */
@Mapper
public interface DoctorDao extends BaseDao<Doctor> {
}
