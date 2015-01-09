/**
 * 
 */
package com.easyway.spring.bean;


import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;
/**
 * @author longgangbai
 * 2015-1-8  上午11:53:55
 */
@Document(collection = "users")
public class User {
 
	@Id
	private String id;
 
	String username;
 
	String password;
 
	@Indexed
	private String ic;
 
	private String name;
 
	private int age;
 
	@DateTimeFormat(iso = ISO.DATE_TIME)
	private Date createdDate;
	public User(String username,String password,int age,Date createdDate){
	    this.username=username;
	    this.password=password;
	    this.age=age;
	    this.createdDate=createdDate;
	}
	public User(String username,String password){
	    this.username=username;
	    this.password=password;
	}
	public User(){
	}
	public String getIc() {
	    return ic;
	}

	public void setIc(String ic) {
	    this.ic = ic;
	}

	public String getName() {
	    return name;
	}

	public void setName(String name) {
	    this.name = name;
	}

	public int getAge() {
	    return age;
	}

	public void setAge(int age) {
	    this.age = age;
	}

	public Date getCreatedDate() {
	    return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
	    this.createdDate = createdDate;
	}

	public String getId() {
	    return id;
	}

	public void setId(String id) {
	    this.id = id;
	}

	public String getUsername() {
	    return username;
	}

	public void setUsername(String username) {
	    this.username = username;
	}

	public String getPassword() {
	    return password;
	}

	public void setPassword(String password) {
	    this.password = password;
	}
 
 
}