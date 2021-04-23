package com.collectplatform.project.aop;

import com.alibaba.fastjson.JSON;
import com.collectplatform.project.service.ScriptFileService;
import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @ Author: fuqiang
 * @ Date: 2021/4/23
 */
@Aspect
@Component
public class FileDownloadedAop  {
    private String fileId;
    @Autowired
    ScriptFileService scriptFileService;

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    @Pointcut("execution(public * com.collectplatform.project.controller.ScriptFileController.downloadFile(..))")
    public void scriptFilePointcut() {

    }

    @Around("scriptFilePointcut()")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String[] ids = request.getParameterMap().get("id");
        setFileId(ids[0]);
        return joinPoint.proceed();
    }


    @After("scriptFilePointcut()")
    public void afterScriptFilePointcut() {
        System.out.println("########### afterScriptFilePointcut  ########" + fileId);
        scriptFileService.deleteTempFile(fileId);
    }
}
