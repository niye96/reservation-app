package com.ices.reservation.manager.web.hospital;

import com.ices.pojo.hospital.Hospital;
import com.ices.reservation.common.sql.BaseController;
import com.ices.reservation.manager.service.hospital.HospitalService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ny
 * @Date: Created in 20:23 2018/3/31 0031
 */
@RestController
@CrossOrigin
@RequestMapping(value = "hospital/hospital")
@Api(tags = {"医院管理接口"})
public class HospitalController extends BaseController<Hospital> {
    @Autowired
    HospitalService hospitalService;

    @RequestMapping(value = "/depttype", method = {RequestMethod.POST})
    public Object getHostpitalDept(@RequestBody Hospital hospital){
        return hospitalService.getHostpitalDept(hospital);
    }
}
