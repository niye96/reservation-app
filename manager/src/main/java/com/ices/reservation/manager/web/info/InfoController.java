package com.ices.reservation.manager.web.info;

import com.ices.reservation.manager.service.info.InfoService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: ny
 * @Date: Created in 20:39 2018/5/7 0007
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/info")
@Api(tags = {"基础信息接口"})
public class InfoController {
    @Autowired
    InfoService infoService;

    @RequestMapping(value = "baseinfo", method = {RequestMethod.POST})
    public Object getBaseInfo(){
        return infoService.getBaseInfo();
    }

    @RequestMapping(value = "hospitalinfo", method = {RequestMethod.POST})
    public Object getHostpitalStatus() {
        return infoService.getHostpitalStatus();
    }

    @RequestMapping(value = "reservationinfo", method = {RequestMethod.POST})
    public Object getReservationStatus(){
        return infoService.getReservationStauts();
    }

    @RequestMapping(value = "hospitaladdr", method = {RequestMethod.POST})
    public Object getHostpitalAddr(){
        return  infoService.getHostpitalAddr();
    }
}
