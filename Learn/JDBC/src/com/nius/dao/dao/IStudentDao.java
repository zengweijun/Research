package com.nius.dao.dao;


import java.util.List;

import com.nius.dao.domain.Student;

public interface IStudentDao {
	void save(Student stu);
	void delete(Long id);
	void update(Long id, Student newStu);
	Student get(Long id);
	List<Student> getAll();
}
