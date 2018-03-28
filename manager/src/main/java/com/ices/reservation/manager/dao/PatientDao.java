package com.ices.reservation.manager.dao;

import com.ices.pojo.Patient;
import com.ices.reservation.common.sql.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ny
 * @Date: Created in 9:19 2018/3/24 0024
 */
@Mapper
public interface PatientDao extends BaseDao<Patient> {
}
