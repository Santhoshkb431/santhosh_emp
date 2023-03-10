package com.btc.employee.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.btc.employee.bean.EmployeeDetailBean;
import com.btc.employee.model.EmployeeDetails;

public interface EmployeeRepository extends CrudRepository<EmployeeDetails,Long>{
	public EmployeeDetailBean searchById(Long id);
//	public void deleteUserById(Long id);
}
