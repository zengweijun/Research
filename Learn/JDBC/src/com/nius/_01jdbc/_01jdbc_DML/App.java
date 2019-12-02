package com.nius._01jdbc._01jdbc_DML;

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
	
	// DML执行sql后返回受影响的行数

	@Test
	public void insertSQL() {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 主机名：localhost:3306可以省略
			connection = DriverManager.getConnection("jdbc:mysql:///wjtest", "root", "admin123");
			statement = connection.createStatement();
			String sql = "insert t_student (name, age) values ('乔峰', 30)";
			int effects = statement.executeUpdate(sql);
			System.out.println((effects == 1) ? "成功" : "失败");
		} catch (ClassNotFoundException | SQLException e) {
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

	@Test
	public void updateSQL() {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 主机名：localhost:3306可以省略
			connection = DriverManager.getConnection("jdbc:mysql:///wjtest", "root", "admin123");
			statement = connection.createStatement();
			String sql = "update t_student set name = '西门崔雪', age = 28 where id = 1";
			int effects = statement.executeUpdate(sql);
			System.out.println((effects == 1) ? "成功" : "失败");
		} catch (ClassNotFoundException | SQLException e) {
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

	@Test
	public void deleteSQL() {
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 主机名：localhost:3306可以省略
			connection = DriverManager.getConnection("jdbc:mysql:///wjtest", "root", "admin123");
			statement = connection.createStatement();
			String sql = "delete from t_student where id = 1";
			int effects = statement.executeUpdate(sql);
			System.out.println((effects == 1) ? "成功" : "失败");
		} catch (ClassNotFoundException | SQLException e) {
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
