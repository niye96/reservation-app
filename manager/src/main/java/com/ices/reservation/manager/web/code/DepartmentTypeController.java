package com.ices.reservation.manager.web.code;

import com.ices.pojo.code.DepartmentType;
import com.ices.reservation.common.sql.BaseController;
import com.ices.reservation.manager.service.code.DepartmentTypeService;
import com.sun.deploy.net.HttpResponse;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: ny
 * @Date: Created in 9:10 2018/3/25 0025
 */
@RestController
@RequestMapping(value = "code/departmenttype")
@CrossOrigin
@Api(tags = {"科室分类接口"})
public class DepartmentTypeController extends BaseController<DepartmentType> {

    @Autowired
    DepartmentTypeService departmentTypeService;

    @RequestMapping(value = "downloadExcel", method = RequestMethod.GET)
    public void downloadAllDepartmentType(HttpServletResponse response){
        try {
            departmentTypeService.downloadAllDepartmentType(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
