package com.ices.reservation.common.utils;


import sun.misc.BASE64Encoder;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @Author: ny
 * @Date: Created in 21:35 2018/3/23 0023
 */
public class PwdUtil {
    public static String encrypt(String pwd)throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest md5= null;
        md5 = MessageDigest.getInstance("MD5");
        BASE64Encoder base64en = new BASE64Encoder();
        String newstr=base64en.encode(md5.digest(pwd.getBytes("utf-8")));
        return newstr;
    }

    public static void main(String[] args) {
        try {
            System.out.println(PwdUtil.encrypt("123456"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
