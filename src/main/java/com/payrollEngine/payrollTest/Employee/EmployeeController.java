package com.payrollEngine.payrollTest.Employee;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/Employee")
public class EmployeeController {
	
	private final EmployeeService employeeService;
	
	@Autowired
	public EmployeeController(EmployeeService employeeService) {
		this.employeeService =employeeService ;
	}
	
    // Get All Employees in the Database
	@GetMapping
	public List<Employee> getEmployees() {
		return employeeService.getEmployees();
	}
	
	// Add a new Employee into the Database
	@PostMapping("/registerNewEmployee")
	public String registerNewEmployee (@RequestBody Employee employee) {
		employeeService.addNewEmployee(employee);
		return ("added Employee");
	}
	
	// Get List of All employees in the Company
	@GetMapping("/getEmployeeByCompany/{company}")
	public List<Employee> getEmployeesByCompany(@PathVariable String company){
		return employeeService.getEmpByCompany(company);
	}
	
	// Get List of All employees in the Company in a Specific Period
	@GetMapping("/getEmployeeByCompanyAndPeriod/{company}/{period}")
	public List<Employee> getEmployeesByCompanyAndPeriod(@PathVariable String company, @PathVariable int period){
		return employeeService.getEmpByCompanyAndPeriod(company,period);
	}
	
	// Update Gross Salary of a given employee identifier
	@PutMapping("/updateEmployee/{id}")
	public Employee updateEmployeeSalary(@PathVariable String id, @RequestBody Double salary){
		
		return employeeService.updateEmployee(id,salary);
		
	}
	
	// Get Employee by his/her ID
	@GetMapping("/getEmployeeById/{id}")
	public Optional<Employee> getEmployeebyId(@PathVariable String id) {
 		return employeeService.getByid(id);
	}
   
}
