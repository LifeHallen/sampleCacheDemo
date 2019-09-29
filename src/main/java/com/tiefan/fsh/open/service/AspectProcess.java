/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: AspectProcess.java
 * Author:   15082151
 * Date:     2018年4月26日 下午2:15:53
 * Description: //模块目的、功能描述
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.tiefan.fsh.open.service;

import java.lang.reflect.Method;

import com.tiefan.fsh.open.annotation.LocalCache;
import com.tiefan.fsh.open.annotation.RemoveLocalCache;
import com.tiefan.fsh.open.factory.GenericSimpleCacheFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.ehcache.Cache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Service;

/**
 * @author brucidshan
 * @version 1.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Service
public class AspectProcess {

    public AspectProcess() {
    }

    @Autowired
    private GenericSimpleCacheFactory cacheFactory;

    public Object arountLocalCacheAnnotation(ProceedingJoinPoint invocation) throws Throwable {
        Method method = ((MethodSignature) invocation.getSignature ()).getMethod ();
        LocalCache cacheAnnotation = AnnotationUtils.getAnnotation (method, LocalCache.class);
        Cache cache = cacheFactory.getCache (cacheAnnotation.cacheName (), cacheAnnotation.keyClazz (), cacheAnnotation.valueClazz ());
        if (cache != null) {
            int index = cacheAnnotation.keyIndex ();
            Object[] args = invocation.getArgs ();
            if (index < 0 || index >= args.length) {
                throw new ArrayIndexOutOfBoundsException ("LocalCache Argumens Index is Out of Array");
            }
            return cache.get (args[index]);
        }
        return invocation.proceed ();
    }

    public Object removeLocalCacheAnnotation(ProceedingJoinPoint invocation) throws Throwable {
        Method method = ((MethodSignature) invocation.getSignature ()).getMethod ();
        RemoveLocalCache cacheAnnotation = AnnotationUtils.getAnnotation (method, RemoveLocalCache.class);
        Cache cache = cacheFactory.getCache (cacheAnnotation.cacheName (), cacheAnnotation.keyClazz (), cacheAnnotation.valueClazz ());
        if (cache != null) {
            int index = cacheAnnotation.keyIndex ();
            Object[] args = invocation.getArgs ();
            if (index < 0 || index >= args.length) {
                throw new ArrayIndexOutOfBoundsException ("LocalCache Argumens Index is Out of Array");
            }
            cache.remove (args[index]);
        }
        return invocation.proceed ();
    }
}
