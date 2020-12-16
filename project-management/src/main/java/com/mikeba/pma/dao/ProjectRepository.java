package com.mikeba.pma.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mikeba.pma.dto.ChartData;
import com.mikeba.pma.dto.EmployeeProject;
import com.mikeba.pma.dto.ProjectStage;
import com.mikeba.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long> {

	/**
	@Override
	List<Project> findAll();
	**/
	
	List<Project> findAll(Sort sort);
	
	@Query(value = "SELECT stage as label, count(stage) as value FROM PROJECT "
					+ "group by stage",
    nativeQuery = true)
	List<ChartData> getPojectSatus();
	
	Project findById(long id);	
	
}
