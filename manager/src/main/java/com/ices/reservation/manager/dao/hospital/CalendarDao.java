package com.ices.reservation.manager.dao.hospital;

import com.ices.pojo.hospital.Calendar;
import com.ices.reservation.common.sql.BaseDao;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 9:45 2018/5/6 0006
 */
@Mapper
public interface CalendarDao extends BaseDao<Calendar> {
    @SelectProvider(
            type = CalendarProvider.class,
            method = "getListByName"
    )
    List<Map> getListByName(@Param("calendar") Calendar calendar);

    @SelectProvider(
            type = CalendarProvider.class,
            method = "countListByName"
    )
    Integer countListByName(@Param("calendar") Calendar calendar);

}
