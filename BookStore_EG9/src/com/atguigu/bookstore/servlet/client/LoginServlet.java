package com.atguigu.bookstore.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

/**
 * 澶勭悊鐢ㄦ埛鐧诲綍鐨凷ervlet
 * @author lilichao
 *
 */
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//鍒涘缓涓�釜UserService
	private UserService userService = new UserServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		User user = new User(null, username, password, null);
		
		User loginUser = userService.login(user);
		
		if(loginUser == null){
			request.setAttribute("msg", "用户名或密码错误");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
		
	}

}
