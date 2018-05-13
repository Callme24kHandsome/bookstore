package com.atguigu.bookstore.bean;
public class OrderItem {
	//订单项的编号
	 private Integer id;
	 //当前图书项的数量
	 private int count;
	 //当前图书的金额
	 private double amount;
	//书名
	 private String title;
	 //书的作者
	 private String author;
	 private double price;
	 public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getImgPath() {
		return imgPath;
	}
	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
	public String getOrder_id() {
		return order_id;
	}
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}
	public OrderItem() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderItem(Integer id, int count, double amount, String title,
			String auhtor, double price, String imgPath, String order_id) {
		super();
		this.id = id;
		this.count = count;
		this.amount = amount;
		this.title = title;
		this.author = auhtor;
		this.price = price;
		this.imgPath = imgPath;
		this.order_id = order_id;
	}
	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", count=" + count + ", amount="
				+ amount + ", title=" + title + ", auhtor=" + author
				+ ", price=" + price + ", imgPath=" + imgPath + ", order_id="
				+ order_id + "]";
	}
	//书的封面
	 private String imgPath;
	 //订单项的属于哪个订单
	 private String order_id;

}
