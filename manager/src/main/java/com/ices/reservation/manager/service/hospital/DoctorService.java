package com.ices.reservation.manager.service.hospital;

import com.ices.pojo.hospital.Doctor;
import com.ices.pojo.system.Buser;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import com.ices.reservation.common.utils.ExcelUtil;
import com.ices.reservation.common.utils.IdUtil;
import com.ices.reservation.common.utils.ReturnUtil;
import com.ices.reservation.manager.service.system.BuserService;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 14:49 2018/4/2 0002
 */
@Service
public class DoctorService extends BaseService<Doctor> {
    @Autowired
    BuserService buserService;

    @Override
    @Transactional
    public Object addOneUsedByBase(Doctor clz) {
        clz.setDoctorId(IdUtil.generateId());
        try {
            Buser buser = new Buser();
            buser.setLoginId(clz.getLoginId());
            buser.setLoginPwd("123456");
            buser.setRoleId(3L);
            buser.setUserName(clz.getDoctorName());
            // 判断是否已存在此用户名
            Object result = buserService.addOneUsedByBase(buser);
            if((boolean)((Map)result).get("status") == false){
                return result;
            }
            this.baseDao.insertOne(clz);
            return ReturnUtil.success(clz);
        } catch (DuplicateKeyException var3) {
            throw var3;
        } catch (Exception var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    @Override
    @Transactional
    public Object deleteUsedByBase(Doctor doctor) {
        try {
            Map d = (Map) ((Map) detailUsedByBase(doctor)).get("data");
            if(d == null) throw new RuntimeException();
            Buser buser = new Buser();
            buser.setLoginId((String) d.get("loginId"));
            buserService.deleteUsedByBase(buser);
            this.baseDao.deleteByObject(doctor);
            return ReturnUtil.success("删除成功");
        } catch (Exception var4) {
            throw new RuntimeException(var4.getMessage());
        }
    }

    @Override
    @Transactional
    public Object deleteAllUsedByBase(List<Doctor> list) {
        try {
            Buser buser = new Buser();
            Map d;
            for(Doctor t : list) {
                d = (Map) ((Map) detailUsedByBase(t)).get("data");
                if(d == null) throw new RuntimeException();
                buser.setLoginId((String) d.get("loginId"));
                buserService.deleteUsedByBase(buser);
                this.baseDao.deleteByObject(t);
            }
            return ReturnUtil.success("删除成功");
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }
    }

    public void downloadTemplet(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        createTitle(sheet);
        String fileName = "doctorTemplet.xls";
        response.setContentType("application/octet-stream");
        response.setHeader("Content-disposition", "attachment;filename=" + fileName);
        response.flushBuffer();
        workbook.write(response.getOutputStream());
    }


    /***
     * 创建表头
     * @param sheet
     */
    private void createTitle(HSSFSheet sheet)
    {
        HSSFRow row = sheet.createRow(0);
        //设置列宽，setColumnWidth的第二个参数要乘以256，这个参数的单位是1/256个字符宽度
        sheet.setColumnWidth(0, 20*256);
        sheet.setColumnWidth(1, 10*256);
        sheet.setColumnWidth(2, 20*256);
        sheet.setColumnWidth(3, 10*256);
        sheet.setColumnWidth(4, 10*256);
        sheet.setColumnWidth(5, 80*256);
        sheet.setColumnWidth(6, 80*256);


        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("登录id");

        cell = row.createCell(1);
        cell.setCellValue("科室类型id");

        cell = row.createCell(2);
        cell.setCellValue("医生名称");

        cell = row.createCell(3);
        cell.setCellValue("性别(0-男，1-女)");

        cell = row.createCell(4);
        cell.setCellValue("医生头衔(0-主治医师，1-医生，2-住院医生)");

        cell = row.createCell(5);
        cell.setCellValue("擅长");

        cell = row.createCell(6);
        cell.setCellValue("介绍");
    }

    @Transactional
    public Object addDoctorByExcel(MultipartFile file, String hospitalId){
        File tempFile = null;
        try {
            // 用uuid作为文件名，防止生成的临时文件重复
            tempFile = File.createTempFile(IdUtil.getUUID(), "temp");
            file.transferTo(tempFile);
        } catch (IOException e) {
            e.printStackTrace();
        }


        List<List> list = ExcelUtil.readExcel(tempFile);
        Doctor doctor = new Doctor();
        try {
            for(int i = 1; i < list.size(); i++) {
                List<String> item = list.get(i);
                doctor.setLoginId(item.get(0));
                doctor.setTypeId(Long.valueOf(item.get(1)));
                doctor.setDoctorName(item.get(2));
                doctor.setSex(item.get(3));
                doctor.setDoctorTitle(item.get(4));
                doctor.setSkill(item.get(5));
                doctor.setIntroduction(item.get(6));
                doctor.setHospitalId(hospitalId);
                addOneUsedByBase(doctor);
            }
        } catch (Exception var3) {
            throw new RuntimeException(var3.getMessage());
        }

        if(tempFile != null) {
            tempFile.delete();
        }
        return ReturnUtil.success("新增成功");
    }

}
