package com.atguigu.bookstore.service.impl;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;
import com.atguigu.bookstore.service.UserService;

/**
 * UserService的实现类
 * @author lilichao
 *
 */
public class UserServiceImpl implements UserService {
	
	private UserDao userDao = new UserDaoImpl();

	@Override
	public User login(User user) {
		return userDao.getUserByUsernameAndPassword(user);
	}

	@Override
	public boolean regist(User user) {
		
		int count = userDao.saveUser(user);
		
		return count > 0;
	}

	@Override
	public boolean checkUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.getUserByUsername(username)==null;
	}

	

}
