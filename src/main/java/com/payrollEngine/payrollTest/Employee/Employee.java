package com.payrollEngine.payrollTest.Employee;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "employees")
public class Employee {
	
	@Id
	private String id;
	private String company;
	private double grossSalary;
	private int hiredPeriod;
	private int leavePeriod;
	
	public Employee() {
	}

	public Employee(String id, String company, double grossSalary, int hiredPeriod, int leavePeriod) {
		this.id = id;
		this.company = company;
		this.grossSalary = grossSalary;
		this.hiredPeriod = hiredPeriod;
		this.leavePeriod = leavePeriod;
	}

	public Employee(String company, double grossSalary, int hiredPeriod, int leavePeriod) {
		this.company = company;
		this.grossSalary = grossSalary;
		this.hiredPeriod = hiredPeriod;
		this.leavePeriod = leavePeriod;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public double getGrossSalary() {
		return grossSalary;
	}

	public void setGrossSalary(double grossSalary) {
		this.grossSalary = grossSalary;
	}

	public int getHiredPeriod() {
		return hiredPeriod;
	}

	public void setHiredPeriod(int hiredPeriod) {
		this.hiredPeriod = hiredPeriod;
	}

	public int getLeavePeriod() {
		return leavePeriod;
	}

	public void setLeavePeriod(int leavePeriod) {
		this.leavePeriod = leavePeriod;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", company=" + company + ", grossSalary=" + grossSalary + ", hiredPeriod="
				+ hiredPeriod + ", leavePeriod=" + leavePeriod + "]";
	}
	
	
	
	
	
	
	
}
