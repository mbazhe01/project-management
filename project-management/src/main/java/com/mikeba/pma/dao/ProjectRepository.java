package com.mikeba.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mikeba.pma.dto.ChartData;
import com.mikeba.pma.dto.EmployeeProject;
import com.mikeba.pma.dto.ProjectStage;
import com.mikeba.pma.entities.Project;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	@Override
	List<Project> findAll();
	
	@Query(value = "SELECT stage as label, count(stage) as value FROM PROJECT "
					+ "group by stage",
    nativeQuery = true)
	List<ChartData> getPojectSatus();
	
		
}
