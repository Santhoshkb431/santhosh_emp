package com.btc.employee.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;


@Table(name="Employee_details")
@Entity
@Data
public class EmployeeDetails {


	
	public EmployeeDetails(String firstname, String lastname, String department, Long salary) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.department = department;
		this.salary = salary;
	}
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id")
	protected Long id;
	@Column(name="first_name")
	protected String firstname;
	@Column(name="last_name")
	protected String lastname;
	@Column(name="department")
	protected String department;
	@Column(name="salary")
	protected Long salary;
	public EmployeeDetails()
	{
		super();
	}
}

