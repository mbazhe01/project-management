package com.mikeba.pma.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mikeba.pma.dao.EmployeeRepository;
import com.mikeba.pma.entities.Address;
import com.mikeba.pma.entities.Employee;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	EmployeeRepository emplRepo;

	@RequestMapping("/new")
	public String displayEmployeeForm(Model model) {
		Employee employee = new Employee();
		employee.addAddress();

		model.addAttribute("employee", employee);

		return "employees/new-employee";
	}

	@RequestMapping("/update")
	public String displayUpdateEmployeeForm(@RequestParam("id") long id, Model model) {
		Employee employee = emplRepo.findById(id);
		model.addAttribute("employee", employee);
		return "employees/edit-employee";
	}

	@RequestMapping("/delete")
	public String delete(@RequestParam("id") long id, Model model) {
		Employee employee = emplRepo.findById(id);

		emplRepo.delete(employee);

		return "redirect:/employees/list";
	}

	@RequestMapping("/list")
	public String displayEmployeeList(Model model) {

		List<Employee> employees = emplRepo.findAll(Sort.by("lastName"));
		model.addAttribute("employeeList", employees);
		model.addAttribute("emplCnt", employees.size());

		return "employees/employee-list";
	}

	@PostMapping("/save")
	public String createEmployee(@Valid Employee employee, Errors errors, Model model,
			@RequestParam(required = false, value = "save") String saveFlag,
			@RequestParam(required = false, value = "newaddress") String newAddress,
			@RequestParam(required = false, value = "deleteaddress") String deleteAddress
			) {
		if (null != errors && errors.getErrorCount() > 0) {
			return "employees/new-employee";
		} else if (newAddress != null) {

			// add new address line
			employee.addAddress();
			if (newAddress.equals("NewaddressOldemployee")) {
				
				
				return "employees/edit-employee";
			}
			else
				return "employees/new-employee";
		} 
		else if(deleteAddress !=null) {
			
			long addressId = Integer.parseInt(deleteAddress);
			employee.deletAddress(addressId);
			
			return "employees/edit-employee";
		}
		else {
			for (Address addr : employee.getAddresses()) {
				addr.setEmployee(employee);
			}
			emplRepo.save(employee);
			return "redirect:/employees/list";
		}

	}

	/**
	 * @PostMapping( "/save") public String addNewAddress(@RequestParam("action")
	 * String action, @Valid Employee employee, Errors errors, Model model) {
	 * 
	 * 
	 * if (null != errors && errors.getErrorCount() > 0) { return
	 * "employees/new-employee"; } else {
	 * 
	 * if (action.equals("newaddress")) { // user requested new address // add new
	 * address here return "employees/new-employee"; } else {
	 * emplRepo.save(employee); return "redirect:/employees/list"; }
	 * 
	 * }
	 * 
	 * }
	 **/

	@PostMapping("/update")
	public String updateEmployee(@Valid Employee employee, Errors errors, Model model) {
		if (null != errors && errors.getErrorCount() > 0) {
			return "employees/edit-employee";
		} else {
			emplRepo.save(employee);
			return "redirect:/employees/list";
		}

	}

}
