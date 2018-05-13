package com.atguigu.bookstore.service.impl;

import java.util.Date;
import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;
import com.atguigu.bookstore.service.OrderService;

public class OrderServiceImpl implements OrderService {
	//创建三个dao
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public String createOrder(Cart cart, User user) {
		//生成订单号
		String orderId = System.currentTimeMillis()+""+user.getId();
		//获取购物车中的总金额和总数量
		double totalAmount = cart.getTotalAmount();
		int totalCount = cart.getTotalCount();
		//生成订单
		Order order = new Order(orderId, new Date(), totalCount, totalAmount, 0, user.getId());
		//将订单插入数据库
		orderDao.saveOrder(order);
		List<CartItem> list = cart.getCartItems();
		Object[][] itemParams = new Object[list.size()][]; 
		Object[][] bookParams = new Object[list.size()][]; 
		//生成订单购物项
		for(int i=0 ;i<list.size() ;i++){
			CartItem cartItem = list.get(i);
			//获取图书对象
			Book book = cartItem.getBook();
			//获取小计数量
			int count = cartItem.getCount();
			//获取小计金额
			double amount = cartItem.getAmount();
			//获取图书信息
			String author = book.getAuthor();
			Integer id = book.getId();
			String imgPath = book.getImgPath();
			double price = book.getPrice();
			String title = book.getTitle();
			//获取图书的库存和销量
			int sales = book.getSales();
			int stock = book.getStock();
			
			//OrderItem orderItem = new OrderItem(id, count, amount, title, author, price, imgPath, orderId);
			//将orderItem插入数据库
			//orderItemDao.saveOrderItem(orderItem);
			//向数组中添加属性 count, amount, title, author, price, img_path, order_id)
			itemParams[i] = new Object[]{count, amount, title, author, price, imgPath, orderId}; 
			bookParams[i] = new Object[]{sales+count,stock-count,id};
			//修改图书的库存和销量
			//book.setStock(stock-count);
			//book.setSales(sales+count);
			//bookDao.updateBook(book);
			//清空购物车
			cart.clear();
		}
		//批量插入orderitem
		orderItemDao.batchSave(itemParams);
		//批量修改销量和库存
		bookDao.batchUpdateSalesAndStock(bookParams);
		
		return orderId;
	}
	@Override
	public List<Order> getOrderList() {
		return orderDao.getOrderList();
	}
	@Override
	public List<Order> getOrderListByUserId(String userId) {
		return orderDao.getOrderListByUserId(userId);
	}
	@Override
	public void sendBook(String orderId) {
		orderDao.updateState(orderId, 1);
	}
	@Override
	public void takeBook(String orderId) {
		orderDao.updateState(orderId, 2);
	}
	@Override
	public List<OrderItem> getOrderInfo(String orderId) {
		// TODO Auto-generated method stub
		return orderItemDao.getOrderItemByOrderId(orderId);
	}

}
