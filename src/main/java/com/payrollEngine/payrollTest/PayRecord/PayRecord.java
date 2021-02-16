package com.payrollEngine.payrollTest.PayRecord;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "pay_record")
public class PayRecord {
	@Id
	private String id;
	private String idEmployee;
	private double netPay;
	private int period;
	
	public PayRecord() {
	}

	public PayRecord(String idEmployee, double netPay, int period) {
		this.idEmployee = idEmployee;
		this.netPay = netPay;
		this.period = period;
	}

	public PayRecord(String id, String idEmployee, double netPay, int period) {
		this.id = id;
		this.idEmployee = idEmployee;
		this.netPay = netPay;
		this.period = period;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getIdEmployee() {
		return idEmployee;
	}

	public void setIdEmployee(String idEmployee) {
		this.idEmployee = idEmployee;
	}

	public double getNetPay() {
		return netPay;
	}

	public void setNetPay(double netPay) {
		this.netPay = netPay;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
	}

	@Override
	public String toString() {
		return "PayRecord [id=" + id + ", idEmployee=" + idEmployee + ", netPay=" + netPay + ", period=" + period + "]";
	}
	
	

}
