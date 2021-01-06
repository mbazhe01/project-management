package com.mikeba.pma.entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Table(name = "address")
public class Address {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="address_seq")
	@SequenceGenerator(name = "address_seq", sequenceName = "address_seq", allocationSize = 1)
	private long addressId;
			
	private String addressLine1;
	private String addressLine2;
	private String addressCity;
	private String addressState;
	private String addressZip;
	
	
	@ManyToOne(
			cascade = 
			{
					//CascadeType.ALL,
			
			CascadeType.DETACH, CascadeType.MERGE, 
			CascadeType.PERSIST,
			CascadeType.REFRESH, CascadeType.REMOVE
			 
			}
			, fetch = FetchType.LAZY)
	@JoinColumn(name="employee_id")
	private Employee employee;
	
	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public Address(Employee employee, String addressLine1, String addressLine2, String addressCity, String addressState,
			String addressZip) {
		
		this.employee = employee;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZip = addressZip;
	}
	
	
	
	public Address(String addressLine1, String addressLine2, String addressCity, String addressState,
			String addressZip) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.addressCity = addressCity;
		this.addressState = addressState;
		this.addressZip = addressZip;
	}

	public Address() {
		
	}

	public long getAddressId() {
		return addressId;
	}

	public void setAddressId(long addressId) {
		this.addressId = addressId;
	}

	
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	public String getAddressCity() {
		return addressCity;
	}

	public void setAddressCity(String addressCity) {
		this.addressCity = addressCity;
	}

	public String getAddressState() {
		return addressState;
	}

	public void setAddressState(String addressState) {
		this.addressState = addressState;
	}

	public String getAddressZip() {
		return addressZip;
	}

	public void setAddressZip(String addressZip) {
		this.addressZip = addressZip;
	}
	
	
	
}
