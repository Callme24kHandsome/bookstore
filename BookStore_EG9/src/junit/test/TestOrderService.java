package junit.test;
import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.User;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.OrderService;
import com.atguigu.bookstore.service.impl.OrderServiceImpl;

public class TestOrderService {

	@Test
	public void testCreateOrder() {
		Cart cart = new Cart();
		BookDao bookDao = new BookDaoImpl();
		Book book1 = bookDao.getBookById("55");
		System.out.println(book1);
		Book book2 = bookDao.getBookById("56");
		Book book3 = bookDao.getBookById("57");
		cart.addBook2Cart(book1);
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book3);
		System.out.println(cart);
		OrderService orderService = new OrderServiceImpl();
		User user = new User(1, null, null, null);
		String orderId = orderService.createOrder(cart, user);
		System.out.println(orderId);
		
	}
	

}
