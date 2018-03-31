package com.ices.reservation.common.sql;

import com.ices.reservation.common.utils.ReturnUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 20:52 2018/3/22 0022
 */
public class BaseService<T> {
    @Autowired
    public BaseDao<T> baseDao;

    @Transactional
    public Object addOneUsedByBase(T clz) {
        try {
            this.beforeAdd(clz);
            this.baseDao.insertOne(clz);
            return ReturnUtil.success(clz);
        } catch (DuplicateKeyException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    @Transactional
    public Object updateUsedByBase(T clz) {
        try {
            this.beforeUpdate(clz);
            this.baseDao.updateById(clz);
            return ReturnUtil.success(clz);
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    @Transactional
    public Object deleteUsedByBase(T clz) {
        try {
            this.beforeDelete(clz);
            this.baseDao.deleteByObject(clz);
            return ReturnUtil.success("删除成功");
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    @Transactional
    public Object deleteAllUsedByBase(List<T> list) {
        try {
            for(T t : list) {
                this.beforeDelete(t);
                this.baseDao.deleteByObject(t);
            }
            return ReturnUtil.success("删除成功");
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public Object detailUsedByBase(T clz) {
        this.beforeDetail(clz);
        Map re = this.baseDao.selectOneByObject(clz);
        this.afterDetail(re);
        return re != null ? ReturnUtil.success(re) : ReturnUtil.error("获取详情失败");
    }

    public Object getPageListUsedByBase(T clz, String order) {
        this.beforePageList(clz);
        List<Map> re = this.baseDao.selectList(clz, order);
        this.afterPageList(re);
        return re != null ? ReturnUtil.success(re, this.baseDao.selectCount(clz)) : ReturnUtil.error("查询失败");
    }

    public Object getCount(T clz) {
        this.beforeCount(clz);
        Integer re = this.baseDao.selectCount(clz);
        return re != null ? ReturnUtil.success(re) : ReturnUtil.error("查询失败");
    }

    public Object getListUsedByBase(T clz, String order) {
        this.beforeNoPageList(clz);
        List<Map> re = this.baseDao.selectNoPageList(clz, order);
        this.afterNoPageList(re);
        return re != null ? ReturnUtil.success(re) : ReturnUtil.error("查询失败");
    }

    protected void beforeCount(T clz) {
    }

    protected void beforeAdd(T clz) throws RuntimeException {
    }

    protected void beforeUpdate(T clz) throws RuntimeException {
    }

    protected void beforeDelete(T clz) throws RuntimeException {
    }

    protected void beforeDetail(T clz) throws RuntimeException {
    }

    protected void beforeNoPageList(T clz) throws RuntimeException {
    }

    protected void beforePageList(T clz) throws RuntimeException {
    }

    protected void afterDetail(Map map) throws RuntimeException {
    }

    protected void afterNoPageList(List<Map> map) throws RuntimeException {
    }

    protected void afterPageList(List<Map> map) throws RuntimeException {
    }
}