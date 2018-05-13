package com.atguigu.bookstore.bean;

import java.io.Serializable;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;
public class User implements HttpSessionActivationListener,Serializable{

	private static final long serialVersionUID = 1L;
	private Integer id;
	private String username;
	private String password;
	private String email;

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}

	public User(Integer id, String username, String password, String email) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.email = email;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", username=" + username + ", password="
				+ password + ", email=" + email + "]";
	}
	@Override
	public void sessionDidActivate(HttpSessionEvent se) {
		System.out.println("user对象活化了");  
	}

	@Override
	public void sessionWillPassivate(HttpSessionEvent se) {
		System.out.println("user对象钝化了");  
	}

}
