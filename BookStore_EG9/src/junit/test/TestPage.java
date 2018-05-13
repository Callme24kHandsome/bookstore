package junit.test;

import org.junit.Test;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public class TestPage {

	@Test
	public void test() {
		Page<Book> page = new Page<Book>();
		page.setTotalRecord(10);
		page.setPageSize(2);
		page.setPageNumber(3);
		System.out.println(page.getIndex());
		System.out.println(page.getTotalPage());
		

	}

}
