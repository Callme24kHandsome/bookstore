package junit.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.dao.impl.UserDaoImpl;

public class TestUserDao {
	
	UserDao ud = new UserDaoImpl();

	@Test
	public void testGetUserByUsernameAndPassword() {
		
		User user = new User(null, "admin", "1231231", null);
		
		User loginUser = ud.getUserByUsernameAndPassword(user);
		
		System.out.println(loginUser);
	}

	@Test
	public void testSaveUser() {
		
		User user = new User(null, "admin", "123123", "ad@atguigu.com");
		
		int count = ud.saveUser(user);
		
		System.out.println(count);
		
	}

}
