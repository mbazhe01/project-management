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
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository empRepo;
	
	@RequestMapping("/new")
	public String displayprojectForm(Model model) {
		
		List<Employee> employees = empRepo.findAll();
		
		model.addAttribute("project", new Project());
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}
	
	@RequestMapping("/list")
	public String displayProjectList(Model model) {
		
		List<Project> projects = proRepo.findAll();
		model.addAttribute("projectList", projects);
		
		return "projects/project-list";
	}
	
	@PostMapping("/save")
	public String createProject(Project project, Model model) {
		proRepo.save(project);
		return "redirect:/projects/list";
	}
	
}
