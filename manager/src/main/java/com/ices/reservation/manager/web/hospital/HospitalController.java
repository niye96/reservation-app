package com.ices.reservation.manager.web.hospital;

import com.ices.pojo.hospital.Hospital;
import com.ices.reservation.common.sql.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ny
 * @Date: Created in 20:23 2018/3/31 0031
 */
@RestController
@CrossOrigin
@RequestMapping(value = "hospital/hospital")
@Api(tags = {"医院管理接口"})
public class HospitalController extends BaseController<Hospital> {
}
