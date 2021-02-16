package com.payrollEngine.payrollTest.PayRecord;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.payrollEngine.payrollTest.Employee.Employee;

import java.util.*;

@RestController
@RequestMapping(path = "api/v1/PayRecord")
public class PayRecordController {
	
	private final PayRecordService payRecordService;

	@Autowired
	public PayRecordController(PayRecordService payRecordService) {
		this.payRecordService = payRecordService;
	} 
	
	// Calculate Payroll of a Whole Company in a period
    @PostMapping("/ByCompanyByPeriod/{company}/{period}")
	public int PayRecordByCompanyByPeriod (@PathVariable String company, @PathVariable int period) {
    	return payRecordService.PayRecordByCompanyByPeriod(company,period);	
	}
    
    // Calculate Payroll of a Given list of Employee
    @PostMapping("/PayRollByPeriod/{period}")
    public int PayRollByPeriod (@PathVariable int period,@RequestBody List<Employee> emp) {
    	return payRecordService.getPayRollByPeriod(period,emp);
    }

    // Calculate Whole Payroll of Each Existing Company in a Given Period , return a JSON Body with all the information
    @PostMapping("/GetWholePayRoll/{period}")
    public List<FullPayRoll> GetWholePayRoll (@PathVariable int period) {
    	return payRecordService.getWholePayRollByPeriod(period);
    }
}
