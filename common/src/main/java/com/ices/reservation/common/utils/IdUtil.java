package com.ices.reservation.common.utils;

import java.util.Date;
import java.util.Random;

/**
 * @Author: ny
 * @Date: Created in 20:56 2018/3/22 0022
 */
public class IdUtil {
    private static Random random = new Random();
    public static String generateId(){
        return "" + new Date().getTime() + random.nextInt(10000000);
    }
}
