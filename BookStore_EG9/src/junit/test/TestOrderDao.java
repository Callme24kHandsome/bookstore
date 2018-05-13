package junit.test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import com.atguigu.bookstore.bean.Order;
import com.atguigu.bookstore.dao.OrderDao;
import com.atguigu.bookstore.dao.impl.OrderDaoImpl;

public class TestOrderDao {
	OrderDao orderDao = new OrderDaoImpl();
	@Test
	public void testSaveOrder() {
		String id = System.currentTimeMillis()+""+1;
		Order order = new Order(id,new Date(),2,50,1,1);
		orderDao.saveOrder(order);
		
	}

	@Test
	public void testUpdateState() {
		orderDao.updateState("15213410615511", 0);
	}

	@Test
	public void testGetOrderList() {
		System.out.println(orderDao.getOrderList());
	}

	@Test
	public void testGetOrderListByUserId() {
		System.out.println(orderDao.getOrderListByUserId("1"));
	}

}
