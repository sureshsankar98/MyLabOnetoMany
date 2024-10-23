package com.ibm.LabOneToMany.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Employee {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long Emp_id;
	private String firstName;
	private String lastName;
	@Column(name = "email_Id", unique = true, nullable = false)
	private String emailId;
	// @OneToMany(mappedBy="employee", fetch=FetchType.EAGER)//employee attribute of
	// Mobile entity
	@OneToMany(mappedBy = "employee", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	// @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private List<Mobile> mobileNumbers = new ArrayList<Mobile>();
	// @OneToOne(mappedBy = "employee")
	// private Mobile mobileNumber;

	public Employee() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * public Employee(String firstName, String lastName, String emailId) { super();
	 * this.firstName = firstName; this.lastName = lastName; this.emailId = emailId;
	 * }
	 */

	public Long getEmp_id() {
		return Emp_id;
	}

	public void setEmp_id(Long emp_id) {
		Emp_id = emp_id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public List<Mobile> getMobileNumbers() {
		return mobileNumbers;
	}

	public void setMobileNumbers(List<Mobile> mobileNumbers) {
		this.mobileNumbers = mobileNumbers;
	}

	@Override
	public String toString() {
		return "Employee [Emp_id=" + Emp_id + ", firstName=" + firstName + ", lastName=" + lastName + ", emailId="
				+ emailId + ", ml=" + mobileNumbers + "]";
	}

}