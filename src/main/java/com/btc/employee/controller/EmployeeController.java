package com.btc.employee.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.btc.employee.Exception.EmployeeNotFoundException;
import com.btc.employee.bean.EmployeeDetailBean;
import com.btc.employee.model.EmployeeDetails;
import com.btc.employee.repository.EmployeeRepository;
import com.btc.employee.service.EmployeeService;



@Controller
@Transactional
@Validated
@RequestMapping("/employee")
public class EmployeeController {
	@Autowired
	EmployeeService employeeService;
	@Autowired
	EmployeeRepository employeeRepo;
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	 
	    ex.getBindingResult().getFieldErrors().forEach(error -> 
	        errors.put(error.getField(), error.getDefaultMessage()));
	     
	    return errors;
	}
	@PostMapping(value = "/")
//	public EmployeeDetails createuser(@RequestHeader String uniqueIdentification,@RequestBody @Valid  EmployeeDetailBean employeeDetail )
//	{
//		EmployeeDetails employeeDetails = employeeService.createUser(employeeDetail);
//		return employeeDetails;
//			
//	}
	@DeleteMapping(value="/{id}")
	@Transactional
	public String deleteUser(@PathVariable("id") long id) throws EmployeeNotFoundException {
		return employeeService.deleteUserById(id);
		
	}
	
	@GetMapping("/{id}")
	public EmployeeDetailBean searchById(@PathVariable Long id) throws EmployeeNotFoundException
	{
		EmployeeDetailBean employeeDetailBean=employeeService.searchById(id);
		return employeeDetailBean;
	}
	
//		@GetMapping("/getAll")
//		public List<EmployeeDetails> getAllDetails()
//		{
//			return employeeService.getAllDetails();
//			
//	}
  @GetMapping("/employee")
	public String getAllDetails(Model model)
	{
		List<EmployeeDetails> employee=employeeService.getAllDetails();
		
		model.addAttribute("employee", employee);
		return "employee";
	
	}
	
	
	
	
//	@PutMapping(value="/{id}")
//	public  EmployeeDetails updateUser(@RequestBody  EmployeeDetailBean userDetail,@PathVariable Long id) throws EmployeeNotFoundException
//	{
//		EmployeeDetails userDetails = employeeService.updateUser(userDetail, id);
//		return userDetails;
//	}
	@GetMapping(value ="/employee/new")
	public String createuser(EmployeeDetailBean employeedetail,Model model )
	{
		
		EmployeeDetails employeeDetails = new EmployeeDetails();
		//playerDetails=playerService.createPlayer(playerDetail);
		model.addAttribute("employeeDetails",employeeDetails);
		return "AddEmployees";
	}
	
	@PostMapping(value = "/employee")
	public String createuser(EmployeeDetailBean employeedetail )
	{
		
		EmployeeDetails employeeDetails = employeeService.createUser(employeedetail);
		
		
		return "redirect:/employee/employee";
	}
	

	@GetMapping("/employee/edit/{id}")
	public String editStudentForm(@PathVariable long id, Model model) throws EmployeeNotFoundException {
		model.addAttribute("employee", employeeService.getById(id));
		return "editemployee";
	}

	@PostMapping("/employee/{id}")
	public String updateStudent(@PathVariable long id,
			@ModelAttribute("employee") EmployeeDetails employeedetails,
			Model model) throws EmployeeNotFoundException {

		
		
		EmployeeDetails existemp = employeeService.getById(id);
		existemp.setId(id);
		existemp.setFirstname(employeedetails.getFirstname());
		existemp.setLastname(employeedetails.getLastname());
		existemp.setDepartment(employeedetails.getDepartment());
		existemp.setSalary(employeedetails.getSalary());

		
		employeeService.updateById(existemp);
		return "redirect:/employee/employee";		
	}
	@GetMapping("/employee/{id}")
	public String deletePlayer(@PathVariable("id") long id)throws EmployeeNotFoundException
	{
		employeeService.deleteUserById(id);
		return "redirect:/employee/employee";
	}
}
