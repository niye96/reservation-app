package com.ices.reservation.manager.dao;

import com.ices.pojo.User;
import com.ices.reservation.common.sql.BaseDao;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: ny
 * @Date: Created in 21:30 2018/3/22 0022
 */

@Mapper
public interface UserDao extends BaseDao<User>{
}
