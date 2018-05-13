package com.atguigu.bookstore.dao;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookDao {
	int savaBook(Book book);
	int delBook(String bookId);
	int updateBook(Book book);
	List<Book> getBookList();
	Book getBookById(String bookId);
	Page<Book> findBook(Page<Book> page);
	Page<Book> findBookByPrice(Page<Book> page,double minPrice,double maxPrice);
	/**
	 * 批量修改图书的销量和库存
	 * @param params
	 */
	void batchUpdateSalesAndStock(Object[][] params);
}
