package com.atguigu.bookstore.service;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;

public interface BookService {
	int saveBook(Book book);
	int delBook(String bookId);
	int updateBook(Book book);
	List<Book> getBookList();
	Book getBookById(String bookId);
	Page<Book> findBook(String pageNumber,int pageSize);
	/**
	 * 根据价格寻找图书
	 * @param pageNumber
	 * @param pageSize
	 * @param minPrice
	 * @param maxPrice
	 * @return
	 */
	Page<Book> findBookByPrice(String pageNumber,int pageSize,String min,String max);
}
