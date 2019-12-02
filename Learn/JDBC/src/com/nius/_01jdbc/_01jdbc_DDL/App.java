package com.nius._01jdbc._01jdbc_DDL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

public class App {

	// jdbc操作步骤：贾琏欲执事
	// 贾：加载（注册）驱动
	// 琏：链接数据库对象
	// 欲：创建语句
	// 执：执行sql
	// 事：释放资源
	
	// DDL执行sql后返回0

	// 操作对象是表：DDL
	// 表的创建、删除..
	@Test
	public void testDDL() {
		// 创建学生信息表 t_student，包含id、name、age
		Connection connection = null;
		Statement statement = null;
		try {
			// 1.加载注册器
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2.链接数据库对象
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wjtest", "root", "admin123");

			// 3.创建执行语句
			statement = connection.createStatement();
			// "create table t_student(id bigint primary key auto_increment, name
			// varchar(20), age int);"
			String sql = "CREATE TABLE `t_student` ( `id` bigint(20) PRIMARY KEY AUTO_INCREMENT, `name` varchar(20) DEFAULT NULL, `age` int(11) DEFAULT NULL) CHARSET=utf8";

			// 4.执行sql语句
			statement.executeUpdate(sql);

			System.out.println("创建表完成");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
