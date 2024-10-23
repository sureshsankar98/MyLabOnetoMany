package com.ibm.LabOneToMany.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.ibm.LabOneToMany.dto.EmployeeDto;
import com.ibm.LabOneToMany.model.Employee;
import com.ibm.LabOneToMany.model.Mobile;
import com.ibm.LabOneToMany.myException.EmployeeAlreadyPresent;
import com.ibm.LabOneToMany.myException.EmployeeEmailPresent;
import com.ibm.LabOneToMany.myException.ResourceNotFound;
import com.ibm.LabOneToMany.repository.EmployeeDao;
import com.ibm.LabOneToMany.util.EndUserMessage;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeDao empRepository;
//	@Autowired
//	private MobileRepository mobRepository;

	// tested 07062021(backend)
	public Employee save(Employee employee) throws EmployeeAlreadyPresent, EmployeeEmailPresent {
		System.out.println("service-save()" + employee);
		
		Optional<Employee> optEmp = empRepository.findById(employee.getEmp_id());

		if (optEmp.isPresent()) {
			throw new EmployeeAlreadyPresent(EndUserMessage.employeeAllreadyExists);
		}

		List<Employee> el = empRepository.findByEmailId(employee.getEmailId());
		System.out.println("No error upto here");
		if (el.size() > 0) {
			System.out.println("email duplicates");
			throw new EmployeeEmailPresent(EndUserMessage.emailAllreadyExists);
		}

		Employee emp = new Employee();

		emp.setEmp_id(employee.getEmp_id());
		emp.setFirstName(employee.getFirstName());
		emp.setLastName(employee.getLastName());
		emp.setEmailId(employee.getEmailId());
		emp.setMobileNumbers(employee.getMobileNumbers());
		// emp.setMobileNumbers
		// emp.setMobileNumbers();
		System.out.println("Inside service 1");

		for (Mobile mobile : employee.getMobileNumbers()) {
			System.out.println("inside for loop");
			mobile.setEmployee(emp);
		}

		return empRepository.save(emp);
		// return employee;
	}

	public List<Employee> getAllEmployees() {
		List<Employee> el = new ArrayList<>();
		el = empRepository.findAll();
		return el;
	}

	// tested 07062021(backend)
	public ResponseEntity<Employee> getEmployeeById(Long id) throws ResourceNotFound {
		System.out.println("service getEmployeeById()" + id);
		Employee emp = empRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound(EndUserMessage.employeeNotFound + id));
		return ResponseEntity.ok(emp);
	}

	// tested 07062021(backend)
	public ResponseEntity<Employee> updateEmployeeById(EmployeeDto emp) throws ResourceNotFound {
		// public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id,
		// @RequestBody EmployeeDto emp) throws ResourceNotFound {
		System.out.println("0.Service-updateEmployeeById");
		System.out.println("1.emp.getFirstName() service" + emp.getFirstName());
		System.out.println("1.emp.getEmp_id() service" + emp.getEmp_id());
		Employee empN = empRepository.findById(emp.getEmp_id())
				.orElseThrow(() -> new ResourceNotFound(EndUserMessage.employeeNotFound + emp.getEmp_id()));
		System.out.println("1a.emp.getFirstName() service" + emp.getFirstName());
		empN.setFirstName(emp.getFirstName());
		empN.setLastName(emp.getLastName());
		empN.setEmailId(emp.getEmailId());
		Employee updatedEmployee = empRepository.save(empN);
		System.out.println("2.emp service" + emp);
		return ResponseEntity.ok(updatedEmployee);
	}

	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) throws ResourceNotFound {
		Employee empN = empRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFound("Employee with id " + id + "not found"));
		empRepository.delete(empN);
		Map<String, Boolean> response = new HashMap();
		response.put("Deleted", true);
		return ResponseEntity.ok(response);

	}

}
