package com.ices.reservation.manager.web.hospital;

import com.ices.pojo.hospital.Department;
import com.ices.reservation.common.sql.BaseController;
import com.ices.reservation.manager.service.hospital.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: ny
 * @Date: Created in 14:16 2018/4/12 0012
 */
@RestController
@CrossOrigin
@RequestMapping(value = "hospital/department")
public class DepartmentController extends BaseController<Department>{

    @Autowired
    DepartmentService departmentService;

    @RequestMapping(value = "listbytype", method = RequestMethod.POST)
    public Object listByType(@RequestBody Department department){
        return departmentService.listByType(department);
    }
}
