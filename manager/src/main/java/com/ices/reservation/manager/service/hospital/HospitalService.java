package com.ices.reservation.manager.service.hospital;

import com.ices.pojo.hospital.Hospital;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.IdUtil;
import org.springframework.stereotype.Service;

/**
 * @Author: ny
 * @Date: Created in 20:22 2018/3/31 0031
 */
@Service
public class HospitalService extends BaseService<Hospital> {
    @Override
    protected void beforeAdd(Hospital clz) throws RuntimeException {
        clz.setHospitalId(IdUtil.generateId());
    }
}
