/**
 * 
 */
package com.easyway.spring.dao;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;


/**
 * Mongo 操作底层接口实现
 * @author longgangbai
 * 2015-1-9  上午9:57:26
 */
public abstract class AbstractGeneralRepository<K,V> implements GeneralRepository<K,V>  {
    private Logger logger = LoggerFactory.getLogger(AbstractGeneralRepository.class);
    private String collectionName;
    private Class<V> type;
    @Autowired
    private MongoTemplate mongoTemplate;
    
    @SuppressWarnings("unchecked")
    public AbstractGeneralRepository() {
	initType(((Class<V>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[1]));
    }

    protected void initType(Class<V> type) {
	this.type=type;
	collectionName = type.getSimpleName().toLowerCase();
    }


    public String getCollectionName() {
        return collectionName;
    }

    public void setCollectionName(String collectionName) {
        this.collectionName = collectionName;
    }

    /* (non-Javadoc)
     * @see com.easyway.spring.dao.GeneralRepository#insert(java.lang.Object)
     */
    @Override
    public void insert(V v) {
	mongoTemplate.insert(v, collectionName);
    }

    /* (non-Javadoc)
     * @see com.easyway.spring.dao.GeneralRepository#findOne(java.lang.Object)
     */
    @Override
    public V findOne(K k) {
	return mongoTemplate.findById(k, type);
    }

    /* (non-Javadoc)
     * @see com.easyway.spring.dao.GeneralRepository#findAll()
     */
    @Override
    public List<V> findAll() {
	return mongoTemplate.findAll(type, collectionName);
    }

    /* (non-Javadoc)
     * @see com.easyway.spring.dao.GeneralRepository#findByRegex(java.lang.String, java.util.Map)
     */
    @Override
    public List<V> findByRegex(Query query) {
	// 使用query对象查询
	return mongoTemplate.find(query, type);
    }

    /* (non-Javadoc)
     * @see com.easyway.spring.dao.GeneralRepository#removeOne(java.lang.String)
     */
    public void removeOne(K v) {
	Query query = new Query(Criteria.where("_id").in(v));
	mongoTemplate.remove(query, type);
    }

    /* (non-Javadoc)
     * @see com.easyway.spring.dao.GeneralRepository#removeAll()
     */
    @Override
    public void removeAll() {
	mongoTemplate.dropCollection(type);
	
    }

    /* (non-Javadoc)
     * @see com.easyway.spring.dao.GeneralRepository#findAndModify(java.lang.String, java.util.Map)
     */
    public void findAndModify(K k, Map<String, String> condMap) {
	Query query = new Query(Criteria.where("_id").in(k));
	Update update = new Update();
	for (Entry<String,String> entry : condMap.entrySet()) {
	    update.set(entry.getKey(), entry.getValue());
	}
	mongoTemplate.findAndModify(query, update, type);
    }

    public MongoTemplate getMongoTemplate() {
        return mongoTemplate;
    }

    public void setMongoTemplate(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

}
