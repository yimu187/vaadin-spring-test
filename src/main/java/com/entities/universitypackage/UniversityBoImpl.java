package com.entities.universitypackage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.entities.University;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UniversityBoImpl{

	@Autowired
	private UniversityDaoImpl universityDaoImpl;

	public List<University> listOfUniversity() {
		return universityDaoImpl.listOfUniversity();
	}
}