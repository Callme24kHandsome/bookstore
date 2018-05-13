package com.atguigu.bookstore.service;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.bean.User;

/**
 * 瀹氫箟鐢ㄦ埛鐩稿叧涓氬姟鐨勬帴鍙�
 * @author lilichao
 *
 */
public interface UserService {
	
	/**
	 * 鐢ㄦ埛鐧诲綍鐨勬柟娉�
	 * @param user
	 * @return
	 */
	User login(User user);
	
	/**
	 * 鐢ㄦ埛娉ㄥ唽鐨勬柟娉�
	 * @param user
	 * @return
	 */
	boolean regist(User user);
	boolean checkUsername(String username);

}
