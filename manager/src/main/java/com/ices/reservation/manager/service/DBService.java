package com.ices.reservation.manager.service;

import com.alibaba.fastjson.JSONObject;
import com.ices.reservation.common.utils.ReturnUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.util.WebUtils;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: ny
 * @Date: Created in 9:50 2018/6/18 0018
 */
@Service
public class DBService {

    public Object getAllBackup(){
        List res = new ArrayList();
        File file = new File("./backup/");
        File[] list = file.listFiles();
        for(int i = 0; i < list.length; i++){
            if(list[i].getName().endsWith(".sql")) {
                JSONObject obj = new JSONObject();
                obj.put("filename", list[i].getName());
                res.add(obj);
            }
        }
        return ReturnUtil.success(res);
    }

    public Object exportDatabaseTool(String hostIP, String userName, String password, String savePath, String fileName, String databaseName) throws InterruptedException {
        File saveFile = new File(savePath);
        if (!saveFile.exists()) {// 如果目录不存在
            saveFile.mkdirs();// 创建文件夹
        }
        if(!savePath.endsWith(File.separator)){
            savePath = savePath + File.separator;
        }
        PrintWriter printWriter = null;
        BufferedReader bufferedReader = null;
        try {
            printWriter = new PrintWriter(new OutputStreamWriter(new FileOutputStream(savePath + fileName), "utf8"));
            Process process = Runtime.getRuntime().exec(" mysqldump -h" + hostIP + " -u" + userName + " -p" + password + " --set-charset=UTF8 " + databaseName);
            InputStreamReader inputStreamReader = new InputStreamReader(process.getInputStream(), "utf8");
            bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                printWriter.println(line);
            }
            printWriter.flush();
            if(process.waitFor() == 0){//0 表示线程正常终止。
                return ReturnUtil.success();
            }
        }catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bufferedReader != null) {
                    bufferedReader.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return ReturnUtil.error("备份失败");
    }

    public Object restore(String hostIP, String userName, String password, String path, String fileName, String databaseName){
        OutputStream out = null;
        BufferedReader br = null;
        OutputStreamWriter writer = null;

        File file = new File(path+fileName);
        if(!file.exists()) return ReturnUtil.error("文件不存在");

        try {
            Process process = Runtime.getRuntime().exec(" mysql -h" + hostIP + " -u" + userName + " -p" + password + " " + databaseName);
            out = process.getOutputStream();//控制台的输入信息作为输出流
            String inStr;
            StringBuffer sb = new StringBuffer("");
            String outStr;
            br = new BufferedReader(new InputStreamReader(
                    new FileInputStream(path + fileName), "utf8"));
            while ((inStr = br.readLine()) != null) {
                sb.append(inStr + "\r\n");
            }
            outStr = sb.toString();

            writer = new OutputStreamWriter(out, "utf8");
            writer.write(outStr);
            // 注：这里如果用缓冲方式写入文件的话，会导致中文乱码，用flush()方法则可以避免
            writer.flush();
        } catch (Exception e) {
            e.printStackTrace();
            return ReturnUtil.error("还原失败");
        } finally {
            // 别忘记关闭输入输出流
            try {
                out.close();
                br.close();
                writer.close();
            } catch (IOException e) {
                return ReturnUtil.error("还原失败");
            }
        }
        return ReturnUtil.success();
    }
}
