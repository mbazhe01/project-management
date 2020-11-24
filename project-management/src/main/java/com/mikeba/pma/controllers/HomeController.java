package com.mikeba.pma.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mikeba.pma.dao.EmployeeRepository;
import com.mikeba.pma.dao.ProjectRepository;
import com.mikeba.pma.dto.ChartData;
import com.mikeba.pma.dto.EmployeeProject;
import com.mikeba.pma.entities.Project;

@Controller
public class HomeController {

	@Autowired
	ProjectRepository proRepo;
	
	@Autowired
	EmployeeRepository emplRepo;
	
	@RequestMapping("/")
	public String displayHome(Model model) throws JsonProcessingException {
		
		List<Project> projects = proRepo.findAll();
		List<EmployeeProject> employees =  emplRepo.getEmployeeProjects();
		
		List<ChartData> projectData = proRepo.getPojectSatus();
		
		String label;
		long value;
		
		for(ChartData c : projectData) {
			label = c.getLabel();
			value = c.getValue();
			value = value;
		}
		
		Map<String, Object> map = new HashMap<>();
		// convert projectData into a json object to use in javascript
		ObjectMapper objectMapper = new ObjectMapper();
		
		String jsonString = objectMapper.writeValueAsString(projectData);
		
		model.addAttribute("projectList", projects);
		model.addAttribute("employeeList", employees);
		model.addAttribute("projectStatusCnt", jsonString);
		return "main/home";
	}
	
}
