package com.ices.reservation.manager.dao.hospital;

import com.ices.pojo.hospital.Hospital;
import com.ices.reservation.common.sql.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 20:21 2018/3/31 0031
 */
@Mapper
public interface HospitalDao extends BaseDao<Hospital> {
    @SelectProvider(
            type = HospitalProvider.class,
            method = "getHospitalDepartmentType"
    )
    List<Map> getHospitalDepartmentType(String hospitalId);
}
