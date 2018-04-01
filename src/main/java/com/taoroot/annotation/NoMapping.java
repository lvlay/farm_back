package com.taoroot.annotation;

import java.lang.annotation.*;


/**
 * @author: taoroot
 * @date: 2018/3/25
 * @description: 不做映射的字段
 */

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NoMapping {
}