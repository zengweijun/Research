package com.nius._01jdbc._01jdbc_DQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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
	
	// DQL执行sql后返回结果集
	
	// 表中总共有多少条学生信息
	@Test
	public void test1() {
		System.out.println("表中总共有多少条学生信息");
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 主机名：localhost:3306可以省略
			connection = DriverManager.getConnection("jdbc:mysql:///wjtest", "root", "admin123");
			statement = connection.createStatement();
			
			// String sql = "select * from t_student";
			String sql = "select count(id) from t_student";
			// String sql = "select count(id) totalRows from t_student"; // 可以起别名totalRows,方便结果集取值
			set = statement.executeQuery(sql);
			
			//set 最开始指向集合之前的标题行(结果集)
			//title : count(id) 
			//record:   8 
			
			// 别名方式(结果集)
			//title : totalRows 
			//record:   8 
			
			// 先让指针指向下一行
			// getLong(int columnIndex) set中的列号从1开始
			if (set.next()) {
 				long count1 = set.getLong(1);
 				long count2 = set.getLong("count(id)");
 				System.out.println("学生总数getLong_columnIndex：" + count1);
 				System.out.println("学生总数getLong_columnLabel：" + count2);
 				
 				// 推荐方式
 				//long count3 = set.getLong("totalRows");
 				//System.out.println("学生总数起别名方式查询：" + count3);
			}
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
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
	
	// 表中30岁学生信息
	@Test
	public void test2() {
		System.out.println("表中30岁学生信息");
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 主机名：localhost:3306可以省略
			connection = DriverManager.getConnection("jdbc:mysql:///wjtest", "root", "admin123");
			statement = connection.createStatement();
			// String sql = "select * from t_student";
			
			// 
			String sql = "select id sid,name sname,age sage from t_student where age = 30";
			set = statement.executeQuery(sql);
			
			// 由于取了别名，所以结果集中的结果也是别名,需要用别名取值
			// 给age取别名方式 “age as 别名” == “age 别名”， as可以省略
			//set 最开始指向集合之前的标题行(结果集)
			//title  :  sid sname    sage
			//record1:  1   乔峰      30
			//record2:  2   马林      30
			//record3:  3   刘德华    30

			// 注意：如果结果集使用 set.getClob(columnIndex),index从1开始
			while (set.next()) {
				System.out.println(set.getString("sid") + "\t" + set.getString("sname") + "\t" + set.getInt("sage"));
				
			}
			set.close();
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
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

	// 表中所有学生信息
	@Test
	public void test3() {
		System.out.println("表中所有学生信息");
		Connection connection = null;
		Statement statement = null;
		ResultSet set = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			// 主机名：localhost:3306可以省略
			connection = DriverManager.getConnection("jdbc:mysql:///wjtest", "root", "admin123");
			statement = connection.createStatement();
			// String sql = "select * from t_student";
			String sql = "select id,name,age from t_student";
			set = statement.executeQuery(sql);
			
			//set 最开始指向集合之前的标题行
			//title  :  id name    age
			//record1:  1  乔峰     20
			//record2:  2  马林     19
			//record3:  3  刘德华   18

			// 注意：如果结果集使用 set.getClob(columnIndex),index从1开始
			while (set.next()) {
				System.out.println(set.getString("id") + "\t" + set.getString("name") + "\t" + set.getInt("age"));
				
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				set.close();
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
}
