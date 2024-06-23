package com.roc.admin.backend.aop.log;

import com.roc.admin.backend.dao.entity.RbacUser;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
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
     * 方法参数名解析器
     */
    private final DefaultParameterNameDiscoverer nameDiscoverer = new DefaultParameterNameDiscoverer();
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
        MethodSignature methodSignature = (MethodSignature) pjp.getSignature();
        Method method = methodSignature.getMethod();
        // parameter value
        Object[] args = pjp.getArgs();
        // parameter name
        String[] paramNames = nameDiscoverer.getParameterNames(method);

        OperationRecord annotation = method.getAnnotation(OperationRecord.class);
        String value = annotation.value();
        String func = annotation.func();

        // create SpEL context and add variables
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < Objects.requireNonNull(paramNames).length; i++) {
            context.setVariable(paramNames[i], args[i]);
        }
        // register functions to context
        context.registerFunction("test", OperationRecordFunc.class.getDeclaredMethod("test", String.class));
        context.registerFunction("getUser", OperationRecordFunc.class.getDeclaredMethod("getUser", String.class));

        Object valueResult = parser.parseExpression(value).getValue(context, String.class);
        Object funcResult = parser.parseExpression(func).getValue(context, RbacUser.class);


    }

}
