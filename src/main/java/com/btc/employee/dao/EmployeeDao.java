package com.btc.employee.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.btc.employee.bean.EmployeeDetailBean;
import com.btc.employee.model.EmployeeDetails;
import com.btc.employee.repository.EmployeeRepository;
@Repository
public class EmployeeDao {
	@Autowired
	EmployeeRepository employeeRepo;
	public EmployeeDetails createUser(EmployeeDetails employeeDetail) {
		
		return employeeRepo.save(employeeDetail);
	}
	
	public EmployeeDetails updateUser(EmployeeDetails userDetail)
	{
		
		
			return employeeRepo.save(userDetail);
		
			
	}
//	public EmployeeDetailBean searchById(long id)
//	{
//		return employeeRepo.searchById(id);
//	}


}
