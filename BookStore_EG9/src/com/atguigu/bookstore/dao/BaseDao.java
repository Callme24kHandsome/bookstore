package com.atguigu.bookstore.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.atguigu.bookstore.bean.Book;
import com.atguigu.bookstore.bean.Page;
import com.atguigu.bookstore.utils.JDBCUtils;

/**
 * 瀹氫箟涓�簺鏁版嵁搴撶殑鍩烘湰鎿嶄綔
 * 杩欎釜绫绘槸涓撻棬鐢ㄦ潵琚叾浠朌ao缁ф壙
 * @author lilichao
 *
 * @param <T>
 */
public class BaseDao<T> {
	
	private QueryRunner runner = new QueryRunner();
	
	private Class<T> type;
	
	public BaseDao() {
		Class cla = this.getClass();
		
		ParameterizedType pt = (ParameterizedType) cla.getGenericSuperclass();
		
		 Type[] types = pt.getActualTypeArguments();
		 
		 this.type = (Class<T>) types[0];
		
	}
	
	/**
	 * 鏌ヨ涓�釜瀵硅薄
	 * @param sql
	 * @param params
	 * @return
	 */
	public T getBean(String sql , Object ... params){
		T t = null;
		
		//鑾峰彇鏁版嵁搴撹繛鎺�
		Connection conn = JDBCUtils.getConnection();
		
		try {
			t = runner.query(conn, sql, new BeanHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
		} 
//		finally{
//			JDBCUtils.releaseConnection(conn);
//		}
		
		return t;
	}
	
	/**
	 * 鏌ヨ涓�粍瀵硅薄鍒楄〃
	 * @param sql
	 * @param params
	 * @return
	 */
	public List<T> getBeanList(String sql , Object ... params){
		
		List<T> list = null;
		
		//鑾峰彇鏁版嵁搴撹繛鎺�
		Connection conn = JDBCUtils.getConnection();
		
		try {
			list = runner.query(conn, sql, new BeanListHandler<T>(type), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
//		finally{
//			JDBCUtils.releaseConnection(conn);
//		}
		return list;
		
	}
	
	
	/**
	 * 鏇存柊鏁版嵁搴撴搷浣滅殑鏂规硶
	 * @param sql
	 * @param params
	 * @return
	 */
	public int update(String sql , Object ... params){
		int count = 0;
		
		Connection conn = JDBCUtils.getConnection();
		try {
			count = runner.update(conn, sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
//		finally{
//			JDBCUtils.releaseConnection(conn);
//		}
		
		return count;
		
	}
	/**
	 * 从数据库获取查询中获取第一行第一格的值
	 * @param sql
	 * @param params
	 * @return
	 */
	public Object getSingleValue(String sql,Object ... params) {
		Connection conn = JDBCUtils.getConnection();
		Object obj = null;
		try {
			// ScalarHandler 可以获取查询表格第一行第一格的数据
			obj =runner.query(conn, sql, new ScalarHandler(), params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		finally{
//			JDBCUtils.releaseConnection(conn);
//		}
		return obj;
	}

	public Page<Book> findBook(Page<Book> page) {
		// TODO Auto-generated method stub
		return null;
	}
	/**
	 * 批量操作数据库的方法
	 * @param sql
	 * @param params
	 * Object[][] params是一个二纬数组
	 * 第一维：SQL语句操作的次数
	 * 第二维：每一次sql语句操作使用的参数
	 */
	public void batch(String sql,Object[][] params){
		Connection conn = JDBCUtils.getConnection();
		try {
			runner.batch(conn, sql, params);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		JDBCUtils.releaseConnection();
		
	}

}
