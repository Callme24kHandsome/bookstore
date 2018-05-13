package com.atguigu.bookstore.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;

public class OrderClientServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private OrderService orderService = new OrderServiceImpl();
	/**
	 * 查询当前用户的订单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void orderList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		//获取用户信息
		User user = (User) session.getAttribute("loginUser");
		if(user==null){
			//用户未登录
			request.setAttribute("msg", "该操作需要先登录数据库");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//用户已登陆
			List<Order> list = orderService.getOrderListByUserId(user.getId()+"");
			//将list设置进request域中
			request.setAttribute("list", list);
			//转发到cart.jsp
			request.getRequestDispatcher("/pages/order/order.jsp").forward(request, response);
			
		}
		
	}
	public void checkOut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		//获取用户信息
		User user = (User) session.getAttribute("loginUser");
		if(user==null){
			//用户未登录
			request.setAttribute("msg", "该操作需要先登录数据库");
			request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
		}else{
			//用户已登陆
			//获取购物车对象
			Cart cart = WEBUtils.getCart(request);
			String orderId = orderService.createOrder(cart, user);
			request.setAttribute("orderId", orderId);
			request.getRequestDispatcher("/pages/cart/checkout.jsp").forward(request, response);
			
		}
		
	}
	public void takeBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		orderService.takeBook(orderId);
		response.sendRedirect(request.getContextPath()+"/client/OrderClientServlet?method=orderList");
	}
	public void orderInfo(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String orderId = request.getParameter("orderId");
		List<OrderItem> list = orderService.getOrderInfo(orderId);
		request.setAttribute("list", list);
		request.getRequestDispatcher("/pages/order/order-info.jsp").forward(request, response);
	}
	


}
