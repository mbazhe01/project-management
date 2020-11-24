package com.mikeba.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mikeba.pma.dto.EmployeeProject;
import com.mikeba.pma.entities.Employee;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {
	@Override
	List<Employee> findAll();
	
	@Query(value = "SELECT e.first_name as firstName, e.last_name as lastName, count(pe.employee_id) as projectCount "+
			 		"FROM EMPLOYEE e LEFT JOIN PROJECT_EMPLOYEE pe "+
			 		"ON e.employee_id = pe.employee_id " +
			 		"group by firstName, lastName, e.employee_id " +
			 		"order by 3 desc",
            nativeQuery = true)
	List<EmployeeProject> getEmployeeProjects();
}
