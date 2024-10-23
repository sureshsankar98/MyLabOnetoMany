package com.ibm.LabOneToMany.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ibm.LabOneToMany.model.Employee;

public interface EmployeeDao extends JpaRepository<Employee, Long> {
	public List<Employee> findByEmailId(String emailId);

}
