package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

/**
 * 封装购物车的信息
 * @author DD
 *
 */
public class Cart implements Serializable {
	private static final long serialVersionUID = 1L;
	public int getTotalCount() {
		int totalCount  = 0;
		List<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
			totalCount += cartItem.getCount();
		}
		return totalCount;
	}
	public double getTotalAmount() {
		BigDecimal totalAmount = new BigDecimal("0");
		List<CartItem> cartItems = getCartItems();
		for (CartItem cartItem : cartItems) {
			BigDecimal amount = new BigDecimal(cartItem.getAmount()+"");
			totalAmount = totalAmount.add(amount);
		}
		
		return totalAmount.doubleValue();
	}
	public void delCartItem(String bookId){
		getMap().remove(bookId);
	}
	public void clear(){
		getMap().clear();
	}
	public void addBook2Cart(Book book){
		CartItem cartItem = getMap().get(book.getId()+"");
		if(cartItem == null){
			//购物车中无此购物项
			cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setCount(1);
			//将该购物项加入购物车中
			map.put(book.getId()+"", cartItem);
		}else{
			cartItem.setCount(cartItem.getCount() + 1);
		}
	}
	public void updateCount(String bookId,String countStr){
		int count = 1;
		try {
			count = Integer.parseInt(countStr);
		} catch (NumberFormatException e) {}
		//修改购物项的数量
		map.get(bookId).setCount(count);
		
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	//购物项列表list对象
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	public List<CartItem> getCartItems(){
		Collection<CartItem> values = map.values();
		List<CartItem> cartItems = new ArrayList<CartItem>(values);
		return cartItems;
	}
//	@Override
//	public void sessionDidActivate(HttpSessionEvent se) {
//		System.out.println("一个cart对象从硬盘读取出来了...");  
//		
//	}
//	@Override
//	public void sessionWillPassivate(HttpSessionEvent se) {
//		System.out.println("一个cart对象保存到了硬盘...");
//		
//	} 
}