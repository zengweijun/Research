package com.nius.dao.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.nius.dao.dao.IStudentDao;
import com.nius.dao.domain.Student;

public class StudentDaoImpl implements IStudentDao {
	@Override
	public void save(Student stu) {
		// 贾琏欲执事
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/wjtest";
			String username = "root";
			String password = "admin123";
			connection = DriverManager.getConnection(url, username, password);

			StringBuilder sb = new StringBuilder();
			sb.append("insert t_student (name, age) values");
			sb.append(" (");
			sb.append("'" + stu.getName() + "'"); // 字符串用单引号
			sb.append(" ,");
			sb.append(stu.getAge());
			sb.append(");");
			System.out.println(sb.toString());

			statement = connection.createStatement();

			int effects = statement.executeUpdate(sb.toString()); // 返回受到影响的记录数
			System.out.println((effects == 1) ? "操作成功" : "操作失败");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void delete(Long id) {
		// 贾琏欲执事
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/wjtest";
			String username = "root";
			String password = "admin123";
			connection = DriverManager.getConnection(url, username, password);

			StringBuilder sb = new StringBuilder();
			sb.append("delete from t_student where id = ");
			sb.append(id).append(";");
			System.out.println(sb.toString());

			statement = connection.createStatement();
			int effects = statement.executeUpdate(sb.toString()); // 返回受到影响的记录数
			System.out.println((effects == 1) ? "操作成功" : "操作失败");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public void update(Long id, Student newStu) {
		// 贾琏欲执事
		Connection connection = null;
		Statement statement = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/wjtest";
			String username = "root";
			String password = "admin123";
			connection = DriverManager.getConnection(url, username, password);

			StringBuilder sb = new StringBuilder();
			sb.append("update t_student ");
			sb.append("set name = ").append("'" + newStu.getName() + "'"); // 字符串用单引号
			sb.append(",");
			sb.append(" age = ").append(newStu.getAge());
			sb.append(" where id = ").append(id);
			sb.append(";");
			System.out.println(sb.toString());

			statement = connection.createStatement();

			int effects = statement.executeUpdate(sb.toString()); // 返回受到影响的记录数
			System.out.println((effects == 1) ? "操作成功" : "操作失败");

		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (statement != null) {
					statement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (connection != null) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public Student get(Long id) {
		// 贾琏欲执事
		Student student = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/wjtest";
			String username = "root";
			String password = "admin123";
			connection = DriverManager.getConnection(url, username, password);

			StringBuilder sb = new StringBuilder();
			sb.append("select * from t_student ");
			sb.append(" where id = ").append(id);
			sb.append(";");
			System.out.println(sb.toString());

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sb.toString());
			// 返回结果集
			if (resultSet.next()) { // 下移一步结果集指针
				student = new Student();
				student.setId(resultSet.getLong("id"));
				student.setName(resultSet.getString("name"));
				student.setAge(resultSet.getInt("age"));
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e) {
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return student;
	}

	@Override
	public List<Student> getAll() {
		// 贾琏欲执事
		List<Student> list = new ArrayList<Student>();
		Student student = null;
		Connection connection = null;
		Statement statement = null;
		ResultSet resultSet = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			String url = "jdbc:mysql://localhost:3306/wjtest";
			String username = "root";
			String password = "admin123";
			connection = DriverManager.getConnection(url, username, password);

			String sql = "select * from t_student;";
			System.out.println(sql);

			statement = connection.createStatement();

			resultSet = statement.executeQuery(sql);
			// 返回结果集
			while (resultSet.next()) { // 下移一步结果集指针
				student = new Student();
				student.setId(resultSet.getLong("id"));
				student.setName(resultSet.getString("name"));
				student.setAge(resultSet.getInt("age"));
				list.add(student);
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e) {
			} finally {
				try {
					if (statement != null) {
						statement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					try {
						if (connection != null) {
							connection.close();
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
			}
		}
		return list;
	}
}
