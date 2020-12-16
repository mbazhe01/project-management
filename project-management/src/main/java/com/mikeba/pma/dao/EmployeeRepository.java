package com.mikeba.pma.dao;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.mikeba.pma.dto.EmployeeProject;
import com.mikeba.pma.entities.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	
	@Override 
	List<Employee> findAll();
	
	List<Employee> findAll(Sort sort);
	
	/**
	@Query(value="SELECT e.first_name as firstName, e.last_name as lastName, e.email as email "+
			"FROM EMPLOYEE e order by 2",
			nativeQuery=true)
	List<Employee> findAllSorted();
	**/
	
	@Query("select e from Employee e order by lastName")
	List<Employee> findAllSorted();
		
	@Query(value = "SELECT e.first_name as firstName, e.last_name as lastName, count(pe.employee_id) as projectCount "+
			 		"FROM EMPLOYEE e LEFT JOIN PROJECT_EMPLOYEE pe "+
			 		"ON e.employee_id = pe.employee_id " +
			 		"group by firstName, lastName, e.employee_id " +
			 		"order by 3 desc",
			 		nativeQuery = true)
	List<EmployeeProject> getEmployeeProjects();
}
