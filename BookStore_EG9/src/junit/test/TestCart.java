package junit.test;

import static org.junit.Assert.*;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;

public class TestCart {

	@Test
	public void test() {
		Cart cart = new Cart();
		Book book = new Book(1,"����ϣ��","�ֿ���",100,100,100,"");
		Book book2 = new Book(2,"����ϣ��","�ֿ���",0.01,100,100,"");
		Book book3 = new Book(3,"����ϣ��","�ֿ���",0.05,100,100,"");
	//	cart.addBook2Cart(book);
		cart.addBook2Cart(book2);
		cart.addBook2Cart(book3); 
//		cart.updateCount("1", "3");
		System.out.println(cart.getTotalAmount());
		System.out.println(cart.getTotalCount());
		System.out.println("-------------------");
		for (CartItem cartItem : cart.getCartItems()) {
			System.out.println(cartItem.getCount());
			System.out.println(cartItem.getAmount());
		}
		
	}

}
