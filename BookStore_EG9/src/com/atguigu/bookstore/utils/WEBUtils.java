package com.atguigu.bookstore.utils;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.beanutils.BeanUtils;

import com.atguigu.bookstore.bean.Cart;
import com.atguigu.bookstore.bean.User;

public class WEBUtils {

	public static <T>T param2Bean(HttpServletRequest request, T t) {
		
		Map map = request.getParameterMap();
		try {
			BeanUtils.populate(t, map);
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return t;
	}

	public static String getPath(HttpServletRequest request) {
		//动态地获取请求地址
		String uri = request.getRequestURI();
		String queryString = request.getQueryString();
		String path = uri + "?" + queryString;
		if(path.contains("&pageNumber")){
			path = path.substring(0, path.indexOf("&pageNumber"));
		}
		return path;
	}
/**
 * 获取购物车对象分
 * @param request
 * @return
 */
	public static Cart getCart(HttpServletRequest request) {
		HttpSession session = request.getSession();
		Cart cart = (Cart) session.getAttribute("cart");
		if(cart==null){
			cart = new Cart();
			session.setAttribute("cart", cart);
		}
		return cart;
	}

}
