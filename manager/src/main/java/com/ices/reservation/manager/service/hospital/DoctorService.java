package com.ices.reservation.manager.service.hospital;

import com.ices.pojo.hospital.Doctor;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.IdUtil;
import org.springframework.stereotype.Service;

/**
 * @Author: ny
 * @Date: Created in 14:49 2018/4/2 0002
 */
@Service
public class DoctorService extends BaseService<Doctor> {
    @Override
    protected void beforeAdd(Doctor clz) throws RuntimeException {
        clz.setDoctorId(IdUtil.generateId());
    }
}
