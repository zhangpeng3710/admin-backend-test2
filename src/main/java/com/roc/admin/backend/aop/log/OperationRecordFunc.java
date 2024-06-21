package com.roc.admin.backend.aop.log;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/6/21
 */
public class OperationRecordFunc {

    //spel self-design method must be static
    public static String test(String input) {
        return "test-" + input;
    }
}
