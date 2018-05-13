package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * ��װ���ﳵ�Ĺ�����
 * @author DD
 *
 */
public class CartItem implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//�������ͼ�����Ϣ 
	private Book book;
	//ͼ�����и�ͼ�������
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
