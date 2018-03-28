package com.ices.reservation.common.sql;

import java.lang.annotation.*;

/**
 * @Author: ny
 * @Date: Created in 21:08 2018/3/22 0022
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface RefColumn {
    String refSql() default "";

    String refTb() default "";

    String refCol() default "";

    String refWhereCol() default "";

    String masterCol() default "";
}
