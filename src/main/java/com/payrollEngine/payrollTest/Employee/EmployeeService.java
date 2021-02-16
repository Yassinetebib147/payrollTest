package com.payrollEngine.payrollTest.Employee;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {
	
	private final EmployeeRepository employeeRepository ;
	
	@Autowired
	public EmployeeService (EmployeeRepository employeeRepository) {
	  this.employeeRepository = employeeRepository;
	}
	



	
	public List<Employee> getEmployees() {
		return employeeRepository.findAll();
	}


	public void addNewEmployee(Employee employee) {
		if (employee.getGrossSalary()==0) {
			throw new IllegalStateException("Gross is Mandatory");
		}
		employeeRepository.save(employee);
		
	}


	public List<Employee> getEmpByCompany(String company) {
		List<Employee> emp=employeeRepository.findAll();
		List<Employee> empFinal = new ArrayList<Employee>();
		for (Employee e : emp)
		{
			if (e.getCompany().equals(company))
			{empFinal.add(e);
			}
		}
		return empFinal;
	}


	public Employee updateEmployee(String id, Double salary) {
		List<Employee> emps=employeeRepository.findAll();
		Employee emp = new Employee();
		for (Employee e : emps)
		{
			if (e.getId().equals(id))
			{emp=e;
			break;
			}
			
		}
		emp.setGrossSalary(salary);
		employeeRepository.save(emp);
		 return emp;
		  
	    }


	public Optional<Employee> getByid(String id) {
		Optional<Employee> e= employeeRepository.findById(id);
		return e;
	}


	public List<Employee> getEmpByCompanyAndPeriod(String company, int period) {
		List<Employee> emps = getEmpByCompany(company);
		List<Employee> empInPeriod = new ArrayList<Employee>();
		for(Employee e: emps) {
			if(period >=e.getHiredPeriod() && period<=e.getLeavePeriod()) {
				empInPeriod.add(e);
			}
		}
		return empInPeriod;
	}
}
