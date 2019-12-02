package com.nius.dao_refacor3_druid.test;

import java.sql.ResultSet;
import java.util.List;

import org.junit.Test;

import com.nius.dao.dao.IStudentDao;
import com.nius.dao.domain.Student;
import com.nius.dao_refacor3_druid.dao.impl.StudentDaoImpl;
import com.nius.dao_refacor3_druid.handler.IResultSetHandler;
import com.nius.dao_refacor3_druid.util.JdbcTemplate;

public class StudentDaoTest {

	@Test
	public void save() {
		IStudentDao dao = (IStudentDao) new StudentDaoImpl();
		Student stu = new Student();
		stu.setName("mashibing4");
		stu.setAge(88);
		dao.save(stu);
	}
	
	@Test
	public void delete(){
		IStudentDao dao = (IStudentDao) new StudentDaoImpl();
		dao.delete(7L);
	}
	
	@Test
	public void update(){
		IStudentDao dao = (IStudentDao) new StudentDaoImpl();
		Student stu = new Student();
		stu.setName("金城武_3");
		stu.setAge(123);
		dao.update(8L, stu);
	}
	
	@Test
	public void get(){
		IStudentDao dao = (IStudentDao) new StudentDaoImpl();
		Student student = dao.get(11L);
		System.out.println(student);
	}
	
	@Test
	public void getAll(){
		IStudentDao dao = (IStudentDao) new StudentDaoImpl();
		List<Student> list = dao.getAll();
		for (Student student : list) {
			System.out.println(student);
		}
	}
	
	@Test
	public void totalCount(){
		Long count = JdbcTemplate.query("select count(id) from t_student", new IResultSetHandler<Long>() {
			@Override
			public Long handle(ResultSet rs) throws Exception {
				if (rs.next()) {
					return rs.getLong(1);
				}
				return 0L;
			}
		});
		System.out.println(count);
	}

}
