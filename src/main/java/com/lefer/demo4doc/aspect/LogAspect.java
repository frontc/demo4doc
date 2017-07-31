package com.lefer.demo4doc.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lefer.demo4doc.common.Result;
import io.swagger.annotations.ApiOperation;
import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.Map;

/**
 * @author fang
 * @creatdate 17-7-31
 */
@Component
@Aspect
public class LogAspect {
    private Logger logger = Logger.getLogger(getClass());

    ThreadLocal<Long> startTime = new ThreadLocal<>();

    //@Pointcut("execution(* com.test.spring.aop.pointcutexp..JoinPointObjP2.*(..))")
    //@Pointcut("within(com.test.spring.aop.pointcutexp..*)")
    //@Pointcut("this(com.test.spring.aop.pointcutexp.Intf)")
    //@Pointcut("target(com.test.spring.aop.pointcutexp.Intf)")
    //@Pointcut("@within(org.springframework.transaction.annotation.Transactional)")
    //@Pointcut("@annotation(org.springframework.transaction.annotation.Transactional)")
    //@Pointcut("args(String)")
    @Pointcut("execution(public * com.lefer.demo4doc.controller..*.*(..))")
    public void webLog() {
    }

    //在切入点前的操作，按order的值由小到大执行
    //在切入点后的操作，按order的值由大到小执行
    @Before("webLog()")
    @Order(5)
    public void doBefore(JoinPoint joinPoint) throws Throwable {
        startTime.set(System.currentTimeMillis());

        // 接收到请求，记录请求内容
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        // 记录下请求内容
        // 调用方法名
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        logger.info(method.getAnnotation(ApiOperation.class).value());
        //被调用的URL
        logger.info("URL : " + request.getRequestURL().toString());
        logger.info("HTTP_METHOD : " + request.getMethod());
        //调用者的IP
        logger.info("IP : " + request.getRemoteAddr());
        //调用的类和方法
        logger.info("CLASS_METHOD : " + joinPoint.getSignature().getDeclaringTypeName() + "." + joinPoint.getSignature().getName());
        //logger.info("ARGS : " + Arrays.toString(joinPoint.getArgs()));
        //请求参数和值
        Map map = request.getParameterMap();
        Iterator iterator = map.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry entry = (Map.Entry) iterator.next();
            Object objectValue = entry.getValue();
            if (null == objectValue) {
                logger.info("参数名:" + entry.getKey() + " 参数值: ");
            } else if (objectValue instanceof String[]) {
                String[] values = (String[]) objectValue;
                StringBuilder sb = new StringBuilder();
                for (String val : values) {
                    sb.append(val);
                }
                String value = sb.toString();
                logger.info("ARGS : " + entry.getKey() + " : " + value);
            }
        }
    }

    @AfterReturning(returning = "ret", pointcut = "webLog()")
    public void doAfter(Result ret) throws Throwable {
        // 处理完请求，返回内容
        logger.info("RESPONSE : " + new ObjectMapper().writeValueAsString(ret));
        logger.info("SPEND TIME : " + (System.currentTimeMillis() - startTime.get()));
    }
}
