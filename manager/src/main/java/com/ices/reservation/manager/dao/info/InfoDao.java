package com.ices.reservation.manager.dao.info;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 21:19 2018/5/7 0007
 */
@Mapper
public interface InfoDao {
    @Select(value = "select count(*) as num from hospital_info group by is_valid order by is_valid")
    List<Map> getHostpitalStatus();

    @SelectProvider(
            type = InfoProvider.class,
            method = "getReservationStatus"
    )
    List<Map> getReservationStatus(@Param("start") String start, @Param("end") String end);

    @Select(value = "select a.address_name as addressName, count(1) as num from hospital_info h, addr_code a " +
            "where h.province = a.address_id group by a.address_name")
    List<Map> getHospitalAddr();
}
