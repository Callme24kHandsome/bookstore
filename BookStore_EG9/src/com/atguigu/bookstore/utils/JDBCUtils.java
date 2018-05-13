package com.atguigu.bookstore.utils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 获取和释放数据库连接的工具类
 * 
 * @author lilichao
 * 
 */
public class JDBCUtils {

	private static DataSource dataSource = new ComboPooledDataSource(
			"webDataSource");
	private static Map<Thread, Connection> map = new ConcurrentHashMap<Thread, Connection>();
	private static ThreadLocal<Connection> threadLocal = new ThreadLocal<Connection>();

	public static Connection getConnection() {
//		if (conn == null) {
//			try {
//				conn = dataSource.getConnection();
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		}
//		return conn;
//		Connection conn = map.get(Thread.currentThread());
//		if(conn == null){
//			try {
//				conn = dataSource.getConnection();
//				map.put(Thread.currentThread(), conn);
//			} catch (SQLException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//		}
//		return conn;
		Connection conn = threadLocal.get();
		if(conn == null){
			try {
				conn = dataSource.getConnection();
				threadLocal.set(conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return conn;
	}

	// public static Connection getConnection(){
	//
	// Connection conn = null;
	//
	// try {
	// conn = dataSource.getConnection();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	//
	// return conn;
	// }
	public static void releaseConnection() {
		Connection conn = threadLocal.get();
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		threadLocal.remove();
//		Connection conn = map.get(Thread.currentThread());
//		try {
//			conn.close();
//			map.remove(Thread.currentThread());
//		} catch (SQLException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}
//
//		if (conn != null) {
//			try {
//				conn.close();
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//		}
//		conn = null;
	}

	/**
	 * 释放数据库连接的方法
	 * 
	 * @param conn
	 */
	public static void releaseConnection(Connection conn) {

		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
