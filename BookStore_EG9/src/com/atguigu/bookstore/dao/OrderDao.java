package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

public interface OrderDao{
	int saveOrder(Order order);
	int updateState(String orderId, int state);
	List<Order> getOrderList();
	/**
	 * �����޸�ͼ��Ŀ�������
	 * @param userId
	 * @return
	 */
	List<Order> getOrderListByUserId(String userId);
}
