package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;

public interface OrderService {
	/**
	 * �����û��͹��ﳵ��Ϣ����һ�������������ض�����
	 * @param cart
	 * @param user
	 * @return
	 */
	String createOrder(Cart cart, User user);
	/**
	 * �ɹ���Ա����
	 * @return
	 */
	List<Order> getOrderList();
	/**
	 * �����û���id���ض�����Ϣ
	 * @param userId
	 * @return
	 */
	List<Order> getOrderListByUserId(String userId);
	/**
	 * ����
	 * @param orderId
	 */
	void sendBook(String orderId);
	void takeBook(String orderId);
	List<OrderItem> getOrderInfo(String orderId);
}
