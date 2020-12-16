package com.mikeba.pma.api.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.mikeba.pma.dao.EmployeeRepository;
import com.mikeba.pma.entities.Employee;

@RestController
@RequestMapping("/app-api/employees")
public class EmployeeApiController {
	@Autowired
	EmployeeRepository empRepo;
	
	@GetMapping
	public Iterable<Employee> getEmployees() {
		return empRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Employee getEmployyeeById(@PathVariable("id") long id) {
		return empRepo.findById(id).get();
	}
	
	@PostMapping( consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Employee create(@RequestBody Employee employee) {
		return empRepo.save(employee);
	}
	
	// danger -> this function deletes related records
	// from project_employee table when updating employees table
	@PutMapping(path="/{id}",consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Employee update(@RequestBody Employee employee) {
		return empRepo.save(employee);
	}
	
	@PatchMapping(path="/{id}",consumes="application/json")
	public Employee partialUpdate(@PathVariable("id") long id, @RequestBody Employee patchEmployee) {
		Employee emp = empRepo.findById(id).get();
		
		if(patchEmployee.getEmail()!=null)
			emp.setEmail(patchEmployee.getEmail());
		
		if(patchEmployee.getFirstName()!=null)
			emp.setFirstName(patchEmployee.getFirstName());
		
		if(patchEmployee.getLastName()!=null)
			emp.setLastName(patchEmployee.getLastName());
		
		return empRepo.save(emp);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		try {
			empRepo.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			
		}
	}
	
}// eoc
