package junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.OrderItem;
import com.atguigu.bookstore.dao.OrderItemDao;
import com.atguigu.bookstore.dao.impl.OrderItemDaoImpl;

public class TestOrderItemDao {
	OrderItemDao orderItemDao = new OrderItemDaoImpl();

	@Test
	public void testGetOrderItemByOrderId() {
		List<OrderItem> list = orderItemDao.getOrderItemByOrderId("15213410615511");
		System.out.println(list);
	}

	@Test
	public void testSaveOrderItem() {
		OrderItem orderItem = new OrderItem(null, 2, 10, "三国演义", "罗贯中", 20, "hello", "15213410615511");
		int count = orderItemDao.saveOrderItem(orderItem);
		System.out.println(count);
	}

}
