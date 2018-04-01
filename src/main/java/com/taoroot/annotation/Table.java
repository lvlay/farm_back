package com.taoroot.annotation;

import java.lang.annotation.*;

/**
 * @author: taoroot
 * @date: 2018/3/25
 * @description: 表名称
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Table {
    String value() default "";
}
