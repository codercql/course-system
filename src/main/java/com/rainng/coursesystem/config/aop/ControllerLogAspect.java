package com.rainng.coursesystem.config.aop;

import com.rainng.coursesystem.manager.LoginStatusManager;
import com.rainng.coursesystem.model.constant.HttpStatusCode;
import com.rainng.coursesystem.model.vo.response.ResultVO;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Aspect
@Component
public class ControllerLogAspect {
    private static final String PACKAGE_PREFIX = "com.rainng.coursesystem.controller.";
    private static final String PACKAGE_PREFIX2 = "com.rainng.coursesystem.config.handler.BindExceptionHandler.";
    private static final String CONTROLLER_POSTFIX = "Controller.";

    private final LoginStatusManager loginStatusManager;

    public ControllerLogAspect(LoginStatusManager loginStatusManager) {
        this.loginStatusManager = loginStatusManager;
    }

    @Pointcut("execution(public * com.rainng.coursesystem.controller..*.*(..)) || " +
            "execution(public * com.rainng.coursesystem.config.handler.BindExceptionHandler.handleBindException(..))")
    public void controllerLog() {
    }

    @Around("controllerLog()")
    public Object around(ProceedingJoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable ex) {
            log.error(ex.getMessage());
            setResponseCode(HttpStatusCode.INTERNAL_SERVER_ERROR);
            result = new ResultVO(ResultVO.SERVER_ERROR, ex.getMessage(), null);
        }

//        logResult(log, result, System.currentTimeMillis() - startTime);
//        logDAO.insert(log);

        return result;
    }

    private void logRequest(ProceedingJoinPoint joinPoint) {
        HttpServletRequest request = getRequest();

        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String businessTarget = signature.getDeclaringTypeName() + "." + signature.getMethod().getName();
        businessTarget = businessTarget
                .replace(PACKAGE_PREFIX, "")
                .replace(CONTROLLER_POSTFIX, ".")
                .replace(PACKAGE_PREFIX2, "");
    }

    private void logResult(Object result, long executeTime) {
        if (!(result instanceof ResultVO)) {
            return;
        }

        ResultVO resultVO = (ResultVO) result;
    }

    private HttpServletRequest getRequest() {
        ServletRequestAttributes attributes = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes());
        if (attributes == null) {
            return null;
        }

        return attributes.getRequest();
    }

    private void setResponseCode(int statusCode) {
        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            return;
        }
        HttpServletResponse response = requestAttributes.getResponse();
        if (response == null) {
            return;
        }

        response.setStatus(statusCode);
    }
}
