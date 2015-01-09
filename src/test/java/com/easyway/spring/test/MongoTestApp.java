package com.easyway.spring.test;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.easyway.spring.bean.Person;
import com.easyway.spring.dao.impl.PersonRepository;

/**
 * 测试类
 * 
 * @author longgangbai 2015-1-9 上午9:45:52
 */
public class MongoTestApp {

    private static Log log = LogFactory.getLog(MongoTestApp.class.getName());

    private PersonRepository pr = null;

    /**
	 * 
	 */
    public void init() {
	ApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:springdata-mongo-mongodb.xml");
	String[] beanList = ctx.getBeanDefinitionNames();
	for (String string : beanList) {
	    System.out.println(string);
	}
	pr = (PersonRepository) ctx.getBean(PersonRepository.class);
    }

    /**
	 * 
	 */
    public void insert() {
	Person p = new Person("cuiran", 27);
	pr.insert(p);
    }

    /**
	 * 
	 */
    public void findOne() {
	String id = "50c83cb552c2ceb0463177d6";
	Person p = pr.findOne(id);
    }

    /**
	 * 
	 */
    public void listAll() {
	List<Person> list = pr.findAll();
	for (Person p : list) {
	    System.out.println(p.toString());
	}

    }

    /**
	 * 
	 */
    public void start() {
	init();
	insert();
	listAll();
    }

    /**
     * 
     * @param args
     */
    public static void main(String[] args) {
	MongoTestApp t = new MongoTestApp();
	t.start();
    }

}
