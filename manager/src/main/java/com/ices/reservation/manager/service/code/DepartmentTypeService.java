package com.ices.reservation.manager.service.code;

import com.ices.pojo.code.DepartmentType;
import com.ices.reservation.common.sql.BaseService;
import com.ices.reservation.common.utils.ClassUtil;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * @Author: ny
 * @Date: Created in 9:10 2018/3/25 0025
 */
@Service
public class DepartmentTypeService extends BaseService<DepartmentType> {
    public void downloadAllDepartmentType(HttpServletResponse response) throws IOException {
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("信息表");
        createTitle(sheet);
        List<Map> list = this.baseDao.selectNoPageList(new DepartmentType(), null);

        String fileName = "departmentType.xls";
        //新增数据行，并且设置单元格数据
        int rowNum = 1;
        for (Map type: list) {
            HSSFRow row = sheet.createRow(rowNum);
            DepartmentType departmentType = ClassUtil.mapToClass(type, DepartmentType.class);
            row.createCell(0).setCellValue(departmentType.getDepartmentTypeId());
            row.createCell(1).setCellValue(departmentType.getDepartmentTypeName());
            rowNum++;
        }

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
        sheet.setColumnWidth(0, 10*256);
        sheet.setColumnWidth(1, 10*256);

        HSSFCell cell;
        cell = row.createCell(0);
        cell.setCellValue("科室id");

        cell = row.createCell(1);
        cell.setCellValue("科室名称");
    }
}
