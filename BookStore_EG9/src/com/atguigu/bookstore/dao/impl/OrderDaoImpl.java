package com.atguigu.bookstore.dao.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.OrderDao;

public class OrderDaoImpl extends BaseDao<Order> implements OrderDao {
/**
 * private String id;
	private Date orderTime;
	private int totalCount;
	private double totalAmount;
	private int state;
	private int userId;
 */
	@Override
	public int saveOrder(Order order) {
		String sql = "INSERT INTO bs_order(id, order_time, total_count, total_amount, state, user_id)"
				+ " VALUE(?,?,?,?,?,?)";
		return  this.update(sql, order.getId(), order.getOrderTime(), order.getTotalCount(),
				order.getTotalAmount(), order.getState(), order.getUserId()); 
	}

	@Override
	public int updateState(String orderId, int state) {
		String sql = "UPDATE bs_order SET state = ? WHERE id = ?";
		return this.update(sql, state, orderId);
	}

	@Override
	public List<Order> getOrderList() {
		String sql = "SELECT id, order_time orderTime, total_count totalCount, total_amount totalAmount,"
				+ "state, user_id userId from bs_order ORDER BY orderTime DESC";
		return this.getBeanList(sql);
	}

	@Override
	public List<Order> getOrderListByUserId(String userId) {
		String sql = "SELECT id, order_time orderTime, total_count totalCount, total_amount totalAmount,"
				+ "state, user_id userId from bs_order WHERE user_id = ? ORDER BY orderTime DESC";
		return this.getBeanList(sql, userId);
	}

}
