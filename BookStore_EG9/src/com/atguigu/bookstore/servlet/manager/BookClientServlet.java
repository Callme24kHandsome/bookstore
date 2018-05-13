package com.atguigu.bookstore.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;

public class BookClientServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BookService bookService = new BookServiceImpl();
	public void findBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		int pageSize = 4;
		Page<Book> page = bookService.findBook(pageNumber, pageSize);
		page.setPath(WEBUtils.getPath(request));
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/book-list.jsp").forward(request, response);
	}
	public void findBookByPrice(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String pageNumber = request.getParameter("pageNumber");
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		int pageSize = 4;
		Page<Book> page = bookService.findBookByPrice(pageNumber, pageSize,min,max);
		System.out.println(WEBUtils.getPath(request));
		page.setPath(WEBUtils.getPath(request));
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/book/book-list.jsp").forward(request, response);
	}

}
