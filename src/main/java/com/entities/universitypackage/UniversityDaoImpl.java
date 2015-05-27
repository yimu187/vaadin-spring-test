package com.entities.universitypackage;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.entities.University;

@Repository
public class UniversityDaoImpl{
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public List<University> listOfUniversity() {
		@SuppressWarnings("unchecked")
		List<University> listOfUniversity = (List<University>) sessionFactory.getCurrentSession().createCriteria(University.class).list();
		return listOfUniversity;
	}	
}