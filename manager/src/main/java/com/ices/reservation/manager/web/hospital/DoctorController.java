package com.ices.reservation.manager.web.hospital;

import com.ices.pojo.hospital.Doctor;
import com.ices.reservation.common.sql.BaseController;
import com.ices.reservation.manager.service.hospital.DoctorService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 14:49 2018/4/2 0002
 */
@RestController
@CrossOrigin
@RequestMapping(value = "hospital/doctor")
@Api(tags = {"医生管理接口"})
public class DoctorController extends BaseController<Doctor> {

    @Autowired
    DoctorService doctorService;

    @RequestMapping(value = "addbyexcel", method = RequestMethod.POST)
    public Object addDoctorByExcel(MultipartFile file, String hospitalId){
        return doctorService.addDoctorByExcel(file,hospitalId);
    }

    @RequestMapping(value = "templet", method = RequestMethod.GET)
    public void downloadTemplet(HttpServletResponse response) throws IOException {
        doctorService.downloadTemplet(response);
    }
}
