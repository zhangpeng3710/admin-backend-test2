package com.roc.admin.backend.aop.log;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2023/9/8
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationRecord {
    String value() default "";

    String localVar() default "";
}
