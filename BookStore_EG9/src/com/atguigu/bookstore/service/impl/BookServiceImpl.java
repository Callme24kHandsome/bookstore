package com.atguigu.bookstore.service.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BookDao;
import com.atguigu.bookstore.dao.impl.BookDaoImpl;
import com.atguigu.bookstore.service.BookService;

public class BookServiceImpl implements BookService {
	BookDao bookDao = new BookDaoImpl();

	@Override
	public int saveBook(Book book) {
		return bookDao.savaBook(book);
	}

	@Override
	public int delBook(String bookId) {
		return bookDao.delBook(bookId);
	}

	@Override
	public int updateBook(Book book) {
		return bookDao.updateBook(book);
	}

	@Override
	public List<Book> getBookList() {
		return bookDao.getBookList();
	}

	@Override
	public Book getBookById(String bookId) {
		return bookDao.getBookById(bookId);
	}

	@Override
	public Page<Book> findBook(String pageNumber, int pageSize) {
		int pn = 1;
		try {
			pn = Integer.parseInt(pageNumber);
			
		} catch (NumberFormatException e){}
		Page<Book> page = new Page<Book>();
		page.setPageNumber(pn);
		page.setPageSize(pageSize);
		return bookDao.findBook(page);

	}

	@Override
	public Page<Book> findBookByPrice(String pageNumber, int pageSize,
			String min, String max) {
		int pn = 1;
		double minPrice = 0;
		double maxPrice = Double.MAX_VALUE;
		
		try {
			pn = Integer.parseInt(pageNumber);
		} catch (NumberFormatException e){}
		try {
			minPrice = Double.parseDouble(min);
		} catch (NumberFormatException e){}
		try {
			maxPrice = Double.parseDouble(max);
		} catch (NumberFormatException e){}
		Page<Book> page = new Page<Book>();
		page.setPageNumber(pn);
		page.setPageSize(pageSize);
		return bookDao.findBookByPrice(page,minPrice,maxPrice);  
	}

}
