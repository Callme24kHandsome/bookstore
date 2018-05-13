package com.atguigu.bookstore.servlet.client;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.UserDao;
import com.atguigu.bookstore.service.UserService;
import com.atguigu.bookstore.service.impl.UserServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;

public class UserServlet extends BaseServlet {

	UserService userService = new UserServiceImpl();
	private static final long serialVersionUID = 1L;
	
	public void login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡsession����
		HttpSession session = request.getSession();
		
		User user = WEBUtils.param2Bean(request,new User());
		User loginUser = userService.login(user);	
		if(loginUser == null){
			request.setAttribute("msg", "�û������������");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//��user�������ý�session����
			session.setAttribute("loginUser", loginUser);
			response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
		}
	
	}
	public void logout(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡsession����
		HttpSession session = request.getSession();
		//ʹsessionʧЧ
		session.invalidate();
		response.sendRedirect(request.getContextPath()+"/index.jsp");
	
	}
	public void regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//��ȡsession����
		HttpSession session = request.getSession();
		String sessCode = (String) session.getAttribute("code");
		session.removeAttribute("code");
		//��request�л�ȡcode���Ե�ֵ
		String reqCode = request.getParameter("code");
		if(reqCode != null && sessCode.equals(reqCode)){
			//��֤����ȷ
			User user = WEBUtils.param2Bean(request, new User());
			boolean isRegist = userService.regist(user);
			
			if(isRegist){
				response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
			}else{
				request.setAttribute("msg", "�û����Ѵ���");
				request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
			}
		}else{
			//��֤�����
			request.setAttribute("msg","��֤����д����" );
			request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
		}	
	}
	public void checkUsername(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String username = request.getParameter("username");
		boolean flag = userService.checkUsername(username);
		if(flag==true){
			response.getWriter().print("1");
		}else{
			response.getWriter().print("0");
		}
	}
}
