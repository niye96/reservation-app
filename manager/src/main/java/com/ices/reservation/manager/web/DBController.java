package com.ices.reservation.manager.web;

import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.service.DBService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import java.io.FileNotFoundException;
import java.util.Date;

/**
 * @Author: ny
 * @Date: Created in 9:53 2018/6/18 0018
 */
@RestController
@CrossOrigin
@Api(tags = {"数据库备份接口"})
public class DBController {
    @Autowired
    DBService dbService;
    @Value("${spring.datasource.username}")
    String userName;
    @Value("${spring.datasource.password}")
    String password;
    @Value("${spring.datasource.host}")
    String host;
    @Value("${spring.datasource.database-name}")
    String databaseName;

    @RequestMapping(value = "/db/backup", method = RequestMethod.POST)
    public Object backup(String filename){
        try {
            String path = "./backup";
            return dbService.exportDatabaseTool(host,userName,password,path,filename+".sql",databaseName);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ReturnUtil.error("备份失败");
    }

    @RequestMapping(value = "/db/list", method = RequestMethod.POST)
    public Object getList(){
        return dbService.getAllBackup();
    }

    @RequestMapping(value = "/db/restore", method = RequestMethod.POST)
    public Object restore(String filename){
        String path = "./backup/";
        return dbService.restore(host,userName,password,path,filename,databaseName);
    }
}
