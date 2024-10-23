package com.ibm.LabOneToMany.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ibm.LabOneToMany.dto.EmployeeDto;
import com.ibm.LabOneToMany.dto.SuccessMessageDto;
import com.ibm.LabOneToMany.model.Employee;
import com.ibm.LabOneToMany.myException.EmployeeAlreadyPresent;
import com.ibm.LabOneToMany.myException.EmployeeEmailPresent;
import com.ibm.LabOneToMany.myException.ResourceNotFound;
import com.ibm.LabOneToMany.service.EmployeeService;
import com.ibm.LabOneToMany.util.EndUserMessage;

@RestController
@RequestMapping("/EmpApi1toMany/v1.2CG")
@CrossOrigin
public class EmployeeController {

	@Autowired
	private EmployeeService empService;

	// tested 07062021
	@PostMapping("/employees")
	public SuccessMessageDto createEmployee(@RequestBody Employee empDto)
			throws EmployeeAlreadyPresent, EmployeeEmailPresent {
		System.out.println("employee= " + empDto);
		/*
		 * Employee e=new Employee(); e.setFirstName(empDto.getFirstName());
		 * e.setLastName(empDto.getLastName());
		 * 
		 * e.setEmailId(empDto.getEmailId());
		 */
		// e.setMobileNumbers(empDto.getMobileNumbers());

		Employee e = empService.save(empDto);
		return new SuccessMessageDto(EndUserMessage.employeeAddedSuccessFully + e.getEmp_id());
	}

	/*
	 * @PutMapping("/employees") public Employee updateEmployee(@RequestBody
	 * Employee emp) { System.out.println("employee= " + emp); return
	 * empService.save(emp); }
	 */
	@GetMapping("/employees")
	public List<Employee> getAllEmployees() {
		List<Employee> el = empService.getAllEmployees();
		// el = empRepository.findAll();
		return el;
	}

	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) throws ResourceNotFound {
		System.out.println("Controller getEmployeeById()");
		ResponseEntity<Employee> emp = empService.getEmployeeById(id);
		// orElseThrow(() -> new ResourceNotFound("Employee with id " + id + "not
		// found"));
		return emp;
	}

	// tested 07062021(backend)
	@PutMapping("/employees")
	public ResponseEntity<Employee> updateEmployeeById(@RequestBody EmployeeDto emp)
			// public ResponseEntity<Employee> updateEmployeeById(@PathVariable Long id,
			// @RequestBody EmployeeDto emp)
			throws ResourceNotFound {
		System.out.println("controller updateEmployeeById");
		ResponseEntity<Employee> updatedEmployee = empService.updateEmployeeById(emp);
		return updatedEmployee;
	}

	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String, Boolean>> deleteEmployee(@PathVariable Long id) throws ResourceNotFound { // empService.deleteEmployee(id);

		return empService.deleteEmployee(id);

	}

}