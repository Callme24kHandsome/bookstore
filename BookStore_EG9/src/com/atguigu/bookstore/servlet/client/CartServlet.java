package com.atguigu.bookstore.servlet.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.CartItem;
import com.atguigu.bookstore.service.BookService;
import com.atguigu.bookstore.service.impl.BookServiceImpl;
import com.atguigu.bookstore.servlet.BaseServlet;
import com.atguigu.bookstore.utils.WEBUtils;
import com.google.gson.Gson;
/**
 * �����ﳵ�����Servlet
 * @author DD
 *
 */
public class CartServlet extends BaseServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	BookService bookService = new BookServiceImpl();
	/**
	 * �޸�ָ�������������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void updateCount(HttpServletRequest request,
		HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		String countStr = request.getParameter("count");
		Cart cart = WEBUtils.getCart(request);
		cart.updateCount(bookId, countStr);
		//��ȡҪ���µ���Ϣ���ܽ��������������ܽ��
		double totalAmount = cart.getTotalAmount();	
		int totalCount = cart.getTotalCount();
		List<CartItem> list = cart.getCartItems();
		Map<String, CartItem> map = cart.getMap();
		CartItem cartItem = map.get(bookId);
		double amount = cartItem.getAmount();
		Map<String, Object> jsonmap = new HashMap<String, Object>();
		jsonmap.put("amount", amount+"");
		jsonmap.put("totalAmount", totalAmount+"");
		jsonmap.put("totalCount", totalCount+"");
		Gson gson = new Gson();
		String string = gson.toJson(jsonmap);
		response.getWriter().print(string);		
//		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
	}
	
	
	/**
	 * ɾ��ָ��������
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void delCartItem(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Cart cart = WEBUtils.getCart(request);
		cart.delCartItem(bookId);
		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
	}
	/**
	 * ��չ��ﳵ
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void clear(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		Cart cart = WEBUtils.getCart(request);
		cart.clear();
		response.sendRedirect(request.getContextPath()+"/pages/cart/cart.jsp");
	}
	public void add2Cart(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String bookId = request.getParameter("bookId");
		Book book = bookService.getBookById(bookId);
		//��ȡsession����
//		HttpSession session = request.getSession();
//		session.setAttribute("title",book.getTitle());
		Cart cart = WEBUtils.getCart(request);
		cart.addBook2Cart(book);
		//ʹ��AJAX����ҳ��
		String title = book.getTitle();
		int totalCount = cart.getTotalCount();
		Gson gson = new Gson();
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("title", title);
		map.put("totalCount", totalCount);
		String str = gson.toJson(map);
		//�������͹��ﳵ���������Ϊ��Ӧ��Ϣ���������
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().print(str);
		
		
//		//��ȡ����ͷ
//		String referer = request.getHeader("referer");
//		response.sendRedirect(referer);
//		
	}

}
