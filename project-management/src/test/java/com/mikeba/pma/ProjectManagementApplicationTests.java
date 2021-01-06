package com.mikeba.pma;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.context.junit4.SpringRunner;

import com.mikeba.pma.dao.ProjectRepository;
import com.mikeba.pma.entities.Project;

@SpringBootTest
@RunWith(SpringRunner.class)
class ProjectManagementApplicationTests {

	@Autowired
	ProjectRepository proRepo;
	
	@Test
	void contextLoads() {
		
		int origSize = proRepo.findAll(Sort.by("name")).size();
		Project newProject = new Project("New Project", "COMPLETE", "Test Descr");
		proRepo.save(newProject);
		
		
		assertEquals(++origSize, proRepo.findAll(Sort.by("name")).size());
		
	}

}
