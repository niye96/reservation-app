package com.ices.reservation.manager.service.hospital;

import com.ices.pojo.hospital.Hospital;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.IdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.dao.hospital.HospitalDao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 20:22 2018/3/31 0031
 */
@Service
public class HospitalService extends BaseService<Hospital> {
    @Autowired
    HospitalDao hospitalDao;

    @Override
    protected void beforeAdd(Hospital clz) throws RuntimeException {
        clz.setHospitalId(IdUtil.generateId());
    }

    public Object getHostpitalDept(Hospital hospital){
        if(StringUtils.isEmpty(hospital.getHospitalId()))
            return ReturnUtil.error("医院Id不能为空");
        List<Map> re = hospitalDao.getHospitalDepartmentType(hospital.getHospitalId());
        return re == null ? ReturnUtil.error("查询失败") : ReturnUtil.success(re);
    }
}
