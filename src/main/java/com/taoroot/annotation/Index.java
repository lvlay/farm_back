package com.taoroot.annotation;

import java.lang.annotation.*;

/**
 * @author: taoroot
 * @date: 2018/3/25
 * @description: 索引 标志位
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Index {

}