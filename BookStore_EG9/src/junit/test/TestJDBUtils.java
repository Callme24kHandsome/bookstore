package junit.test;

import java.sql.Connection;

import org.junit.Test;

import com.atguigu.bookstore.utils.JDBCUtils;

public class TestJDBUtils {

	@Test
	public void testJDBUTils() {
		
		//获取数据库连接
		Connection conn = JDBCUtils.getConnection();
		
		System.out.println(conn);
		
		//释放数据库连接
		JDBCUtils.releaseConnection(conn);
		
		
	}

}
