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
 * ��װ���ﳵ����Ϣ
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
			//���ﳵ���޴˹�����
			cartItem = new CartItem();
			cartItem.setBook(book);
			cartItem.setCount(1);
			//���ù�������빺�ﳵ��
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
		//�޸Ĺ����������
		map.get(bookId).setCount(count);
		
	}
	public Map<String, CartItem> getMap() {
		return map;
	}
	public void setMap(Map<String, CartItem> map) {
		this.map = map;
	}
	//�������б�list����
	private Map<String, CartItem> map = new LinkedHashMap<String, CartItem>();
	public List<CartItem> getCartItems(){
		Collection<CartItem> values = map.values();
		List<CartItem> cartItems = new ArrayList<CartItem>(values);
		return cartItems;
	}
//	@Override
//	public void sessionDidActivate(HttpSessionEvent se) {
//		System.out.println("һ��cart�����Ӳ�̶�ȡ������...");  
//		
//	}
//	@Override
//	public void sessionWillPassivate(HttpSessionEvent se) {
//		System.out.println("һ��cart���󱣴浽��Ӳ��...");
//		
//	} 
}