package com.nius.dao_refacor2.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.nius.dao_refacor2.dao.IStudentDao;
import com.nius.dao_refacor2.domain.Student;
import com.nius.utils.JdbcDaoUtils;

// 注意，这里操作的为预编译sql，预编译sql能预编缓存，性能良好
// 预编译sql中，参数使用“?”占位，注意，问好不能加单引号，错误：'?'，正确 ?
// 使用预编译sql方式不仅能缓存预编译sql，还能防止sql注入，提高性能的同时提高安全性

//PreparedStatement和Statement都可以表示语句对象:
//PreparedStatement相对于Statement的优势:
//  1):拼接SQL上,操作更简单.  
//  2):性能会更加高效,但是需要取决于数据库服务器是否支持.
//    MySQL:不支持预编译sql   Oracle:支持预编译sql
//  3):安全性更高,防止SQL的注入(先讲解登录操作).

public class StudentDaoImpl implements IStudentDao {
	@Override
	public void save(Student stu) {
		// 贾琏欲执事
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = JdbcDaoUtils.getConnection();
			String sql = "insert t_student (name, age) values (?, ?);";
			System.out.println(sql);
			ps = connection.prepareStatement(sql);
			ps.setString(1, stu.getName()); // 设置第一个占位符参数，注意，jdbc中，索引从1开始
			ps.setInt(2, stu.getAge());     // 设置第二个占位符参数
			
			// 创建prepareStatement的时候给了sql，这里不传sql
			// 如果这里调用executeUpdate(sql)将调用父类statement的方法，不能获得动态特性
			int effects = ps.executeUpdate(); 
			System.out.println((effects == 1) ? "操作成功" : "操作失败");
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcDaoUtils.close(connection, ps, null);
		}
	}

	@Override
	public void delete(Long id) {
		// 贾琏欲执事
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = JdbcDaoUtils.getConnection();

			String sql = "delete from t_student where id = ?;";
			System.out.println(sql);

			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);
			
			int effects = ps.executeUpdate(); // 返回受到影响的记录数
			System.out.println((effects == 1) ? "操作成功" : "操作失败");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcDaoUtils.close(connection, ps, null);
		}
	}

	@Override
	public void update(Long id, Student newStu) {
		// 贾琏欲执事
		Connection connection = null;
		PreparedStatement ps = null;
		try {
			connection = JdbcDaoUtils.getConnection();

			String sql = "update t_student set name = ?, age = ? where id = ?;";
			System.out.println(sql);
			
			ps = connection.prepareStatement(sql); 
			ps.setString(1, newStu.getName());
			ps.setInt(2, newStu.getAge());
			ps.setLong(3, id);

			int effects = ps.executeUpdate(); // 返回受到影响的记录数
			System.out.println((effects == 1) ? "操作成功" : "操作失败");

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcDaoUtils.close(connection, ps, null);
		}
	}

	@Override
	public Student get(Long id) {
		// 贾琏欲执事
		Student student = null;
		Connection connection = null;
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcDaoUtils.getConnection();

			String sql = "select * from t_student where id = ?;";
			System.out.println(sql);

			ps = connection.prepareStatement(sql);
			ps.setLong(1, id);

			resultSet = ps.executeQuery();
			// 返回结果集
			if (resultSet.next()) { // 下移一步结果集指针
				student = new Student();
				student.setId(resultSet.getLong("id"));
				student.setName(resultSet.getString("name"));
				student.setAge(resultSet.getInt("age"));
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (Exception e) {
			} finally {
				JdbcDaoUtils.close(connection, ps, resultSet);
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
		PreparedStatement ps = null;
		ResultSet resultSet = null;
		try {
			connection = JdbcDaoUtils.getConnection();

			String sql = "select * from t_student;";
			System.out.println(sql);

			ps = connection.prepareStatement(sql);

			resultSet = ps.executeQuery();
			
			// 返回结果集
			while (resultSet.next()) { // 下移一步结果集指针
				student = new Student();
				student.setId(resultSet.getLong("id"));
				student.setName(resultSet.getString("name"));
				student.setAge(resultSet.getInt("age"));
				list.add(student);
			}
		}  catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JdbcDaoUtils.close(connection, ps, resultSet);
		}
		return list;
	}
}
