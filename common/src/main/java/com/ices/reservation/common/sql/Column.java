package com.ices.reservation.common.sql;

import java.lang.annotation.*;

/**
 * @Author: ny
 * @Date: Created in 21:08 2018/3/22 0022
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface Column {
    String column() default "";

    boolean isUpdateSet() default true;

    boolean isSelectSet() default true;

    boolean isId() default false;

    boolean isDateTime() default false;

    boolean isUseLike() default false;
}