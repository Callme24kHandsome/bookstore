package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageNumber;//当前页
	private int totalRecord;//数据库中总记录数
	private int pageSize;//每一页的大小
	private List<T> data;
	//用来保存请求地址信息；
	private String path;
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public int getIndex() {
		return (pageNumber - 1)* pageSize;
	}
	public int getTotalPage() {
		return (totalRecord + pageSize -1) / pageSize;
	}
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> date) {
		this.data = date;
	}
	public int getPageNumber() {
		if(pageNumber<1){
			return 1;
		}
		if(pageNumber>getTotalPage()){
			return getTotalPage();
		}
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getTotalRecord() {
		return totalRecord;
	}
	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
}
