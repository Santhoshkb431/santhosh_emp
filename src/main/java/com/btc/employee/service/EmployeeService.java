package com.btc.employee.service;





import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.btc.employee.Exception.EmployeeNotFoundException;
import com.btc.employee.bean.EmployeeDetailBean;
import com.btc.employee.dao.EmployeeDao;
import com.btc.employee.model.EmployeeDetails;
import com.btc.employee.repository.EmployeeRepository;


@Service
public class EmployeeService {
	@Autowired 
	EmployeeDao employeeDao;
	@Autowired 
	EmployeeRepository employeeRepo;

	public EmployeeDetails createUser( EmployeeDetailBean employeeDetail)
	{
		String msg="";
		EmployeeDetails employeeDetails= new EmployeeDetails(employeeDetail.getFirstname(),employeeDetail.getLastname(),employeeDetail.getDepartment(),employeeDetail.getSalary());
		employeeDao.createUser(employeeDetails);
		return employeeDetails;
	}
	public String deleteUserById(Long id) throws EmployeeNotFoundException {
		// TODO Auto-generated method stub
		EmployeeDetailBean userById=null;
	
		Optional<EmployeeDetails> user=employeeRepo.findById(id);
		
		if(user.isPresent())
		{
			
			employeeRepo.deleteById(id);
			return "Deleted Successfully";
		}
		
		else {
			throw new EmployeeNotFoundException("Record Not Found");
		}
	}
	public EmployeeDetails updateUser(EmployeeDetailBean employeeUpdateDetail,Long id) throws EmployeeNotFoundException {
	
		EmployeeDetailBean employeeById=null;
		
		Optional<EmployeeDetails> user=employeeRepo.findById(id);
	
		if(user.isPresent())
		{
		
			EmployeeDetails employeeDetail=user.get();
			employeeDetail.setFirstname(employeeUpdateDetail.getFirstname());
			employeeDetail.setLastname(employeeUpdateDetail.getLastname());
			employeeDetail.setDepartment(employeeUpdateDetail.getDepartment());
			employeeDetail.setSalary(employeeUpdateDetail.getSalary());
			employeeRepo.save(employeeDetail);
			
			employeeById=new EmployeeDetailBean();
			employeeById.setFirstname(employeeDetail.getFirstname());
			employeeById.setLastname(employeeDetail.getLastname());
			employeeById.setDepartment(employeeDetail.getDepartment());
			employeeById.setSalary(employeeDetail.getSalary());
			return employeeDetail;	
		}
		else
		{
			throw new EmployeeNotFoundException("Record Not Found");
		}
	}
	
	public EmployeeDetailBean searchById(Long id) throws EmployeeNotFoundException {
		EmployeeDetailBean employeeById=null;
		
		Optional<EmployeeDetails> user=employeeRepo.findById(id);
		
		if(user.isPresent())
		{
			employeeById=new EmployeeDetailBean();
			EmployeeDetails employeeDetail=user.get();
			employeeById.setFirstname(employeeDetail.getFirstname());
			employeeById.setLastname(employeeDetail.getLastname());
			employeeById.setDepartment(employeeDetail.getDepartment());
			employeeById.setSalary(employeeDetail.getSalary());
			
		}
		else
		{
			throw new EmployeeNotFoundException("Record Not Found");
		}
		return employeeById;
		
	}

     
	public List<EmployeeDetails> getAllDetails() {
	
		return (List<EmployeeDetails>) employeeRepo.findAll();
	}
	public EmployeeDetails getById(long id)throws EmployeeNotFoundException {
		
		return employeeRepo.findById(id).get();

	}

public EmployeeDetails updateById(EmployeeDetails employeedetails)throws EmployeeNotFoundException {

return employeeRepo.save(employeedetails);

}
 
}
