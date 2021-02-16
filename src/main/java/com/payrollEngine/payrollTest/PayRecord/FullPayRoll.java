package com.payrollEngine.payrollTest.PayRecord;

import java.util.List;


// Class Needed for the JSON outPut of the Bonus Part
public class FullPayRoll {

	private String company;
	private int employeeCount;
	private int sumNetPays;
	private int period;
	private List<PayRecord> payRecords;
	public FullPayRoll() {
	}
	public FullPayRoll(String company, int employeeCount, int sumNetPays, int period, List<PayRecord> payRecords) {
		this.company = company;
		this.employeeCount = employeeCount;
		this.sumNetPays = sumNetPays;
		this.period = period;
		this.payRecords = payRecords;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public int getEmployeeCount() {
		return employeeCount;
	}
	public void setEmployeeCount(int employeeCount) {
		this.employeeCount = employeeCount;
	}
	public int getSumNetPays() {
		return sumNetPays;
	}
	public void setSumNetPays(int sumNetPays) {
		this.sumNetPays = sumNetPays;
	}
	public int getPeriod() {
		return period;
	}
	public void setPeriod(int period) {
		this.period = period;
	}
	public List<PayRecord> getPayRecords() {
		return payRecords;
	}
	public void setPayRecords(List<PayRecord> payRecords) {
		this.payRecords = payRecords;
	}
	@Override
	public String toString() {
		return "FullPayRoll [company=" + company + ", employeeCount=" + employeeCount + ", sumNetPays=" + sumNetPays
				+ ", period=" + period + ", payRecords=" + payRecords + "]";
	}
	
	
}
