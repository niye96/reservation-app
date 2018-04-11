package com.ices.reservation.manager.web.hospital;

import com.ices.pojo.hospital.Doctor;
import com.ices.reservation.common.sql.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ny
 * @Date: Created in 14:49 2018/4/2 0002
 */
@RestController
@CrossOrigin
@RequestMapping(value = "hospital/doctor")
@Api(tags = {"医生管理接口"})
public class DoctorController extends BaseController<Doctor> {
}
