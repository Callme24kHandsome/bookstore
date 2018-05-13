package com.atguigu.bookstore.servlet.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.BeanUtilsBean;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;

public class BookManagerServlet extends BaseServlet {


	private static final long serialVersionUID = 1L;

	BookService bookService = new BookServiceImpl();
	/**
	 * 修改或者添加图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateBook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Book book = WEBUtils.param2Bean(request, new Book());
		String referer = request.getParameter("referer");
		//如果bookId不等于空或者零，则进行更新操作，否则进行添加图书
		if(book.getId()==null || book.getId()==0){
			bookService.saveBook(book);
		}else{
			bookService.updateBook(book);
		}
		response.sendRedirect(referer);
	}
	/**
	 * 获取图书列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void BookList(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		List<Book> list = bookService.getBookList();
		request.setAttribute("list", list);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}
	/**
	 * 删除图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String bookId = request.getParameter("bookId");
		String referer = request.getHeader("referer");
		System.out.println(referer);
		System.out.println(bookId);
		bookService.delBook(bookId);
		response.sendRedirect(referer);
 
	}
	/**
	 * 去修改图书的页面
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void toUpdatePage(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String bookId = request.getParameter("bookId");
		Book book = bookService.getBookById(bookId);
		request.setAttribute("book", book);
		request.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(request, response);
		
	}
	/**
	 * 分页获取图书
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void findBook(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException{
		String pageNumber = request.getParameter("pageNumber");
		int pageSize = 4;
		Page<Book> page = bookService.findBook(pageNumber, pageSize);
		//获取请求地址
		String path = WEBUtils.getPath(request);
		//设置分页请求地址
		page.setPath(path);
		//把page对象设置进request域中
		request.setAttribute("page", page);
		request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request, response);
	}



}
