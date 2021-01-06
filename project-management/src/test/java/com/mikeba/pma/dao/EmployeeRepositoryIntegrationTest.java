package com.mikeba.pma.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.mikeba.pma.ProjectManagementApplication;
import com.mikeba.pma.entities.Address;
import com.mikeba.pma.entities.Employee;

@SpringBootTest
@ContextConfiguration(classes=ProjectManagementApplication.class)
@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"})
    
})
public class EmployeeRepositoryIntegrationTest {

	@Autowired
	EmployeeRepository emplRepo;
	
	@Autowired
	AddressRepository addrRepo;
	
	@Test
	public void ifNewEmployeeSaved_thenSucces() throws ParseException {
				
		List<Employee> employees = emplRepo.findAll(Sort.by("lastName"));
		
		long origSize = employees.size();
		
		
		
		try {
			Employee emp = new Employee("Vlad", "Lenin", "sky@gmail.com");
			emp.setAddresses(new ArrayList<>());
			Address address = new Address("405 Red Square", "", "Miami", "FL", "10022" );
			address.setEmployee(emp);
			emp.getAddresses().add(address);
			emp = emplRepo.save(emp);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		/**
		Address address = new Address("405 Leffer Ave", "", "Miami", "FL", "10022" );
		address.setEmployee(emp);
		try {
			addrRepo.save(address);
		}catch(Exception e) {
			e.printStackTrace();
		}
		**/
		
		long newSize = emplRepo.findAll(Sort.by("lastName")).size();
		assertEquals(++origSize, newSize);
	}
}
