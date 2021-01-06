package com.mikeba.pma.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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
@ContextConfiguration(classes = ProjectManagementApplication.class)
@RunWith(SpringRunner.class)
@SqlGroup({ @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = { "classpath:schema.sql",
		"classpath:data.sql" })

})
public class AddressRepositoryIntegrationTest {

	@Autowired
	AddressRepository addrRepo;

	@Autowired
	EmployeeRepository emplRepo;

	@Test
	public void ifNewAddressSaved_thenSucces() throws ParseException {

		List<Address> addrs = addrRepo.findAll();

		long origSize = addrs.size();
		Employee employee = emplRepo.findById((long) 1);

		Address address = new Address("123 Main st", "", "Brooklyn", "NY", "10022");
		address.setEmployee(employee);
		try {
			addrRepo.save(address);
		} catch (Exception e) {
			e.printStackTrace();
		}

		long newSize = addrRepo.findAll().size();
		assertEquals(++origSize, newSize);
	}
}
