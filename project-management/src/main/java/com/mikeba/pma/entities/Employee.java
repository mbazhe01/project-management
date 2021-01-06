package com.mikeba.pma.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mikeba.pma.dao.AddressRepository;

@Entity
@Table(name = "employee")
public class Employee {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "employee_seq")
	@SequenceGenerator(name = "employee_seq", sequenceName = "employee_seq", allocationSize = 1)
	private long employeeId;

	@NotNull
	@Size(min = 2, max = 30)
	private String firstName;
	@NotBlank
	@Size(min = 2, max = 30)
	private String lastName;
	@NotNull
	@Size(min = 2, max = 30)
	@Email
	private String email;

	@ManyToMany(cascade = { CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH }, fetch = FetchType.LAZY)
	@JoinTable(name = "project_employee", joinColumns = @JoinColumn(name = "employee_id"), inverseJoinColumns = @JoinColumn(name = "project_id"))

	@JsonIgnore
	private List<Project> projects;

	@OneToMany(mappedBy="employee", cascade = { CascadeType.DETACH,
			CascadeType.MERGE, CascadeType.PERSIST,
			CascadeType.REFRESH, CascadeType.REMOVE }, fetch = FetchType.LAZY)
	private List<Address> addresses;
		
	public Employee() {

	}

	public Employee(String firstName, String lastName, String email) {

		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
	}

	public List<Project> getProjects() {
		return projects;
	}

	public void setProjects(List<Project> projects) {
		this.projects = projects;
	}

	public long getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(long employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Address> getAddresses() {
		return addresses;
	}

	public void setAddresses(List<Address> adresses) {
		this.addresses = adresses;
	}
	
	// utility methods
	public void addAddress() {
		if (addresses == null) 
			addresses = new ArrayList<>();
		addresses.add(new Address());
	}
	
	public void deletAddress(long id) {
		if (addresses != null) {
			
			int index = -1;
			
			for (int i = 0; i < addresses.size(); i++) {
			    
			    if(addresses.get(i).getAddressId() == id) {
			    	index = i;
			    	break;
			    }
			   
			}
			
			if (index !=-1) {
				addresses.remove(index);
			}
		
			
		}//eof
			
	}
	
}
