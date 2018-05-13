package com.atguigu.bookstore.dao.impl;

import java.util.List;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.dao.BaseDao;
import com.atguigu.bookstore.dao.BookDao;


public class BookDaoImpl extends BaseDao<Book> implements BookDao {

	@Override
	public int savaBook(Book book) {
		String sql = "INSERT INTO bs_book(title,author,price,sales,stock,img_path)"
				+ "VALUE(?,?,?,?,?,?)";

		return this.update(sql, book.getTitle(), book.getAuthor(),
				book.getPrice(), book.getSales(), book.getStock(),
				book.getImgPath());
	}

	@Override
	public int delBook(String bookId) {
		String sql = "DELETE FROM bs_book WHERE id = ?";
		
		return this.update(sql, bookId);
	}

	@Override
	public int updateBook(Book book) {
		String sql = "UPDATE bs_book SET title = ?,author = ?, price = ?, sales = ?, stock = ?, img_path = ? WHERE id = ?";
		
		return this.update(sql, book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath(),book.getId()
			);
	}

	@Override
	public List<Book> getBookList() {
		String sql = "SELECT id, title, author, price, sales, stock, img_path imgPath FROM bs_book ";
		return this.getBeanList(sql);
	}

	@Override
	public Book getBookById(String bookId) {
		String sql = "SELECT id, title, author, price, sales, stock, img_path imgPath FROM bs_book WHERE id = ?";
		return this.getBean(sql, bookId);
	}

	@Override
	public Page<Book> findBook(Page<Book> page) {
		String sql = "SELECT COUNT(*) FROM bs_book";
		long totalRecord = (long) this.getSingleValue(sql);
		page.setTotalRecord((int) totalRecord);
		/**
		 * pageNumber不能＜1或者大于页面总数
		 */
		int pn = page.getPageNumber();
		if(pn<1){
			pn = 1;
		}
		if(pn>page.getTotalPage()){
			pn = page.getTotalPage();
		}
		page.setPageNumber(pn);
		sql = "SELECT id, title, author, price, sales, stock, img_path imgPath FROM bs_book limit ?,?";
		List<Book> list = this.getBeanList(sql, page.getIndex(),page.getPageSize());
		page.setData(list);
		return page;
	}

	@Override
	public Page<Book> findBookByPrice(Page<Book> page, double minPrice,
			double maxPrice) {
		String sql = "SELECT COUNT(*) FROM bs_book WHERE price >= ? AND price <= ? ";
		long totalRecord = (long) this.getSingleValue(sql, minPrice,maxPrice);
		page.setTotalRecord((int) totalRecord);
		/**
		 * pageNumber不能＜1或者大于页面总数
		 */
		int pn = page.getPageNumber();
		if(pn<1){
			pn = 1;
		}
		if(pn>page.getTotalPage()){
			pn = page.getTotalPage();
		}
		page.setPageNumber(pn);
		sql = "SELECT id, title, author, price, sales, stock, img_path imgPath"
				+ " FROM bs_book WHERE price >= ? and price <= ? limit ?,?";
		List<Book> list = this.getBeanList(sql, minPrice,maxPrice,page.getIndex(),page.getPageSize());
		page.setData(list);
		return page;
	}

	@Override
	public void batchUpdateSalesAndStock(Object[][] params) {
		String sql = "UPDATE bs_book SET sales = ? , stock = ? WHERE id = ?";
		this.batch(sql, params);
	}

	

}
