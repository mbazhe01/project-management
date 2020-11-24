package com.mikeba.pma.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.mikeba.pma.dao.EmployeeRepository;
import com.mikeba.pma.dao.ProjectRepository;
import com.mikeba.pma.entities.Employee;
import com.mikeba.pma.entities.Project;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository emplRepo;
	
	@RequestMapping("/new")
	public String displayEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "employees/new-employee";
	}
	
	@RequestMapping("/list")
	public String displayEmployeeList(Model model) {
		
		List<Employee> employees = emplRepo.findAll();
		model.addAttribute("employeeList", employees);
		
		return "employees/employee-list";
	}
	
	@PostMapping("/save")
	public String createEmployee(Employee employee, Model model) {
		emplRepo.save(employee);
		return "redirect:/employees/new";
	}
	
	
}
