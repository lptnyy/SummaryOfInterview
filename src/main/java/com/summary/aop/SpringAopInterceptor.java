package com.summary.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SpringAopInterceptor {

    //使用org.slf4j.Logger,这是spring实现日志的方法
    private final static Logger logger = LoggerFactory.getLogger(LogAopInterceptor.class);

    /**
     * 拦截 spring 当中的注解
     * @param joinPoint
     */
    @Before(value = "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public void doBefore(JoinPoint joinPoint) {
        logger.info("拦截spring 自带的注解");

        //获取到请求的属性
        ServletRequestAttributes attributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

        //获取到请求对象
        HttpServletRequest request = attributes.getRequest();

        //URL：根据请求对象拿到访问的地址
        logger.info("url=" + request.getRequestURL());

        //获取请求的方法，是Get还是Post请求
        logger.info("method=" + request.getMethod());

        //ip：获取到访问
        logger.info("ip=" + request.getRemoteAddr());
        
        //获取被拦截的类名和方法名
        logger.info("class=" + joinPoint.getSignature().getDeclaringTypeName() +
                "and method name=" + joinPoint.getSignature().getName());
        //参数
        logger.info("参数=" + joinPoint.getArgs().toString());

    }
}
