package com.ibm.LabOneToMany.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.ibm.LabOneToMany.dto.ErrorMessageDto;
import com.ibm.LabOneToMany.myException.EmployeeAlreadyPresent;
import com.ibm.LabOneToMany.myException.EmployeeNotPresent;
import com.ibm.LabOneToMany.myException.ResourceNotFound;

@RestControllerAdvice
public class EmployeeAdvice {
	@ExceptionHandler(EmployeeNotPresent.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ErrorMessageDto handlEmployeeNotFound(EmployeeNotPresent exception) {
		return new ErrorMessageDto(exception.getMessage(), HttpStatus.NOT_FOUND.toString());
	}

	@ExceptionHandler(ResourceNotFound.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handleEmployeeEmailPresent(ResourceNotFound exception) {
		System.out.println("EmployeeEmailPresent" + exception);
		return new ErrorMessageDto(exception.getMessage(), HttpStatus.BAD_REQUEST.toString());
	}

	@ExceptionHandler(EmployeeAlreadyPresent.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ErrorMessageDto handlEmployeeAlreadyPresent(EmployeeAlreadyPresent exception) {
		return new ErrorMessageDto(exception.getMessage(), HttpStatus.BAD_REQUEST.toString());
	}

}