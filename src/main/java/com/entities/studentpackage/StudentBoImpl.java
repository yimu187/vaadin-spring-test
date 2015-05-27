package com.entities.studentpackage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Student;

@Service
@Transactional
public class StudentBoImpl{
	
	@Autowired
	private StudentDaoImpl studentDaoImpl;
	
	public void save(Student student) {
		studentDaoImpl.save(student);
	}
	
	public List<Student> listStudentOnDatabase() {
		return studentDaoImpl.listStudentOnDatabase();
	}

}