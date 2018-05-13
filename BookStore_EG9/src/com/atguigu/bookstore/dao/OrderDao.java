package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Order;

public interface OrderDao{
	int saveOrder(Order order);
	int updateState(String orderId, int state);
	List<Order> getOrderList();
	/**
	 * 批量修改图书的库存和销量
	 * @param userId
	 * @return
	 */
	List<Order> getOrderListByUserId(String userId);
}
