/*
 * Copyright (C), 2002-2018, 苏宁易购电子商务有限公司
 * FileName: DefaultCacheLoadWriter.java
 * Author:   15082151
 * Date:     2018年4月11日 下午5:05:20
 * Description: //模块目的、功能描述      
 * History: //修改记录
 * <author>      <time>      <version>    <desc>
 * 修改人姓名             修改时间            版本号                  描述
 */
package com.tiefan.fsh.open;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.tiefan.fsh.open.intf.SOR;
import org.ehcache.spi.loaderwriter.BulkCacheLoadingException;
import org.ehcache.spi.loaderwriter.BulkCacheWritingException;
import org.ehcache.spi.loaderwriter.CacheLoaderWriter;

/**
 *
 * @author brucidshan
 * @version 1.0
 * @see〈LoadWriter缺省实现〉
 */
public class DefaultCacheLoadWriter<K, V> implements CacheLoaderWriter<K, V> {

    private SOR<K, V> _sor;

    public DefaultCacheLoadWriter(SOR<K, V> sor) {
        if (sor == null) {
            throw new IllegalArgumentException("DefaultCacheLoadWriter 构造时必须指定非空sor !!!");
        }
        _sor = sor;
    }

    public void delete(K key) throws Exception {
        _sor.delete(key);
    }

    public V load(K key) throws Exception {
        return _sor.load(key);
    }

    public void write(K key, V value) throws Exception {
        _sor.write(key, value);
    }

    public Map<K, V> loadAll(Iterable<? extends K> keys) throws BulkCacheLoadingException, Exception {
        Map<K, V> entries = new HashMap<K, V>();
        for (K k : keys) {
            entries.put(k, load(k));
        }
        return entries;
    }

    public void writeAll(Iterable<? extends Entry<? extends K, ? extends V>> entries)
            throws BulkCacheWritingException, Exception {
        for (Entry<? extends K, ? extends V> entry : entries) {
            write(entry.getKey(), entry.getValue());
        }
    }

    public void deleteAll(Iterable<? extends K> keys) throws BulkCacheWritingException, Exception {
        for (K k : keys) {
            delete(k);
        }
    }
}