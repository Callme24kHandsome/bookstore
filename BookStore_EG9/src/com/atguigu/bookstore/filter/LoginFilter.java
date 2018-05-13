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
 * �Է��͵�orderclientservlet���������ͳһ�ĵ�½��֤
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
			//�û�δ��¼
			request.setAttribute("msg","�ò�����Ҫ��¼��" );
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//�û��ѵ�¼
			chain.doFilter(request, response);
		}
	}

}
