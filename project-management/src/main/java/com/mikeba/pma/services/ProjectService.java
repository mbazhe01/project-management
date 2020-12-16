package com.mikeba.pma.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mikeba.pma.dao.ProjectRepository;
import com.mikeba.pma.entities.Project;

@Service
public class ProjectService {
	
	@Autowired
	ProjectRepository projRepo;
	
	public List<Project> findAll(){
		return projRepo.findAll();
	}

	public Project findById(long id) {
		
		return projRepo.findById(id);
	}

	public void delete(Project proj) {
		
		projRepo.delete(proj);
		
	}

	
}
