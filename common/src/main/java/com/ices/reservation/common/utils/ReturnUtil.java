package com.ices.reservation.common.utils;

import java.util.HashMap;

/**
 * @Author: ny
 * @Date: Created in 20:56 2018/3/22 0022
 */
public class ReturnUtil {
    public static final String RETURN_MESSAGE = "message";
    public static final String STATUS = "status";
    public static final String RETURN_DATA = "data";
    public static final String MESSAGE_CODE = "messagecode";
    public static final String SUCCESS_MESSAGE = "success";
    public static final String NEED_MESSAGE = "need params";
    public static final String TOTAL = "total";

    public ReturnUtil() {
    }

    public static HashMap<String, Object> success(Object returnData, Integer total) {
        HashMap<String, Object> resultDataMap = new HashMap();
        resultDataMap.put("message", "success");
        resultDataMap.put("status", true);
        resultDataMap.put("data", returnData);
        resultDataMap.put("total", total);
        return resultDataMap;
    }

    public static HashMap<String, Object> success(Object returnData) {
        HashMap<String, Object> resultDataMap = new HashMap();
        resultDataMap.put("message", "success");
        resultDataMap.put("status", true);
        resultDataMap.put("data", returnData);
        return resultDataMap;
    }

    public static HashMap<String, Object> success() {
        return success((Object)null);
    }

    public static HashMap<String, Object> needParam(String... paramNames) {
        HashMap<String, Object> resultDataMap = new HashMap();
        resultDataMap.put("status", false);
        StringBuffer msg = new StringBuffer("need params");
        String[] var3 = paramNames;
        int var4 = paramNames.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            String paramName = var3[var5];
            msg.append(paramName).append(";");
        }

        resultDataMap.put("message", msg);
        resultDataMap.put("data", (Object)null);
        return resultDataMap;
    }

    public static HashMap<String, Object> error(String errorMessage, Object returnData) {
        HashMap<String, Object> resultDataMap = new HashMap();
        resultDataMap.put("status", false);
        resultDataMap.put("message", errorMessage);
        resultDataMap.put("messagecode", "0");
        resultDataMap.put("data", returnData);
        return resultDataMap;
    }

    public static HashMap<String, Object> error(String errorMessage) {
        return error(errorMessage, (Object)null);
    }

    public static HashMap<String, Object> sdkSuccess(String message, String messageCode, Object data) {
        return returnData(true, message, messageCode, data);
    }

    public static HashMap<String, Object> sdkError(String message, String messageCode, Object data) {
        return returnData(false, message, messageCode, data);
    }

    public static HashMap<String, Object> sdkSuccess(Object data) {
        return returnData(true, "success", "1", data);
    }

    public static HashMap<String, Object> sdkError(Object data) {
        return returnData(false, "error", "0", data);
    }

    public static HashMap<String, Object> returnData(Boolean flag, String message, String messageCode, Object data) {
        HashMap<String, Object> resultDataMap = new HashMap();
        resultDataMap.put("status", flag);
        resultDataMap.put("message", message);
        resultDataMap.put("messagecode", messageCode);
        resultDataMap.put("data", data);
        return resultDataMap;
    }
}
