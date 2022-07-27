package com.skyline.skysmart.core.aspect;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * [FEATURE INFO]<br/>
 * web log aspect
 *
 * @author Skyline
 * @create 2022/7/27 15:25
 * @since 1.0.0
 */
@Aspect
@Component
public class WebLogAspect {

    private final static Logger LOGGER = LogManager.getLogger(WebLogAspect.class);

    @Pointcut("execution(public * com.skyline.skysmart..*.controller..*.*(..))")
    public void webLog() {}

    @Before("webLog()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        assert attributes != null;
        HttpServletRequest request = attributes.getRequest();

        LOGGER.info("==============================start==============================");
        LOGGER.info("URL                : {}", request.getRequestURL().toString());
        LOGGER.info("HTTP Method        : {}", request.getMethod());
        LOGGER.info("Class Method       : {}.{}", joinPoint.getSignature().getDeclaringTypeName(), joinPoint.getSignature().getName());
        LOGGER.info("IP                 : {}", request.getRemoteAddr());

        try {
            Object requestParam = joinPoint.getArgs();
            LOGGER.info("Param              : {}", JSONObject.toJSONString(requestParam, SerializerFeature.IgnoreNonFieldGetter));
        } catch (Exception e) {
            LOGGER.info("Param print failed : {}", e.getMessage());
        }
    }

    @Around("webLog()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object result = proceedingJoinPoint.proceed();
        LOGGER.info("return value       : {}", JSONObject.toJSONString(result, SerializerFeature.IgnoreNonFieldGetter));
        LOGGER.info("time consuming     : {} ms", System.currentTimeMillis() - startTime);
        LOGGER.info("============================== end ==============================");
        return result;
    }

}