package com.roc.admin.backend.aop.log;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.LocalVariableTableParameterNameDiscoverer;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Objects;


/**
 * @Description
 * @Author: Zhang Peng
 * @Date: 2023/9/8
 */
@Aspect
@Component
@Slf4j
public class OperationRecordAspect {
    /**
     * SpEL表达式解析器
     */
    private final SpelExpressionParser parser = new SpelExpressionParser();
    /**
     * SpEL表达式标识符
     */
    public final String SPEL_FLAG = "#";

    @AfterReturning(value = "@annotation(com.roc.admin.backend.aop.log.OperationRecord)", returning = "rv")
    public void log(JoinPoint pjp, Object rv) throws Throwable {
        System.out.println(rv);
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // 获取类名
        String className = pjp.getTarget().getClass().getName();
        // 获取方法名
        String methodName = method.getName();
        // 获取参数
        Object[] args = pjp.getArgs();
        // 读取参数名
        LocalVariableTableParameterNameDiscoverer l = new LocalVariableTableParameterNameDiscoverer();
        String[] paramNames = l.getParameterNames(method);

        OperationRecord annotation = method.getAnnotation(OperationRecord.class);
        String value = annotation.value();
        String var = annotation.localVar();

        if (Objects.isNull(args) || Objects.isNull(paramNames)) {
            return;
        }

        if (value.contains(SPEL_FLAG)) {
            // 解析后的SpEL
            Expression expression = parser.parseExpression(value);
            // SpEL表达式上下文
            StandardEvaluationContext context = new StandardEvaluationContext();
            // 给上下文赋值变量
            for (int i = 0; i < paramNames.length; i++) {
                context.setVariable(paramNames[i], args[i]);
            }
            Object result = expression.getValue(context);
        }

        try {
            StandardEvaluationContext context = new StandardEvaluationContext();
            context.setVariable(paramNames[1], args[1]);
            context.registerFunction("test", OperationRecordFunc.class.getDeclaredMethod("test", String.class));
            String result = parser.parseExpression(annotation.localVar()).getValue(context, String.class);
            System.out.println("1");
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

    }

}
