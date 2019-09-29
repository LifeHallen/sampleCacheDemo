package com.tiefan.fsh.open.intf;

/**
 *
 * @author brucidshan
 * @version 1.0
 * @see http://www.ehcache.org/documentation/3.5/caching-patterns.html
 */
public interface SOR<K, V> {
    
    /**
     * 功能描述: <br>
     * @Description:〈读取SOR指定key的值〉
     *
     * @param key 指定条件
     * @return 查询结果
     */
    public V load(K key);
    
    /**
     * 功能描述: <br>
     * 〈写入SOR指定key的值〉
     *
     * @param key
     * @param value
     */
    public void write(K key, V value);
    
    /**
     * 功能描述: <br>
     * 〈删除SOR中指定键值对〉
     *
     * @param key
     */
    public void delete(K key);
}