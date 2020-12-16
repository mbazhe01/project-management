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
import com.mikeba.pma.dao.ProjectRepository;
import com.mikeba.pma.entities.Employee;
import com.mikeba.pma.entities.Project;

@RestController
@RequestMapping("/app-api/projects")
public class ProjectApiController {

	@Autowired
	ProjectRepository proRepo;
	
	@GetMapping
	public Iterable<Project> getEmployees() {
		return proRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public Project getProjectById(@PathVariable("id") long id) {
		return proRepo.findById(id);
	}
	
	@PostMapping( consumes="application/json")
	@ResponseStatus(HttpStatus.CREATED)
	public Project create(@RequestBody Project project) {
		return proRepo.save(project);
	}
	
	// danger -> this function deletes related records
	// from project_employee table when updating employees table
	@PutMapping(path="/{id}",consumes="application/json")
	@ResponseStatus(HttpStatus.OK)
	public Project update(@RequestBody Project project) {
		return proRepo.save(project);
	}
	
	@PatchMapping(path="/{id}",consumes="application/json")
	public Project partialUpdate(@PathVariable("id") long id, @RequestBody Project patchProject) {
		Project proj = proRepo.findById(id);
		
		if(patchProject.getName()!=null)
			proj.setName(patchProject.getName());;
		
		if(patchProject.getDescription()!=null)
			proj.setDescription(patchProject.getDescription());
		
		if(patchProject.getStage()!=null)
			proj.setStage(patchProject.getStage());
		
		return proRepo.save(proj);
	}
	
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable("id") long id) {
		try {
			proRepo.deleteById(id);
		} catch(EmptyResultDataAccessException e) {
			
		}
	}
	
}// eoc
