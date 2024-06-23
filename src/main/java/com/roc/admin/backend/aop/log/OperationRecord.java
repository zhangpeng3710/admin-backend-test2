package com.roc.admin.backend.aop.log;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2023/9/8
 * SpEL tips:
 * if there are more than one variable, use "+" split them, otherwise spel cannot parse
 * e.g. func = "#test(" + "#user.getUserEmail)", the spel parse it as "test("app@gmail.com")
 * if you want to add some string, the string should be surrounded by ''
 * e.g. value = "'user:'+#user.getUserEmail", the spel parse it as "user:app@gmail.com
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface OperationRecord {
    /**
     * comment
     */
    String detail() default "";

    /**
     * value
     */
    String value() default "";

    /**
     * function
     */
    String func() default "";
}
