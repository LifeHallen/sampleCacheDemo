package com.tiefan.fsh.open.intf;

/**
 *
 * @author brucidshan
 * @version 1.0
 */
public interface SOR<K, V> {
    
    /**
     * @param key 指定条件
     * @return 查询结果
     */
    public V load(K key);
    
    /**
     * @param key 指定条件
     * @param value 保存结果
     */
    public void write(K key, V value);
    
    /**
     *
     * @param key 删除关键字
     */
    public void delete(K key);
}