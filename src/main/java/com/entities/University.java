package com.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name = "universities")
public class University implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;



	public University() {
		student = new ArrayList<Student>();
	}
	
	@Id
	@Column(name = "universityId")
	private int universityId;
	
	@Column(name = "universityName")
	private String universityName;
	
	@OneToMany(fetch = FetchType.EAGER, mappedBy="university")
	private List<Student> student;
	
	
	
	public void setUniversityId(int universityId) {
		this.universityId = universityId;
	}
	
	public void setUniversityName(String universityName) {
		this.universityName = universityName;
	}
	
	public void setStudent(List<Student> student) {
		this.student = student;
	}
	
	public int getUniversityId() {
		return universityId;
	}
	
	public String getUniversityName() {
		return universityName;
	}
	
	public List<Student> getStudent() {
		return student;
	}
	
	@Override
	public String toString() {
		return universityName;
	}

}
