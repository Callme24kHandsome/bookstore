package com.atguigu.bookstore.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.User;

/**
 * 对发送到orderclientservlet的请求进行统一的登陆验证
 * @author DD
 *
 */
public class LoginFilter extends HttpFilter implements Filter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("loginUser");
		if(user==null){
			//用户未登录
			request.setAttribute("msg","该操作需要登录！" );
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//用户已登录
			chain.doFilter(request, response);
		}
	}

}
