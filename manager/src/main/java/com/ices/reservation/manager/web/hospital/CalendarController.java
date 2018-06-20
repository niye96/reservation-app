package com.ices.reservation.manager.web.hospital;

import com.ices.pojo.hospital.Calendar;
import com.ices.reservation.common.sql.BaseController;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.manager.service.hospital.CalendarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 9:55 2018/5/6 0006
 */
@RestController
@CrossOrigin
@RequestMapping(value = "hospital/calendar")
@Api(tags = {"医院接诊日历表接口"})
public class CalendarController extends BaseController<Calendar> {

    @Autowired
    CalendarService calendarService;

    @Override
    @RequestMapping(value = "pagelist", method = {RequestMethod.POST})
    public Object getPageListUsedByBase(@RequestBody Calendar clz) {
        return calendarService.getPageListByCondition(clz);
    }

    @RequestMapping(value = "getbydate", method = {RequestMethod.POST})
    public Object getCalendarByDate(@RequestBody Map map){
        return calendarService.getCalendarByDate(ClassUtil.mapToClass((Map) map.get("calendar"), Calendar.class),
                (String)map.get("begin"), (String)map.get("end"));
    }

    @RequestMapping(value = "getselect", method = {RequestMethod.POST})
    public Object getSelectCalendar(@RequestBody Calendar calendar){
        return calendarService.getSelectCalendar(calendar);
    }
}
