package com.jwm.blog.aspect;

import com.jwm.blog.dto.RequestLog;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class LogAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    //这个注解表示一个切面,execution()表示拦截哪些
    //拦截controller下所有类的方法
    @Pointcut("execution(* com.jwm.blog.controller.*.*(..))")
    public void log(){}

    //在目标方法调用之前执行
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        String url = request.getRequestURL().toString();
        String ip = request.getRemoteAddr();
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        // 输出请求的基本信息
        logger.info("Request: {}", requestLog);
    }

    //在目标方法调用之后执行
    @After("log()")
    public void doAfter(){
        //logger.info("-------doAfter------");
    }

    //捕获目标方法返回的内容
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("Result: {}", result);
    }
}
