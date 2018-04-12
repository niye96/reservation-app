package com.ices.reservation.manager.service.hospital;

import com.ices.pojo.hospital.Department;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.IdUtil;
import org.springframework.stereotype.Service;

/**
 * @Author: ny
 * @Date: Created in 14:16 2018/4/12 0012
 */
@Service
public class DepartmentService extends BaseService<Department>{
    @Override
    protected void beforeAdd(Department clz) throws RuntimeException {
        clz.setDepartmentId(IdUtil.generateId());
    }
}
