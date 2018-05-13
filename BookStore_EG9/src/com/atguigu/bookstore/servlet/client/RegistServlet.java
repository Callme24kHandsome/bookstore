package com.atguigu.bookstore.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;

public class RegistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	//鍒涘缓涓�釜UserService
	private UserService userService = new UserServiceImpl();
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		
		User user = new User(null, username, password, email);
		
		boolean isRegist = userService.regist(user);
		
		if(isRegist){
			response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
		}else{
			request.setAttribute("msg", "用户名已存在");
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			
		}
	}

}
