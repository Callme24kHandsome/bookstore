package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 封装购物车的购物项
 * @author DD
 *
 */
public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//所购买的图书的信息 
	private Book book;
	//图书项中该图书的数量
	private int count;
	//private double amount;
	public Book getBook() {
		return book;
	}
	public void setBook(Book book) {
		this.book = book;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		BigDecimal count = new BigDecimal(this.count);
		BigDecimal price = new BigDecimal(book.getPrice()+"");
		return price.multiply(count).doubleValue();
	}
	public CartItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	
}
