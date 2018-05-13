package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;

public interface OrderService {
	/**
	 * 根据用户和购物车信息生成一个订单，并返回订单号
	 * @param cart
	 * @param user
	 * @return
	 */
	String createOrder(Cart cart, User user);
	/**
	 * 由管理员调用
	 * @return
	 */
	List<Order> getOrderList();
	/**
	 * 根据用户的id返回订单信息
	 * @param userId
	 * @return
	 */
	List<Order> getOrderListByUserId(String userId);
	/**
	 * 发货
	 * @param orderId
	 */
	void sendBook(String orderId);
	void takeBook(String orderId);
	List<OrderItem> getOrderInfo(String orderId);
}
