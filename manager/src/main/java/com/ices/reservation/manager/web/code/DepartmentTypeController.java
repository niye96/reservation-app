package com.ices.reservation.manager.web.code;

import com.ices.pojo.code.DepartmentType;
import com.ices.reservation.common.sql.BaseController;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ny
 * @Date: Created in 9:10 2018/3/25 0025
 */
@RestController
@RequestMapping(value = "code/departmenttype")
@CrossOrigin
@Api(tags = {"科室分类接口"})
public class DepartmentTypeController extends BaseController<DepartmentType> {
}
