/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: GnericSimpleCacheFactory.java
 * Author:   15082151
 * Date:     2018年4月24日 上午10:30:22
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.tiefan.fsh.open.factory;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import com.tiefan.fsh.open.DefaultCacheLoadWriter;
import com.tiefan.fsh.open.intf.SOR;
import org.ehcache.Cache;
import org.ehcache.CacheManager;
import org.ehcache.config.builders.CacheConfigurationBuilder;
import org.ehcache.config.builders.CacheManagerBuilder;
import org.ehcache.config.builders.ResourcePoolsBuilder;
import org.ehcache.config.units.MemoryUnit;
import org.ehcache.expiry.Duration;
import org.ehcache.expiry.Expirations;

/**
 *
 * @author brucidshan
 * @version 1.0
 */
public abstract class GenericSimpleCacheFactory {

    // 本地缓存开关
    boolean sampleCacheSwitch = true;
    
    // 创建实例
    CacheManager _cManager = CacheManagerBuilder.newCacheManagerBuilder().build(true);

    // 获取simpleCache
    public <K, V> Cache<K, V> getCache(String alias, Class<K> keyType, Class<V> valueType) {
        if (sampleCacheSwitch) {
            return _cManager.getCache(alias, keyType, valueType);
        } else {
            return null;
        }
    }
    
    public <K, V> void registSimpleCache(String cacheName, Class<K> keyType, Class<V> valueType, int cacheSizeInMb, int TTLSecond, SOR<K, V> sor) {
        _cManager.createCache(cacheName, 
                CacheConfigurationBuilder.newCacheConfigurationBuilder(
                        keyType, valueType, 
                        ResourcePoolsBuilder.newResourcePoolsBuilder().heap(cacheSizeInMb, MemoryUnit.MB)).
                        withLoaderWriter(new DefaultCacheLoadWriter<K, V> (sor)).
                        withExpiry(Expirations.timeToLiveExpiration(Duration.of(TTLSecond, TimeUnit.SECONDS)))
                );
    }
    
    @PostConstruct
    public void postConstruct() {
        init();
    }
    
    // 初始化cache
    public abstract void init();

    // 设置开关
    public void setSampleCache(boolean sampleCacheSwitch) {
        this.sampleCacheSwitch = sampleCacheSwitch;
    }
}
