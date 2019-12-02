package com.nius.dao_refacor3_druid.dao.impl;

import java.util.List;

import com.nius.dao.dao.IStudentDao;
import com.nius.dao.domain.Student;
import com.nius.dao_refacor3_druid.handler.BeaListnHandler;
import com.nius.dao_refacor3_druid.handler.BeanHandler;
import com.nius.dao_refacor3_druid.util.JdbcTemplate;

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
		JdbcTemplate.update("insert t_student (name, age) values (?, ?);", stu.getName(), stu.getAge());
	}

	@Override
	public void delete(Long id) {
		JdbcTemplate.update("delete from t_student where id = ?;", id);
	}

	@Override
	public void update(Long id, Student newStu) {
		JdbcTemplate.update("update t_student set name = ?, age = ? where id = ?;", newStu.getName(), newStu.getAge(),
				id);
	}

	@Override
	public Student get(Long id) {
		return JdbcTemplate.query("select * from t_student where id = ?;", new BeanHandler<>(Student.class),
				id);
	}

	@Override
	public List<Student> getAll() {
		return JdbcTemplate.query("select * from t_student;", new BeaListnHandler<>(Student.class));
	}
}
