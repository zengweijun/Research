package com.nius.dao_refacor2.dao;


import java.util.List;

import com.nius.dao_refacor2.domain.Student;

public interface IStudentDao {
	void save(Student stu);
	void delete(Long id);
	void update(Long id, Student newStu);
	Student get(Long id);
	List<Student> getAll();
}
