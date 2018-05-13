package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.OrderItem;

public interface OrderItemDao {
	List<OrderItem> getOrderItemByOrderId(String orderId);
	int saveOrderItem(OrderItem orderItem);
	/**
	 * 批量保存购物项
	 */
	void batchSave(Object[][] params);
}
