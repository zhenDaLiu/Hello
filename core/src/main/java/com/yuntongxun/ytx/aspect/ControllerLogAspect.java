package com.yuntongxun.ytx.aspect;

import com.yuntongxun.ytx.annotation.ControllerLog;
import cn.hutool.json.JSONUtil;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.util.ClassUtils;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * Controller 日志注解 切面
 *
 * @author tangxy
 * @date 2019-05-27
 **/
@Component
@Aspect
@SuppressWarnings("PMD")
public class ControllerLogAspect {

    protected static final Logger LOGGER = LoggerFactory.getLogger(ControllerLogAspect.class);

    @Around("@annotation(com.yuntongxun.ytx.annotation.ControllerLog)")
    public Object logServiceInvoke(ProceedingJoinPoint pjp) throws Throwable {
        return doLog(pjp);
    }

    public Object doLog(ProceedingJoinPoint pjp) throws Throwable {
        if (LOGGER.isInfoEnabled()) {
            ControllerLog serviceLog = printAnnotation(pjp);
            String[] reqExcludes = serviceLog.requestExcludes();
            boolean isPrintRequest = serviceLog.isPrintRequest();
            boolean isPrintResponse = serviceLog.isPrintResponse();
            if (isPrintRequest) {
                // 打印请求参数
                for (Object arg : pjp.getArgs()) {
                    printJsonObj(reqExcludes,arg,"服务参数:");
                }
            }
            try {
                Object retVal = pjp.proceed();
                String [] rspExcludes = serviceLog.responseExcludes();
                if (isPrintResponse) {
                    printJsonObj(rspExcludes, retVal, "返回结果:");
                }
                return retVal;
            } catch (Throwable e) {
                LOGGER.info("抛出异常, 异常信息 :{}", e.getMessage());
                throw e;
            } finally {
                LOGGER.info("*****调用服务结束*****");
            }
        }
        return pjp.proceed();
    }


    /**
     * 输出注解属性
     *
     * @return
     */
    private ControllerLog printAnnotation(ProceedingJoinPoint pjp) {
        Signature signature = pjp.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        Method targetMethod = methodSignature.getMethod();
        try {
            Method realMethod = pjp.getTarget().getClass().getDeclaredMethod(signature.getName(), targetMethod.getParameterTypes());
            //获取RequestAttributes
            RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
            //从获取RequestAttributes中获取HttpServletRequest的信息
            HttpServletRequest request = (HttpServletRequest) requestAttributes.resolveReference(RequestAttributes.REFERENCE_REQUEST);
            String url = request.getServletPath();
            String apiOperationValue = null;
            ApiOperation apiOperation = realMethod.getAnnotation(ApiOperation.class);
            if (apiOperation != null) {
                apiOperationValue = apiOperation.value();
            }
            ControllerLog serviceLog = realMethod.getAnnotation(ControllerLog.class);
            LOGGER.info("-----uri: {} 【{}业务接收到请求】-----", url, apiOperationValue);
            return serviceLog;
        } catch (NoSuchMethodException e) {
            LOGGER.info(e.getMessage());
        }
        return null;
    }

    private void printJsonObj(String[] excludes, final Object arg, String prefix) {
        if (arg != null) {
            if (arg instanceof ServletRequest) {

            } else if (arg instanceof ServletResponse) {

            } else if (arg instanceof ModelAndView) {

            } else if (arg instanceof ModelMap) {

            } else if (arg instanceof HttpSession) {

            } else if (arg instanceof MultipartFile) {
                LOGGER.info(prefix + " MultipartFile# FileName: {} FileSize: {}", ((MultipartFile) arg).getOriginalFilename(), ((MultipartFile) arg).getSize());
            } else if (arg instanceof String) {
                LOGGER.info(prefix + arg);
            } else if (arg instanceof Object) {
                String msg = JSONUtil.toJsonStr(arg);
                LOGGER.info(prefix + msg);
            } else {
                printObj(arg, prefix);
            }
        } else {
            LOGGER.info(prefix + " null");
        }
    }

    /**
     * 记录参数
     *
     * @param arg
     * @param prefix
     */
    @SuppressWarnings("rawtypes")
    private void printObj(final Object arg, String prefix) {
        if (arg != null) {
            if (arg.getClass().isArray()) {
                if (ArrayUtils.isNotEmpty((Object[]) arg)) {
                    Object[] args = (Object[]) arg;
                    for (Object object : args) {
                        printObj(object, prefix);
                    }
                }
            } else if (arg instanceof Collection) {
                if (CollectionUtils.isNotEmpty((Collection) arg)) {
                    Collection collection = (Collection) arg;
                    for (Object object : collection) {
                        printObj(object, prefix);
                    }
                }
            }
            if(ClassUtils.isPrimitiveOrWrapper(arg.getClass())){
                LOGGER.info(prefix+arg.toString());
            }else if(arg instanceof String) {
                LOGGER.info(prefix+arg);
            }else{
                LOGGER.info(prefix+ ReflectionToStringBuilder.toString(arg));
            }
        }else{
            LOGGER.info(prefix+" null");
        }
    }
}
