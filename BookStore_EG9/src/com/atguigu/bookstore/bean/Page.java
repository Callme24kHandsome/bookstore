package com.atguigu.bookstore.bean;

import java.io.Serializable;
import java.util.List;

public class Page<T> implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int pageNumber;//��ǰҳ
	private int totalRecord;//���ݿ����ܼ�¼��
	private int pageSize;//ÿһҳ�Ĵ�С
	private List<T> data;
	//�������������ַ��Ϣ��
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
