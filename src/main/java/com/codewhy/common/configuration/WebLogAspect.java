package com.codewhy.common.configuration;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

@Aspect
@Component
@Slf4j
public class WebLogAspect {
    @Pointcut("execution(public * com.codewhy.controller.*.*(..))")//切入点描述 这个是controller包的切入点
    public void controllerLog() {
    }//签名，可以理解成这个切入点的一个名称

    @Around("controllerLog()")
    public Object logBeforeController(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("/n");
        log.info("/n");
        long startTime = System.currentTimeMillis();
         Object proceed = null;
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();//这个RequestContextHolder是Springmvc提供来获得请求的东西
        HttpServletRequest request = ((ServletRequestAttributes) requestAttributes).getRequest();
            //获得方法前面
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            //获取方法的注解
            ApiOperation operation = signature.getMethod().getAnnotation(ApiOperation.class);
            if (operation != null) {
                String value = operation.value();
                log.info("请求接口名称 : " + value);
            }
            // 记录下请求内容
            log.info("################URL : " + request.getRequestURL().toString());
            log.info("################HTTP_METHOD : " + request.getMethod());
            // log.info("################方法名称："+request.)
            log.info("################IP : " + request.getRemoteAddr());
            log.info("################THE 请求参数 : " + Arrays.toString(joinPoint.getArgs()));
            //下面这个getSignature().getDeclaringTypeName()是获取包+类名的   然后后面的joinPoint.getSignature.getName()获取了方法名
            log.info("################CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
            //logger.info("################TARGET: " + joinPoint.getTarget());//返回的是需要加强的目标类的对象
            //logger.info("################THIS: " + joinPoint.getThis());//返回的是经过加强后的代理类的对象
            proceed = joinPoint.proceed();
            log.info("################返回值" + proceed.toString());
            log.info("################耗时 : " + (System.currentTimeMillis() - startTime) + "毫秒");
            return proceed;
    }

    @AfterThrowing(value = "controllerLog()", throwing = "e")
    public void afterThrowing(Exception e) {
        log.info("################异常信息:" + e.getMessage());
    }
}
