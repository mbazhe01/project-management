package com.mikeba.pma;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.mikeba.pma.dao.ProjectRepository;
import com.mikeba.pma.entities.Project;

@SpringBootTest
@RunWith(SpringRunner.class)
@SqlGroup({
    @Sql(executionPhase = ExecutionPhase.BEFORE_TEST_METHOD, scripts = {"classpath:schema.sql", "classpath:data.sql"}),
    @Sql(executionPhase = ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:drop.sql")
})
class ProjectManagementApplicationTests {

	@Autowired
	ProjectRepository proRepo;
	
	@Test
	void contextLoads() {
		
		Project newProject = new Project("New Project", "COMPLETE", "Test Descr");
		proRepo.save(newProject);
		
		assertEquals(5, proRepo.findAll().size());
		
	}

}
