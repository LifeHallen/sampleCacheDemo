package com.tiefan.fsh.open.intf;

/**
 * This interface show  a template to use the pattern of sor ,and at
 * your sub-class you must implement parents method as the call-back
 * and the detail of usage please  link to http://www.ehcache.org/documentation/3.5/caching-patterns.html and read
 * Cache-aside model
 * @author brucidshan
 * @version 1.0
 */
public interface SOR<K, V> {
    
    /**
     * This method returns the pair of key-value.
     * To use this method you must provided a key
     * @param key 指定条件
     * @return 查询结果
     */
    public V load(K key);
    
    /**
     * The purpose of this method is storage a pair of key-value
     * and you must provided the key and the value
     * @param key 指定条件
     * @param value 保存结果
     */
    public void write(K key, V value);
    
    /**
     *This method is to remove a pair of key-value and it return
     * none
     * @param key 删除关键字
     */
    public void delete(K key);
}