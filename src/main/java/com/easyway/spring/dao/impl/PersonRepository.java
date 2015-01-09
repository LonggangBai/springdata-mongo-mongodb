package com.easyway.spring.dao.impl;

import org.springframework.stereotype.Repository;

import com.easyway.spring.bean.Person;
import com.easyway.spring.dao.AbstractGeneralRepository;

/**
 * 具体实现类
 * @author longgangbai 2015-1-9 上午9:41:43
 */
@Repository
public class PersonRepository extends AbstractGeneralRepository<String,Person> {
}
