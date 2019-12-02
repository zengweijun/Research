package com.nius.DBCP连接池;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.junit.Test;


public class App {
//	1.在Java中,连接池使用javax.sql.DataSource接口来表示连接池(数据源).
//
//	注意:DataSource和JDBC一样,仅仅只是接口,SUN公司自己不提供实现,由第三方组织提供.
//	常用的DataSource的实现:
//	  druid: 阿里巴巴提供的连接池(德鲁伊),号称是世界上最好的连接池,也不仅仅是连接池.
//	  DBCP:  Spring推荐的.
//	  C3P0:   Hibernate推荐的,在2007年开始就没有再更新了,性能比较差.
//	DataSource(数据源)和连接池(Connection Pool)是同一个概念.
//	======================================================================
//	使用连接池和不使用连接池的区别在哪里?
//	1):获取连接的方式不同. (不使用时每次都创建链接对象，使用后可复用)
//	2):释放资源时不同.(不使用时每次都销毁链接，使用后只是)
	
//	2.学习连接池的操作:
//    主要是如何创建DataSource对象,再从DataSource对象中获取Connection对象,以后的操作和以前相同.
//	  Connection conn =  DataSource对象.getConnection();
//	  不同的数据库连接池,其实就是在创建DataSource的方式上有所不同.


//	3.学习DBCP连接池:
//		拷贝jar:
//		commons-dbcp-1.3.jar      commons-pool-1.5.6.jar . 注意是jar包,不是zip包.
//		查阅文档:commons-dbcp-1.3-src\doc\BasicDataSourceExample.java,如何创建DataSource对象.

//	4. 连接池 Connect-pool 也称为 DataSource
	
	@Test
	public void test() throws SQLException {
		Connection cnn = DBCPUtil.getConnection();
		System.out.println(cnn);
		long begin = System.currentTimeMillis();
		for (int i = 0; i < 5000; i++) {
			String sql = "insert into t_performance (name, age) values (?, ?)";
			PreparedStatement st = cnn.prepareStatement(sql);
			st.setString(1, "AA");
			st.setInt(2, 12);;
			st.executeUpdate();
		}
		System.out.println(System.currentTimeMillis() - begin);
	}
}
