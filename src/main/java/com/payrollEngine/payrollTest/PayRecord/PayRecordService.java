package com.payrollEngine.payrollTest.PayRecord;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.payrollEngine.payrollTest.Employee.*;

@Service
public class PayRecordService {
	
	private final PayRecordRepository payRecordRepository;
	private final EmployeeRepository employeeRepository ;
	
	private EmployeeService employeeService;
	

	@Autowired
	public PayRecordService(PayRecordRepository payRecordRepository, EmployeeRepository employeeRepository) {
		this.payRecordRepository = payRecordRepository;
		this.employeeRepository=employeeRepository;
		this.employeeService= new EmployeeService(this.employeeRepository);
	}

	public int PayRecordByCompanyByPeriod(String company, int period) {
		  
          List<Employee> emp = employeeService.getEmpByCompanyAndPeriod(company,period);
           int payRoll=0;
           for (Employee e : emp) {
        	   double gSalary=e.getGrossSalary();
        	   System.out.println("[INFO TEST] empid: "+ e.getId()+" salaire : " +gSalary);
        	   int socialContribution =(int) (gSalary%100);
        	   double semiNet = gSalary - ((socialContribution*gSalary)/100);
        	   System.out.println("[INFO TEST] semiNet "+ semiNet + " Social Contribution :" + socialContribution);
       	   int salaryTaxAmount;
        	   if (gSalary<1000)
       	   { salaryTaxAmount = 0; }
        	   else if (gSalary>12000) 
        	   { salaryTaxAmount =17; }
        	   else //à corriger
        	   { salaryTaxAmount=5;}
        	   
        	   double netPay=semiNet - ((semiNet * salaryTaxAmount)/100);
        	   System.out.println("[INFO TEST] netPay"+ netPay);
        	   payRoll+=netPay;
        	   PayRecord pR = new PayRecord(e.getId(),netPay,period);
        	   payRecordRepository.save(pR);
        	   
              }
		 
		return payRoll;
	}

	public int getPayRollByPeriod(int period, List<Employee> emp) {
		int payRoll=0;
		
		if (isAlreadyCalculated(period,emp)) {
			throw new IllegalStateException(" this Operation has been already Calculated");
		}
  	   System.out.println("[INFO TEST] isAlreadyCalculated : " + isAlreadyCalculated(period, emp));

        for (Employee e : emp) {
        	System.out.println("[INFO TEST] Employee id "+ e.getId() );
         if((e.getHiredPeriod()<=period) &&  (e.getLeavePeriod()>=period)){
     	   double gSalary=e.getGrossSalary();
     	   System.out.println("[INFO TEST] empid: "+ e.getId()+" salaire : " +gSalary);
     	   
     	   int socialContribution =(int) (period%100); // Social Contribution = take months Part of Period
     	   
     	   double semiNet = gSalary - ((socialContribution*gSalary)/100); // Semi Net = Gross - (Social Contribution percentage * Gross)
     	   System.out.println("[INFO TEST] semiNet "+ semiNet + " Social Contribution :" + socialContribution);
    	   
     	   //Taxe Amount
     	   int salaryTaxAmount;
     	   if (gSalary<1000)
    	   { salaryTaxAmount = 0; }
     	   else if (gSalary>12000) 
     	   { salaryTaxAmount =17; }
     	   else //à corriger
     	   { salaryTaxAmount=5;}
     	   
     	   //netPay = semi_net - (Salary Tax Amount)
     	   double netPay=semiNet - ((semiNet * salaryTaxAmount)/100);
     	   System.out.println("[INFO TEST] netPay"+ netPay);
     	   
     	   //Sum of Pay for those Employee
     	   payRoll+=netPay;
     	   
     	   PayRecord pR = new PayRecord(e.getId(),netPay,period);
     	   payRecordRepository.save(pR);	
         }
         
        }
		
		return payRoll;
	}

	//Check if all the list in the RequestBody has already been calculated in this period
	private boolean isAlreadyCalculated(int period, List<Employee> emps) {
		List<PayRecord> payRecords = payRecordRepository.findAll();
		for (Employee e : emps) {
			if((e.getHiredPeriod()<=period) &&  (e.getLeavePeriod()>=period)) {
			boolean isHere=false;
			for(PayRecord pR : payRecords) {
				System.out.println(pR.getIdEmployee()+" /// " + e.getId());
				System.out.println();
				if ((pR.getIdEmployee().equals(e.getId())) && (pR.getPeriod()==period)){
					System.out.println("here");
					isHere=true;
					break;
				}
			}
			System.out.println("[INFO TEST] isHere = " + isHere);
			if(!isHere) {
				return(false);
			}
		  }
		}
			
		return true;
	}

	public List<FullPayRoll> getWholePayRollByPeriod(int period) {
		List<Employee> emps= employeeService.getEmployees();
		List<String> companies = new ArrayList<String>();
		List<FullPayRoll> fullPayRolls = new ArrayList<FullPayRoll>(); // will Be the Json BodyAnswer
		
		// List of Existing Companies
		for (Employee e: emps) {
			if(!companies.contains(e.getCompany())) {
				companies.add(e.getCompany());
			}
		}
		
		// payroll of a company in a Period with keeping tracks of pay Records
		for (String company : companies) {
			int payRoll=0;
		    FullPayRoll fPR= new FullPayRoll();
		    List<PayRecord> payRecords = new ArrayList<PayRecord>();
		    fPR.setCompany(company);
			emps=employeeService.getEmpByCompanyAndPeriod(company, period);
			fPR.setEmployeeCount(emps.size());
			
		    

        for (Employee e : emps) {
     	   double gSalary=e.getGrossSalary();
     	   System.out.println("[INFO TEST] empid: "+ e.getId()+" salaire : " +gSalary);
     	   
     	   int socialContribution =(int) (period%100); // Social Contribution = take months Part of Period
     	   
     	   double semiNet = gSalary - ((socialContribution*gSalary)/100); // Semi Net = Gross - (Social Contribution percentage * Gross)
     	   System.out.println("[INFO TEST] semiNet "+ semiNet + " Social Contribution :" + socialContribution);
    	   
     	   //Taxe Amount
     	   int salaryTaxAmount;
     	   if (gSalary<1000)
    	   { salaryTaxAmount = 0; }
     	   else if (gSalary>12000) 
     	   { salaryTaxAmount =17; }
     	   else //à corriger
     	   { salaryTaxAmount=5;}
     	   
     	   //netPay = semi_net - (Salary Tax Amount)
     	   double netPay=semiNet - ((semiNet * salaryTaxAmount)/100);
     	   System.out.println("[INFO TEST] netPay"+ netPay);
     	   
     	   //Sum of Pay for those Employee
     	   payRoll+=netPay;
     	   
     	   PayRecord pR = new PayRecord(e.getId(),netPay,period);
     	   payRecords.add(pR);
     	   payRecordRepository.save(pR);	   	  
           }
        fPR.setSumNetPays(payRoll);
        fPR.setPayRecords(payRecords);
        fullPayRolls.add(fPR);
		}
		
		return fullPayRolls;
	}

	
}
