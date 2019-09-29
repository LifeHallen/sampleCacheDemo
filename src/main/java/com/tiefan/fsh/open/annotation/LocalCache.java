/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: LocalCache.java
 * Author:   15082151
 * Date:     2018年4月13日 上午11:38:17
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.tiefan.fsh.open.annotation;

import java.lang.annotation.*;

/**
 * @author brucidshan
 * @version 1.0
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LocalCache {

    // 缓存名称
    String cacheName();
    
    // 缓存key的类型
    Class<?> keyClazz();
    
    // 缓存value的类型
    Class<?> valueClazz();
    
    // 请求key的位置
    int keyIndex() default 0;
}
