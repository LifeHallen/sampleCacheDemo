/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LocalCacheAnnotationAspect.java
 * Author:   15082151
 * Date:     2018年4月13日 下午2:50:36
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.tiefan.fsh.open.aop;

import com.tiefan.fsh.open.service.AspectProcess;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author brucidshan
 * @version 1.0
 */
@Aspect 
@Component
public class LocalCacheAnnotationAspect {

    public LocalCacheAnnotationAspect() {
        System.out.println("######LocalCacheAnnotationAspect constructor######");
    }
    
    @Autowired
    private  AspectProcess processor;
    
    @Pointcut("@annotation(com.tiefan.fsh.open.annotation.LocalCache)")
    public void localCachePointcut() {}
    
    @Around("localCachePointcut()")
    public Object invoke(ProceedingJoinPoint invocation) throws Throwable {
        System.out.println("######LocalCacheAnnotationAspect localCachePointcut######");
        return processor.arountLocalCacheAnnotation(invocation);
    }
}
