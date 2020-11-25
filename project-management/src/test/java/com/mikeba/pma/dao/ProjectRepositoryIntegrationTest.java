package com.mikeba.pma.dao;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;

import com.mikeba.pma.ProjectManagementApplication;
import com.mikeba.pma.dao.ProjectRepository;
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
	public void ifNewProjectSaved_thenSucces() {
		Project newProject = new Project("New Project Fake", "COMPLETED", "Test Descr");
		
		long origSize = proRepo.findAll().size();
		proRepo.save(newProject);
		
		assertEquals(++origSize, proRepo.findAll().size());
	}
}
