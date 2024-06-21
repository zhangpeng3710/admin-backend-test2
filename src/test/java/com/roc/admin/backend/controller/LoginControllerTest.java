package com.roc.admin.backend.controller;

import org.junit.jupiter.api.Test;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.lang.reflect.Method;

/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2024/6/21
 */
class LoginControllerTest {
    @Test
    public void testFunctionExpression() throws SecurityException, NoSuchMethodException {
        //定义2个函数,registerFunction和setVariable都可以，不过从语义上面来看用registerFunction更恰当
        StandardEvaluationContext context = new StandardEvaluationContext();
        Method parseInt = Integer.class.getDeclaredMethod("parseInt", String.class);
        context.registerFunction("parseInt1", parseInt);
        context.setVariable("parseInt2", parseInt);
        ExpressionParser parser = new SpelExpressionParser();
        System.out.println(parser.parseExpression("#parseInt1('3')").getValue(context, int.class));
        System.out.println(parser.parseExpression("#parseInt2('3')").getValue(context, int.class));
        String expression1 = "#parseInt1('3') == #parseInt2('3')";
        boolean result1 = Boolean.TRUE.equals(parser.parseExpression(expression1).getValue(context, boolean.class));
        System.out.println(result1);
    }

}