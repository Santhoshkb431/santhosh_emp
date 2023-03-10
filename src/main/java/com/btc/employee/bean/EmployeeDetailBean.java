package com.btc.employee.bean;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@Data
@Getter
@Setter
public class EmployeeDetailBean {
	@NotNull(message="Name should not be null")
	@NotEmpty(message="Name should not be empty")
	@Size(min=4,max=50,message="Name shouldbe between 4 to 50 character")
	@Pattern(regexp="^[A-Za-z]*$",message="First Name should only contains Characters")
    private String firstname;
	@Size(min=4,max=50,message="lastName shouldbe between 4 to 50 character")
	@Pattern(regexp="^[A-Za-z]*$",message="last Name should only contains Characters")
    private String lastname;
	@NotNull(message="DepartmentName should not be null")
	@NotEmpty(message="DepartmentName should not be empty")
	@Size(min=4,max=50,message=" Department Name shouldbe between 4 to 50 character")
	@Pattern(regexp="^[A-Za-z]*$",message="department Name should only contains Characters")
    private String department;
	@NotNull(message="salary should not be empty")
	@Min(value=1000,message="salary must be of 4 digits ")
	@Max(value=99999999,message="pincode must be of 8 digits ")
    private long salary;
	int sal;
    
    
}
