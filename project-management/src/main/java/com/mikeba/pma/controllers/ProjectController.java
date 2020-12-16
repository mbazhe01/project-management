package com.mikeba.pma.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mikeba.pma.dao.EmployeeRepository;
import com.mikeba.pma.dao.ProjectRepository;
import com.mikeba.pma.entities.Employee;
import com.mikeba.pma.entities.Project;
import com.mikeba.pma.services.ProjectService;

@Controller
@RequestMapping("/projects")
public class ProjectController {

	@Autowired
	ProjectRepository proRepo;

	@Autowired
	ProjectService proService;

	@Autowired
	EmployeeRepository empRepo;

	@RequestMapping("/new")
	public String displayprojectForm(Model model) {

		List<Employee> employees = empRepo.findAll();

		model.addAttribute("project", new Project());
		model.addAttribute("allEmployees", employees);
		return "projects/new-project";
	}

	@RequestMapping("/update")
	public String displayUpdateProjectForm(@RequestParam("id") long id, Model model) {
		Project proj = proService.findById(id);
		List<Employee> employees = empRepo.findAll();

		model.addAttribute("project", proj);
		model.addAttribute("allEmployees", employees);
		return "projects/edit-project";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") long id, Model model) {
		Project proj = proRepo.findById(id);

		proService.delete(proj);

		return "redirect:/projects/list";
	}

	@RequestMapping("/list")
	public String displayProjectList(Model model) {

		List<Project> projects = proRepo.findAll(Sort.by("name"));
		model.addAttribute("projectList", projects);

		return "projects/project-list";
	}

	@PostMapping("/save")
	public String createProject(Project project, Errors errors, Model model) {
		
		if (null != errors && errors.getErrorCount() > 0) {

			String errMsg = null;

			FieldError errField = errors.getFieldError();
			String errFieldStr = errField.getField();
			String errValue = (String) errField.getRejectedValue();
			if (errFieldStr.equals("startDate")) {
				errMsg = errValue + " not a valid date. Please use yyyy-mm-dd format.";
				model.addAttribute("startDateErr", errMsg);
			}

			if (errFieldStr.equals("endDate")) {
				errMsg = errValue + " not a valid date. Please use yyyy-mm-dd format.";
				model.addAttribute("endDateErr", errMsg);

			}

			return "projects/new-project";
		}

		proRepo.save(project);
		return "redirect:/projects/list";
	}

	@PostMapping("/update")
	public String updateProject(@Valid Project project, Errors errors, Model model) {
		int rtn = errors.getErrorCount();
		String startDateErrMsg = null;
		if (null != errors && errors.getErrorCount() > 0) {

			String errMsg = null;

			FieldError errField = errors.getFieldError();
			String errFieldStr = errField.getField();
			String errValue = (String) errField.getRejectedValue();
			if (errFieldStr.equals("startDate")) {
				errMsg = errValue + " not a valid date. Please use yyyy-mm-dd format.";
				model.addAttribute("startDateErr", errMsg);
			}

			if (errFieldStr.equals("endDate")) {
				errMsg = errValue + " not a valid date. Please use yyyy-mm-dd format.";
				model.addAttribute("endDateErr", errMsg);

			}

			return "projects/edit-project";
		}

		proRepo.save(project);
		return "redirect:/projects/list";
	}

}
