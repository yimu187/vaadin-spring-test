package com.entities.studentpackage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.Student;


@Repository
public class StudentDaoImpl{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public void save(Student student) {
		sessionFactory.getCurrentSession().save(student);
	}
	
	@SuppressWarnings("unchecked")
	public List<Student> listStudentOnDatabase() {
		List<Student> listOfStudent = (List<Student>) sessionFactory.getCurrentSession().createCriteria(Student.class).list();
		return listOfStudent;
	}
}