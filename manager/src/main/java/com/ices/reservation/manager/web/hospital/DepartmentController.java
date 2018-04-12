package com.ices.reservation.manager.web.hospital;

import com.ices.pojo.hospital.Department;
import com.ices.reservation.common.sql.BaseController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ny
 * @Date: Created in 14:16 2018/4/12 0012
 */
@RestController
@CrossOrigin
@RequestMapping(value = "hospital/department")
public class DepartmentController extends BaseController<Department>{
}
