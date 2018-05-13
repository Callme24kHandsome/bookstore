package junit.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;

public class TestBookDao {
//	Book book = new Book(5, "三国演义", "三毛", 100, 200, 1000, "/static/img/default.jpg");
	BookDao bookDao = new BookDaoImpl();
	@Test
	public void testfindBookByPrice(){
		Page<Book> page = new Page<Book>();
		page.setPageSize(4);
		page.setPageNumber(2);	
		Page<Book> pg = bookDao.findBookByPrice(page, 10, 20);
		List<Book> list = pg.getData();
		for(Book book:list){
			System.out.println(book);
		}
	}
	@Test
	public void testFindBook(){
		Page<Book> page = new Page<Book>();
		page.setPageSize(4);
		page.setPageNumber(1);
		Page<Book> pg = bookDao.findBook(page);
		System.out.println(pg.getTotalPage());
		System.out.println(pg.getTotalRecord());
		List<Book> list = pg.getData();
		for(Book book:list){
			System.out.println(book);
		}	
	}	
	@Test
	public void testSavaBook() {
		Book book = new Book(null,"三国演义", "三毛", 100, 200, 1000, "/static/img/default.jpg");
		System.out.println(book);
		int count = bookDao.savaBook(book);
		System.out.println(count);
	}

	@Test
	public void testDelBook() {
		Book book = new Book(1, "三国演义2", "三毛", 100, 200, 1000, "/static/img/default.jpg");
		int count = bookDao.delBook("1");
		System.out.println(count);
	}

	@Test
	public void testUpdateBook() {
		Book book = new Book(6, "三国演义2", "三毛", 100, 200, 1000, "/static/img/default.jpg");
		int count = bookDao.updateBook(book);
		System.out.println(count);
	}

	@Test
	public void testGetBookList() {
		List<Book> list = bookDao.getBookList();
		System.out.println(list);
	}

	@Test
	public void testGetBookById() {
		
		System.out.println(bookDao.getBookById("6"));
	}
	
}
