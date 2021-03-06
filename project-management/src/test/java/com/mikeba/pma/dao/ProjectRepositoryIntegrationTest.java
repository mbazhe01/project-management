package com.mikeba.pma.dao;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
import com.mikeba.pma.entities.Project;

@SpringBootTest
@ContextConfiguration(classes=ProjectManagementApplication.class)
@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"})
    
})
public class ProjectRepositoryIntegrationTest {

	@Autowired
	ProjectRepository proRepo;
	
	@Test
	public void ifNewProjectSaved_thenSucces() throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
		String startString = "7-Jun-2013";
		String endString = "7-Jun-2020";
		 
		Date start = simpleDateFormat.parse( startString );    
		Date end = simpleDateFormat.parse( endString );  
		
		Project newProject = new Project("New Project Fake", "COMPLETED", "Test Descr",
				   start, end);
		
				
		List<Project> projects = proRepo.findAll(Sort.by("name"));
		
		long origSize = projects.size();
		proRepo.save(newProject);
		
		assertEquals(++origSize, proRepo.findAll(Sort.by("name")).size());
	}
}
