package com.taoroot.util;

import com.google.common.collect.Lists;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * @author: taoroot
 * @date: 2018/3/26
 * @description:
 */
public class ReflectionUtil {
    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param c : class
     * @return 父类中的属性对象
     */
    public static List<Field> getDeclaredFields(Class<?> c) {
        List<Field> fields = new ArrayList<>();
        try {
            if (c.getSuperclass() != null) {
                if (c.getSuperclass().getSuperclass() != null) {
                    for (Field field : c.getSuperclass().getSuperclass().getDeclaredFields()) {
                        fields.add(field);
                    }
                }
                for (Field field : c.getSuperclass().getDeclaredFields()) {
                    fields.add(field);
                }
                for (Field field : c.getDeclaredFields()) {
                    fields.add(field);
                }
            } else {
                for (Field field : c.getDeclaredFields()) {
                    fields.add(field);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return fields;

    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param object    : 子类对象
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */
    public static Field getDeclaredField(Object object, String fieldName) {
        Field field = null;
        Class<?> clazz = object.getClass();
        for (; clazz != Object.class; clazz = clazz.getSuperclass()) {
            try {
                field = clazz.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }

    /**
     * 循环向上转型, 获取对象的 DeclaredField
     *
     * @param c         class
     * @param fieldName : 父类中的属性名
     * @return 父类中的属性对象
     */
    public static Field getDeclaredField(Class<?> c, String fieldName) {
        Field field = null;

        for (; c != Object.class; c = c.getSuperclass()) {
            try {
                field = c.getDeclaredField(fieldName);
                return field;
            } catch (Exception e) {
                //这里甚么都不要做！并且这里的异常必须这样写，不能抛出去。
                //如果这里的异常打印或者往外抛，则就不会执行clazz = clazz.getSuperclass(),最后就不会进入到父类中了
            }
        }
        return null;
    }

    /**
     * 根据列名获取属性值
     *
     * @param object
     * @param fieldName
     * @return
     */
    public static Object getValue(Object object, String fieldName) {
        try {
            Field field = getDeclaredField(object, fieldName);
            field.setAccessible(true);
            return field.get(object);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 根据注解类获取实体中第一个拥有注解的字段名
     *
     * @param c
     * @return
     */
    public static String getColumn(Class<?> c, Class annotaion) {
        try {
            for (Field f : ReflectionUtil.getDeclaredFields(c)) {
                String filedName = f.getName();
                Annotation annotation = f.getAnnotation(annotaion);
                //不更新不映射的属性
                if (annotation != null) {
                    return filedName;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据注解类获取实体中拥有注解的字段名
     *
     * @param c
     * @return
     */
    public static List<String> getColumnList(Class<?> c, Class annotaion) {
        List<String> list = Lists.newArrayList();
        try {
            for (Field f : ReflectionUtil.getDeclaredFields(c)) {
                String filedName = f.getName();
                Annotation annotation = f.getAnnotation(annotaion);
                //不更新不映射的属性
                if (annotation != null) {
                    list.add(filedName);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
