package com.ices.reservation.common.sql;

import java.lang.annotation.*;

/**
 * @Author: ny
 * @Date: Created in 21:08 2018/3/22 0022
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Table {
    String name() default "className";
}