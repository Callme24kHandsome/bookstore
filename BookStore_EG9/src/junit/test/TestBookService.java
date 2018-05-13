package junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.jasper.tagplugins.jstl.core.ForEach;
import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;

public class TestBookService {
	
	BookService bookService = new BookServiceImpl();
	@Test
	public void testFindBookByPrice() {
		Page<Book> page = bookService.findBookByPrice("2", 5, "0", "100");
		List<Book> list = page.getData();
		for(Book book :list){
			System.out.println(book);
		}
		
 	}

}
