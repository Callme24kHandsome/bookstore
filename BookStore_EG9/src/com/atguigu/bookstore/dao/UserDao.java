package com.atguigu.bookstore.dao;

import com.atguigu.bookstore.bean.User;

/**
 * 瀹氫箟User琛ㄥ熀鏈搷浣滅殑Dao
 * @author lilichao
 *
 */
public interface UserDao {

	/**
	 * 鏍规嵁鐢ㄦ埛鍚嶅拰瀵嗙爜鏌ヨ鐢ㄦ埛
	 * @param user
	 * @return
	 */
	User getUserByUsernameAndPassword(User user);
	
	/**
	 * 鍚戞暟鎹簱涓彃鍏ヤ竴涓敤鎴峰璞�
	 * @param user
	 * @return
	 */
	int saveUser(User user);
	User getUserByUsername(String username);
	
}
