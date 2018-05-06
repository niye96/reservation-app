package com.ices.reservation.manager.dao;

import com.ices.pojo.Reservation;
import com.ices.reservation.common.sql.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ny
 * @Date: Created in 16:37 2018/5/6 0006
 */
@Mapper
public interface ReservationDao extends BaseDao<Reservation> {
}
