package com.ices.reservation.common.sql;

import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 20:52 2018/3/22 0022
 */
public interface BaseDao<T> {
    @InsertProvider(
            type = BaseProvider.class,
            method = "getInsertOne"
    )
    int insertOne(@Param("clz") T var1);

    @InsertProvider(
            type = BaseProvider.class,
            method = "getInsertAll"
    )
    int insertAll(@Param("list") List<T> var1);

    @UpdateProvider(
            type = BaseProvider.class,
            method = "getUpdate"
    )
    int updateById(@Param("clz") T var1);

    @UpdateProvider(
            type = BaseProvider.class,
            method = "getUpdateByWhere"
    )
    int updateByWhere(@Param("clz") T var1, @Param("where") String var2);

    @DeleteProvider(
            type = BaseProvider.class,
            method = "getDeleteByWhere"
    )
    int deleteByObject(@Param("clz") T var1);

    @SelectProvider(
            type = BaseProvider.class,
            method = "getSelect"
    )
    Map selectOneByObject(@Param("clz") T var1);

    @SelectProvider(
            type = BaseProvider.class,
            method = "getSelectByWhere"
    )
    List<Map> selectByWhere(@Param("clz") T var1, @Param("where") String var2);

    @SelectProvider(
            type = BaseProvider.class,
            method = "getSelectPageList"
    )
    List<Map> selectList(@Param("clz") T var1, @Param("order") String var2);

    @SelectProvider(
            type = BaseProvider.class,
            method = "getSelectNoPageList"
    )
    List<Map> selectNoPageList(@Param("clz") T var1, @Param("order") String var2);

    @SelectProvider(
            type = BaseProvider.class,
            method = "selectCount"
    )
    Integer selectCount(@Param("clz") T var1);
}
