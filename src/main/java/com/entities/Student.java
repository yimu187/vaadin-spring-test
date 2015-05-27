package com.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ForeignKey;

/**
 * Created by TOSHIBA on 5.5.2015.
 */
@Entity
@Table(name = "student")
public class Student implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "studentId")
	private int studentId;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;

	@ManyToOne(fetch = FetchType.EAGER)
	@ForeignKey(name = "FK_UNI")
	@JoinColumn(name = "universityId")
	private University university;

	@Column(name = "department")
	private String department;

	@Column(name = "mail")
	private String mail;

	@Column(name = "sex")
	private String sex;

	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void setUniversity(University university) {
		this.university = university;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public int getStudentId() {
		return studentId;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public University getUniversity() {
		return university;
	}

	public String getDepartment() {
		return department;
	}

	public String getMail() {
		return mail;
	}

	public String getSex() {
		return sex;
	}

	@Override
	public String toString() {
		return "Öğrenci Numaranız : " + studentId + "</br>Ad : " + firstName
				+ "</br>Soyad : " + lastName + "</br>Cinsiyetiniz : " + sex
				+ "</br>Üniversite : " + university.toString() + "</br>Bölüm : "
				+ department + "</br>E-mail : " + mail + "</br></br>";
	}

	public String htmlTableFormatStudent() {

		return "<table border='1'>" + "<tr>" + "<td>Ad : </td>" + "<td>"
				+ firstName + "</td>" + "</tr>" + "<tr>" + "<td>Soyad : </td>"
				+ "<td>" + lastName + "</td>" + "</tr>" + "<tr>"
				+ "<td>Cinsiyet : </td>" + "<td>" + sex + "</td>" + "</tr>"
				+ "<tr>" + "<td>Üniversite : </td>" + "<td>" + university.toString()
				+ "</td>" + "</tr>" + "<tr>" + "<td>Bölüm : </td>" + "<td>"
				+ department + "</td>" + "</tr>" + "<tr>"
				+ "<td>E-mail : </td>" + "<td>" + mail + "</td>" + "</tr>"
				+ "</table></br></br>";
	}
}
