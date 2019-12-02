package com.nius.dao.test;

import java.util.List;

import org.junit.Test;

import com.nius.dao.dao.IStudentDao;
import com.nius.dao.dao.impl.StudentDaoImpl;
import com.nius.dao.domain.Student;

public class StudentDaoTest {

	@Test
	public void save() {
		IStudentDao dao = (IStudentDao) new StudentDaoImpl();
		Student stu = new Student();
		stu.setName("刘德华");
		stu.setAge(55);
		dao.save(stu);
	}
	
	@Test
	public void delete(){
		IStudentDao dao = (IStudentDao) new StudentDaoImpl();
		dao.delete(2L);
	}
	
	@Test
	public void update(){
		IStudentDao dao = (IStudentDao) new StudentDaoImpl();
		Student stu = new Student();
		stu.setName("金城武");
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

}
