package com.ices.reservation.manager.dao.reservation;

import com.ices.pojo.Reservation;
import com.ices.pojo.hospital.Calendar;
import com.ices.reservation.common.sql.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 16:37 2018/5/6 0006
 */
@Mapper
public interface ReservationDao extends BaseDao<Reservation> {
    @SelectProvider(
            type = ReservationProvider.class,
            method = "getPageListByName"
    )
    List<Map> getPageListByName(@Param("reservation") Reservation reservation);

    @SelectProvider(
            type = ReservationProvider.class,
            method = "countByName"
    )
    Integer countByName(@Param("reservation") Reservation reservation);
}
