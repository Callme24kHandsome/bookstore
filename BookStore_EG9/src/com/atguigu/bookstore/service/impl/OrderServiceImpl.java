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
	//��������dao
	private OrderDao orderDao = new OrderDaoImpl();
	private OrderItemDao orderItemDao = new OrderItemDaoImpl();
	private BookDao bookDao = new BookDaoImpl();
	@Override
	public String createOrder(Cart cart, User user) {
		//���ɶ�����
		String orderId = System.currentTimeMillis()+""+user.getId();
		//��ȡ���ﳵ�е��ܽ���������
		double totalAmount = cart.getTotalAmount();
		int totalCount = cart.getTotalCount();
		//���ɶ���
		Order order = new Order(orderId, new Date(), totalCount, totalAmount, 0, user.getId());
		//�������������ݿ�
		orderDao.saveOrder(order);
		List<CartItem> list = cart.getCartItems();
		Object[][] itemParams = new Object[list.size()][]; 
		Object[][] bookParams = new Object[list.size()][]; 
		//���ɶ���������
		for(int i=0 ;i<list.size() ;i++){
			CartItem cartItem = list.get(i);
			//��ȡͼ�����
			Book book = cartItem.getBook();
			//��ȡС������
			int count = cartItem.getCount();
			//��ȡС�ƽ��
			double amount = cartItem.getAmount();
			//��ȡͼ����Ϣ
			String author = book.getAuthor();
			Integer id = book.getId();
			String imgPath = book.getImgPath();
			double price = book.getPrice();
			String title = book.getTitle();
			//��ȡͼ��Ŀ�������
			int sales = book.getSales();
			int stock = book.getStock();
			
			//OrderItem orderItem = new OrderItem(id, count, amount, title, author, price, imgPath, orderId);
			//��orderItem�������ݿ�
			//orderItemDao.saveOrderItem(orderItem);
			//��������������� count, amount, title, author, price, img_path, order_id)
			itemParams[i] = new Object[]{count, amount, title, author, price, imgPath, orderId}; 
			bookParams[i] = new Object[]{sales+count,stock-count,id};
			//�޸�ͼ��Ŀ�������
			//book.setStock(stock-count);
			//book.setSales(sales+count);
			//bookDao.updateBook(book);
			//��չ��ﳵ
			cart.clear();
		}
		//��������orderitem
		orderItemDao.batchSave(itemParams);
		//�����޸������Ϳ��
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
