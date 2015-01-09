package com.easyway.spring.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.mongodb.core.query.Query;

/**
 * Mongo  操作底层接口
 * @author longgangbai
 * 2015-1-9  上午9:42:42
 */
public interface GeneralRepository<K, V> {
    /**
     * 
     * @param v
     */
    public void insert(V v);

    /**
     * 
     * @param k
     * @return
     */
    public V findOne(K k);

    /**
     * 
     * @return
     */
    public List<V> findAll();

    /**
     * 
     * @param query
     * @return
     */
    public List<V> findByRegex(Query query);

    /**
     * 
     * @param id
     */
    public void removeOne(String id);

    /**
     * 
     */
    public void removeAll();

    /**
     * 
     * @param id
     */
    public void findAndModify(String id,Map<String,String> condMap);

}
