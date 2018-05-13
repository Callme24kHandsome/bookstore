package com.atguigu.bookstore.filter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.utils.JDBCUtils;


public class TranscationFilter extends HttpFilter implements Filter {

	@Override
	public void doFilter(HttpServletRequest request,
			HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		Connection conn = JDBCUtils.getConnection();
		try {
			conn.setAutoCommit(false);
			chain.doFilter(request, response);
			conn.commit();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			try {
				System.out.println("����˷�");
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			request.setAttribute("error", "ϵͳ�����ˣ�����ϵ����Ա������");
			request.getRequestDispatcher("/pages/error/error.jsp").forward(request, response);
		}
		finally{
			JDBCUtils.releaseConnection();
		}
	}
}