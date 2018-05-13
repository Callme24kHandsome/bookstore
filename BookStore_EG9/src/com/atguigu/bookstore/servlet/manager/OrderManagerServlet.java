package com.atguigu.bookstore.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;

public class OrderManagerServlet extends BaseServlet {
	
	OrderService orderService = new OrderServiceImpl();
	
	
	public void orderList(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Order> list = orderService.getOrderList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/pages/manager/order_manager.jsp").forward(request, response);
	}
	public void sendBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		orderService.sendBook(orderId);
		response.sendRedirect(request.getContextPath()+"/manager/OrderManagerServlet?method=orderList");
		
	}
}
